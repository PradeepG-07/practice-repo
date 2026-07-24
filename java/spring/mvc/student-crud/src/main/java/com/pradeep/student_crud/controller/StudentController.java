package com.pradeep.student_crud.controller;

import com.pradeep.student_crud.model.Student;
import com.pradeep.student_crud.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student newStudent = this.studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newStudent);
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudent(@PathVariable("rollNumber") Long rollNumber){
        Student student = studentService.getStudent(rollNumber);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudents = this.studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }

    @PutMapping("/{rollNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable("rollNumber") Long rollNumber,
                                                 @RequestBody Student student){
        Student updatedStudent = this.studentService.updateStudent(rollNumber, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{rollNumber}")
    public ResponseEntity deleteStudent(@PathVariable("rollNumber") Long rollNumber){
        boolean isStudentDeleted = this.studentService.deleteStudent(rollNumber);
        if(isStudentDeleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

}
