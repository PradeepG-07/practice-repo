package com.pradeep.student_crud.service;

import com.pradeep.student_crud.dto.request.CreateStudentRequestDto;
import com.pradeep.student_crud.dto.response.GenericStudentResponseDto;
import com.pradeep.student_crud.dto.request.UpdateStudentRequestDto;
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

    public Optional<GenericStudentResponseDto> createStudent(CreateStudentRequestDto studentRequest){
        Student existingStudent =
                this.studentRepository.getStudent(studentRequest.getRollNumber());
        if(existingStudent != null){
            return Optional.empty();
        }

        Student student = mapToModel(studentRequest);
        Student createdStudent = this.studentRepository.createStudent(student);

        if(createdStudent == null)
            return Optional.empty();

        GenericStudentResponseDto responseDto = mapToResponseDto(createdStudent);
        return Optional.of(responseDto);
    }

    public Optional<GenericStudentResponseDto> updateStudent(Long rollNumber,
                                                             UpdateStudentRequestDto studentRequest){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);
        if(existingStudent == null){
            return Optional.empty();
        }

        Student student = mapToModel(existingStudent, studentRequest);
        Student updatedStudent = this.studentRepository.updateStudent(rollNumber, student);

        if(updatedStudent == null)
            return Optional.empty();

        GenericStudentResponseDto responseDto = mapToResponseDto(updatedStudent);
        return Optional.of(responseDto);
    }

    public Optional<GenericStudentResponseDto> getStudent(Long rollNumber){
        Student student = this.studentRepository.getStudent(rollNumber);
        if(student == null)
            return Optional.empty();
        return Optional.of(mapToResponseDto(student));
    }

    public List<GenericStudentResponseDto> getAllStudents(){
        return this.studentRepository.getAllStudents()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Boolean deleteStudent(Long rollNumber){
        Student existingStudent =
                this.studentRepository.getStudent(rollNumber);

        if(existingStudent == null)
            return  false;

        return this.studentRepository.deleteStudent(rollNumber);
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
