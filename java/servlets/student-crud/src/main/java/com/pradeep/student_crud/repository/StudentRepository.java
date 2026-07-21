package com.pradeep.student_crud.repository;

import com.pradeep.student_crud.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    Map<Long, Student> studentDB;

    public StudentRepository(){
        this.studentDB = new HashMap<>();
    }

    public Student createStudent(Student student){
        studentDB.put(student.getRollNumber(), student);
        return student;
    }
    public Student getStudent(Long rollNumber){
        return studentDB.getOrDefault(rollNumber, null);
    }
    public List<Student> getAllStudents(){
        return new ArrayList<>(studentDB.values());
    }
    public Student updateStudent(Long rollNumber, Student student){
        studentDB.put(rollNumber, student);
        return student;
    }
    public Student deleteStudent(Long rollNumber){
        return studentDB.remove(rollNumber);
    }
}
