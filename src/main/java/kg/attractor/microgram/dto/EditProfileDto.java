package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditProfileDto {

    @NotBlank(message = "Имя обязателен")
    @Size(min = 2, max = 30, message = "Минимальное количество символов 2, максимальное 30")
    private String fullName;
    @NotBlank(message = "Ник обязателен")
    private String nickName;
    private String bio;
}
