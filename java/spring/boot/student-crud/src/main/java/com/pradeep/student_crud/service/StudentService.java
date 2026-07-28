package com.pradeep.student_crud.service;

import com.pradeep.student_crud.dto.request.CreateStudentRequestDto;
import com.pradeep.student_crud.dto.response.GenericStudentResponseDto;
import com.pradeep.student_crud.dto.request.UpdateStudentRequestDto;
import com.pradeep.student_crud.exception.DuplicateResourceException;
import com.pradeep.student_crud.exception.ResourceNotFoundException;
import com.pradeep.student_crud.model.Student;
import com.pradeep.student_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public GenericStudentResponseDto createStudent(CreateStudentRequestDto studentRequest){
        Student existingStudent =
                this.studentRepository.getStudent(studentRequest.getRollNumber());

        if(existingStudent != null)
            throw new DuplicateResourceException(
                    String.format("Student already exists with %s roll number.", existingStudent.getRollNumber())
            );

        Student student = mapToModel(studentRequest);
        Student createdStudent = this.studentRepository.createStudent(student);

        GenericStudentResponseDto responseDto = mapToResponseDto(createdStudent);
        return responseDto;
    }

    public GenericStudentResponseDto updateStudent(Long rollNumber,
                                                             UpdateStudentRequestDto studentRequest){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);
        if(existingStudent == null)
            throw new ResourceNotFoundException(String.format("Student with roll number %d not found.", rollNumber));

        Student student = mapToModel(existingStudent, studentRequest);
        Student updatedStudent = this.studentRepository.updateStudent(rollNumber, student);

        GenericStudentResponseDto responseDto = mapToResponseDto(updatedStudent);
        return responseDto;
    }

    public GenericStudentResponseDto getStudent(Long rollNumber){
        Student student = this.studentRepository.getStudent(rollNumber);
        if(student == null)
            throw new ResourceNotFoundException(String.format("Student with roll number %d not found.", rollNumber));
        GenericStudentResponseDto responseDto = mapToResponseDto(student);
        return responseDto;
    }

    public List<GenericStudentResponseDto> getAllStudents(){
        return this.studentRepository.getAllStudents()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public void deleteStudent(Long rollNumber){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);

        if(existingStudent == null)
            throw new ResourceNotFoundException(String.format("Student with roll number %d not found.", rollNumber));

        this.studentRepository.deleteStudent(rollNumber);
    }

    private Student mapToModel(CreateStudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setRollNumber(studentRequestDto.getRollNumber());
        student.setEmail(studentRequestDto.getEmail());
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setSkills(studentRequestDto.getSkills());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return student;
    }

    private Student mapToModel(Student existingStudent, UpdateStudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setRollNumber(existingStudent.getRollNumber());
        student.setEmail(studentRequestDto.getEmail());
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setSkills(studentRequestDto.getSkills());
        student.setCreatedAt(existingStudent.getCreatedAt());
        student.setUpdatedAt(LocalDateTime.now());
        return student;
    }

    private GenericStudentResponseDto mapToResponseDto(Student createdStudent) {
        GenericStudentResponseDto studentResponseDto = new GenericStudentResponseDto();
        studentResponseDto.setRollNumber(createdStudent.getRollNumber());
        studentResponseDto.setEmail(createdStudent.getEmail());
        studentResponseDto.setName(createdStudent.getName());
        studentResponseDto.setAge(createdStudent.getAge());
        studentResponseDto.setSkills(createdStudent.getSkills());
        studentResponseDto.setCreatedAt(createdStudent.getCreatedAt());
        studentResponseDto.setUpdatedAt(createdStudent.getUpdatedAt());
        return studentResponseDto;
    }
}
