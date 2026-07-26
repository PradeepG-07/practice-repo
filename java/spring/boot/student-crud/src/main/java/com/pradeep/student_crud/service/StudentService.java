package com.pradeep.student_crud.service;

import com.pradeep.student_crud.model.Student;
import com.pradeep.student_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Optional<Student> createStudent(Student student){
        Student existingStudent =
                this.studentRepository.getStudent(student.getRollNumber());
        if(existingStudent != null){
            return Optional.empty();
        }
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return Optional.of(this.studentRepository.createStudent(student));
    }

    public Optional<Student> updateStudent(Long rollNumber, Student student){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);
        if(existingStudent == null){
            return Optional.empty();
        }

        student.setRollNumber(existingStudent.getRollNumber());
        student.setUpdatedAt(LocalDateTime.now());
        return Optional.of(this.studentRepository.updateStudent(rollNumber, student));
    }

    public Optional<Student> getStudent(Long rollNumber){
        return Optional.ofNullable(this.studentRepository.getStudent(rollNumber));
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(this.studentRepository.getAllStudents());
    }

    public Boolean deleteStudent(Long rollNumber){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);

        if(existingStudent == null)
            return  false;

        return this.studentRepository.deleteStudent(rollNumber);
    }
}
