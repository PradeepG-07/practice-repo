package com.pradeep.student_crud.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;

public class UpdateStudentRequestDto {
    @NotBlank(message = "Name cannot be null or empty or blank.")
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email is not valid.")
    private String email;

    @NotNull(message = "Age cannot be null.")
    @Positive(message = "Age must be positive.")
    @Min(value = 4, message = "Age must be between 3 and 26.")
    @Max(value = 25, message = "Age must be between 3 and 26.")
    private Integer age;

    @NotNull(message = "Skills cannot be null.")
    private List<String> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
