package com.pradeep.student_crud.repository;

import com.pradeep.student_crud.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private Map<Long, Student> studentDB;

    public StudentRepository(){
        // TODO: Use ConcurrentHashMap
        this.studentDB = new HashMap<>();
    }

    public Student createStudent(Student student){
        this.studentDB.put(student.getRollNumber(), student);
        return student;
    }

    public Student updateStudent(Long rollNumber, Student student){
        this.studentDB.put(rollNumber, student);
        return student;
    }

    public Student getStudent(Long rollNumber){
        return this.studentDB.getOrDefault(rollNumber, null);
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(this.studentDB.values());
    }

    public Boolean deleteStudent(Long rollNumber){
        this.studentDB.remove(rollNumber);
        return true;
    }

}
