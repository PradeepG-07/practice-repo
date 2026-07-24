package com.pradeep.student_crud.repository;

import com.pradeep.student_crud.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class StudentRepository {
    private Map<Long, Student> studentDB;

    public StudentRepository(){
        this.studentDB = new HashMap<>();
    }

    public Student createStudent(Student student){
        this.studentDB.put(student.getRollNumber(), student);
        return  student;
    }

    public Student getStudent(Long rollNumber){
        return this.studentDB.getOrDefault(rollNumber, null);
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(this.studentDB.values());
    }

    public Student updateStudent(Long rollNumber, Student student){
        this.studentDB.put(rollNumber, student);
        return student;
    }

    public void deleteStudent(Long rollNumber){
        this.studentDB.remove(rollNumber);
    }
}
