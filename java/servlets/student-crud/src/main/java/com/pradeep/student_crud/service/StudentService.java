package com.pradeep.student_crud.service;

import com.pradeep.student_crud.entity.Student;
import com.pradeep.student_crud.repository.StudentRepository;

import java.util.List;

public class StudentService {

    StudentRepository studentRepository;
    public StudentService(){
        this.studentRepository = new StudentRepository();
    }

    public Student createStudent(Student student){
        return this.studentRepository.createStudent(student);
    }
    public Student getStudent(Long rollNumber){
        return this.studentRepository.getStudent(rollNumber);
    }
    public List<Student> getAllStudents(){
        return this.studentRepository.getAllStudents();
    }
    public Student updateStudent(Long rollNumber, Student student){
        return this.studentRepository
                .updateStudent(rollNumber, student);
    }
    public Student deleteStudent(Long rollNumber){
        return this.studentRepository.deleteStudent(rollNumber);
    }
}
