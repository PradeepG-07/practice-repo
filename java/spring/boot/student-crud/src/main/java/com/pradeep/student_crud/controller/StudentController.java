package com.pradeep.student_crud.controller;

import com.pradeep.student_crud.model.Student;
import com.pradeep.student_crud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Optional<Student> createdStudent =
                this.studentService.createStudent(student);
        if(createdStudent.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent.get());
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudent(@PathVariable Long rollNumber){
        Optional<Student> student = this.studentService.getStudent(rollNumber);
        if(student.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student.get());
    }

    @PutMapping("/{rollNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long rollNumber,
                                                 @RequestBody Student studentReq){
        Optional<Student> student = this.studentService.updateStudent(rollNumber, studentReq);
        if(student.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student.get());
    }

    @DeleteMapping("/{rollNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long rollNumber){
        Boolean isStudentDeleted = this.studentService.deleteStudent(rollNumber);
        if(isStudentDeleted == false)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
