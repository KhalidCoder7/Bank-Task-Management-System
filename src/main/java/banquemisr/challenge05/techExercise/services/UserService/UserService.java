package banquemisr.challenge05.techExercise.services.UserService;


import banquemisr.challenge05.techExercise.payload.userDto.request.UserLoginDTO;
import banquemisr.challenge05.techExercise.payload.userDto.request.UserRegistrationDTO;
import banquemisr.challenge05.techExercise.payload.userDto.response.LoginResponseDTO;

public interface UserService {
   void signup (UserRegistrationDTO registrationDTO);
   LoginResponseDTO authenticate (UserLoginDTO userLoginDTO);
}
