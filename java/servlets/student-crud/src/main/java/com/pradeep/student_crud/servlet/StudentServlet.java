package com.pradeep.student_crud.servlet;

import com.pradeep.student_crud.entity.Student;
import com.pradeep.student_crud.service.StudentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/students")
public class StudentServlet extends HttpServlet {
    StudentService studentService;

    public StudentServlet(){
        this.studentService = new StudentService();
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String rollNumber = request.getParameter("rollNumber");
        String email = request.getParameter("email");

        response.setContentType("application/json");

        if(name == null || rollNumber == null || email == null){
            response.setStatus(422);
            response.getWriter()
                    .write(writeMessage("Some of the fields are missing."));
            return ;
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setRollNumber(Long.parseLong(rollNumber));

        Student createdStudent = studentService.createStudent(student);
        response.setStatus(201);
        response.getWriter().write(mapStudentToJSON(createdStudent));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String rollNumber = request.getParameter("rollNumber");
        response.setContentType("application/json");
        if(rollNumber == null){
            List<Student> studentList = studentService.getAllStudents();
            response.setStatus(200);
            response.getWriter()
                    .write(mapStudentsToJSON(studentList));
            return ;
        }

        Student student = studentService.getStudent(Long.parseLong(rollNumber));
        if(student == null){
            response.setStatus(404);
            response.getWriter()
                    .write(writeMessage("Student not found."));
            return ;
        }
        response.setStatus(200);
        response.getWriter()
                .write(mapStudentToJSON(student));
    }

    @Override
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String rollNumber = request.getParameter("rollNumber");
        String email = request.getParameter("email");

        response.setContentType("application/json");

        if(name == null || rollNumber == null || email == null){
            response.setStatus(422);
            response.getWriter()
                    .write(writeMessage("Some of the fields are missing."));
            return ;
        }

        Student existingStudent = studentService.getStudent(Long.parseLong(rollNumber));
        if(existingStudent == null){
            response.setStatus(404);
            response.getWriter()
                    .write(writeMessage("Student not found."));
            return ;
        }

        existingStudent.setName(name);
        existingStudent.setEmail(email);

        Student updatedStudent =
                studentService.updateStudent(Long.parseLong(rollNumber), existingStudent);
        response.setStatus(200);
        response.getWriter().write(mapStudentToJSON(updatedStudent));
    }

    @Override
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        String rollNumber = request.getParameter("rollNumber");
        if(rollNumber == null){
            response.setStatus(422);
            response.getWriter()
                    .write(writeMessage("Some of the fields are missing."));
            return ;
        }
        Student deletedStudent = studentService.deleteStudent(Long.parseLong(rollNumber));
        response.setContentType("application/json");
        if(deletedStudent == null){
            response.setStatus(404);
            response.getWriter()
                    .write(writeMessage("Student not found."));
            return ;
        }
        response.setStatus(204);
        response.getWriter()
                .write(writeMessage("Student deleted."));
    }

    private String mapStudentToJSON(Student student){
        return String.format(
                """
                {
                    "name": "%s",
                    "rollNumber": "%d",
                    "email": "%s"
                }
                """,
                student.getName(),
                student.getRollNumber(),
                student.getEmail());
    }

    private String mapStudentsToJSON(List<Student> studentList){
        StringBuilder stringBuilder = new StringBuilder("[\n");
        for (int i = 0; i < studentList.size(); i++) {
            stringBuilder.append(mapStudentToJSON(studentList.get(i)));
            if(i==studentList.size()) continue;
            stringBuilder.append(",\n");
        }
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }

    private String writeMessage(String message){
        return String.format(
                """
                {
                    "message" : "%s"
                }
                """,
               message
        );
    }
}
