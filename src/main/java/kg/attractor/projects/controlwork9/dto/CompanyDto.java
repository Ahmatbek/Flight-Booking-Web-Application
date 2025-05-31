//package kg.attractor.projects.controlwork9.dto;
//
//import jakarta.validation.constraints.*;
//import kg.attractor.projects.controlwork9.annotations.ValidEmail;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.UniqueElements;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CompanyDto {
//    private Long id;
//    @NotBlank
//    private String name;
//    @Email
//    @ValidEmail
//    private String email;
//    @NotBlank(message = "Password can't be empty")
//    @Size(min = 4, max = 150, message = "Length must be >= 4 and <= 150")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
//            message = "Should contain at least one uppercase letter, one number")
//    private String password;
//    private String logo;
//    private boolean active;
//}
//
