package com.pradeep.student_crud.controller;

import com.pradeep.student_crud.dto.request.CreateStudentRequestDto;
import com.pradeep.student_crud.dto.response.GenericStudentResponseDto;
import com.pradeep.student_crud.dto.request.UpdateStudentRequestDto;
import com.pradeep.student_crud.dto.response.ApiResponseDto;
import com.pradeep.student_crud.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    public ResponseEntity<ApiResponseDto<GenericStudentResponseDto>> createStudent(@Valid @RequestBody CreateStudentRequestDto studentRequest){
        GenericStudentResponseDto createdStudent = this.studentService.createStudent(studentRequest);

        ApiResponseDto<GenericStudentResponseDto> apiResponseDto =
                ApiResponseDto.success(HttpStatus.CREATED, createdStudent, "Student created successfully.");

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<GenericStudentResponseDto>>> getAllStudents(){
        List<GenericStudentResponseDto> studentList =  this.studentService.getAllStudents();

        ApiResponseDto<List<GenericStudentResponseDto>> apiResponseDto =
                ApiResponseDto.success(studentList, "Students fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK).body(apiResponseDto);
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<ApiResponseDto<GenericStudentResponseDto>> getStudent(@Valid @NotNull(message = "Roll number cannot be null.") @PathVariable Long rollNumber){
        GenericStudentResponseDto student = this.studentService.getStudent(rollNumber);

        ApiResponseDto<GenericStudentResponseDto> apiResponseDto =
                ApiResponseDto.success(student, "Student fetched successfully.");

        return ResponseEntity.ok(apiResponseDto);
    }

    @PutMapping("/{rollNumber}")
    public ResponseEntity<ApiResponseDto<GenericStudentResponseDto>> updateStudent(@Valid @NotNull(message = "Roll number cannot be null.") @PathVariable Long rollNumber,
                                                 @Valid @RequestBody UpdateStudentRequestDto studentReq){
        GenericStudentResponseDto updatedStudent = this.studentService.updateStudent(rollNumber, studentReq);

        ApiResponseDto<GenericStudentResponseDto> apiResponseDto =
                    ApiResponseDto.success(updatedStudent, "Student updated successfully.");

        return ResponseEntity.ok(apiResponseDto);
    }

    @DeleteMapping("/{rollNumber}")
    public ResponseEntity<ApiResponseDto<Void>> deleteStudent(@Valid @NotNull(message = "Roll number cannot be null.") @PathVariable Long rollNumber){
        this.studentService.deleteStudent(rollNumber);

        ApiResponseDto<Void> apiResponseDto =
                ApiResponseDto.success(HttpStatus.NO_CONTENT, null, "Student deleted successfully.");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponseDto);
    }
}
