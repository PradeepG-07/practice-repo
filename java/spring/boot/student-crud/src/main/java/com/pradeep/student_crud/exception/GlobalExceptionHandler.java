package com.pradeep.student_crud.exception;

import com.pradeep.student_crud.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Map<String,String>>>
        handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ApiResponseDto<Map<String, String>> apiResponseDto =
                ApiResponseDto.error(HttpStatus.UNPROCESSABLE_CONTENT, validationErrors, "Validations failed");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(apiResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiResponseDto<Void> apiResponseDto =
                ApiResponseDto.error(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseDto);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleDuplicateResourceException(DuplicateResourceException exception){
        ApiResponseDto<Void> apiResponseDto =
                ApiResponseDto.error(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponseDto);
    }

    // Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleGenericException(Exception exception){
        // TODO: Log the exception message and stack
        ApiResponseDto<Void> apiResponseDto =
                ApiResponseDto.error(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponseDto);
    }
}
