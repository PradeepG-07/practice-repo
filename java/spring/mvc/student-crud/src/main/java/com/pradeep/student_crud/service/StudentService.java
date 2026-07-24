package com.pradeep.student_crud.service;

import com.pradeep.student_crud.model.Student;
import com.pradeep.student_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        this.studentRepository.createStudent(student);
        return  student;
    }

    public Student getStudent(Long rollNumber){
        return this.studentRepository.getStudent(rollNumber);
    }

    public List<Student> getAllStudents(){
        return this.studentRepository.getAllStudents();
    }

    public Student updateStudent(Long rollNumber, Student student){
       return this.studentRepository.updateStudent(rollNumber, student);
    }

    public boolean deleteStudent(Long rollNumber){
        Student existingStudent = getStudent(rollNumber);
        if(existingStudent == null) return false;
        this.studentRepository.deleteStudent(rollNumber);
        return true;
    }

}
