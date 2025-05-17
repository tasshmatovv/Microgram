package kg.attractor.microgram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotBlank(message = "Ник обязателен")
    private String nickName;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Пароль должен содержать хотя бы одну заглавную букву и одну цифру")
    @Size(min = 8, message = "Минимальное количество символов 8")
    private String password;

    @NotBlank(message = "Имя обязателен")
    @Size(min = 2, max = 30, message = "Минимальное количество символов 2, максимальное 30")
    private String fullName;
    private String avatar;
    private String bio;
    private Boolean enabled;
    private Integer accountTypeId;
}
