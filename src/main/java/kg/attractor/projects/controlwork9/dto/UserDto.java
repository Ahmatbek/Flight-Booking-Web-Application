package kg.attractor.projects.controlwork9.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.attractor.projects.controlwork9.annotations.ValidEmail;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "Email can't be empty")
    @Email
    @ValidEmail
    private String email;
    @NotBlank(message = "Password can't be empty")
    @Size(min = 4, max = 150, message = "Length must be >= 4 and <= 150")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one uppercase letter, one number")
    private String password;
    @NotBlank(message = "authority can't be empty")
    private String authority;
    private Boolean enabled;

}
