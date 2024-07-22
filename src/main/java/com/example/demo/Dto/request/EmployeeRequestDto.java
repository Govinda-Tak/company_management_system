package com.example.demo.Dto.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.enums.Designation;
import com.example.demo.util.annotation.ValidDateOfBirth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRequestDto {

    @NotBlank(message = "Employee name required !!")
    @Length(max = 255, min = 2, message = "max. name <=255 and >=2 character required !!")
    private String name;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid contact number")
    private String contactNo;

    @ValidDateOfBirth(message = "Age must be between 18 to 60 years old !!")
    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotNull(message = "Designation required !!")
    private Designation designation;

    @NotBlank(message = "Email required !!")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password required !!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @NotNull(message = "Department required !!")
    private Long departmentId;

    @Length(min = 2,max = 255,message = "min 2 and max 255 char required !!")
    @NotNull(message = "Address needed !!")
	private String address;
    @Length(min = 2,max = 50,message = "min 2 and max 50 char required !!")
	@NotBlank(message = "state required !!")
	private String state;
    @Length(min = 2,max = 50,message = "min 2 and max 50 char required !!")
	@NotBlank(message = "country required !!")
	private String country;
	
	@Pattern(regexp = "^[1-9][0-9]{5}$")
	private String pinCode;
	
	private MultipartFile profilePicture;
	
}

