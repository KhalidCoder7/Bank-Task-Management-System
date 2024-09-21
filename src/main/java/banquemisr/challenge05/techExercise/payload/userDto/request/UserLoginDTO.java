package banquemisr.challenge05.techExercise.payload.userDto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
	@NotBlank(message = "username is mandatory")
	private String username;

	@NotBlank(message = "Password is mandatory")
	private String password;
}
