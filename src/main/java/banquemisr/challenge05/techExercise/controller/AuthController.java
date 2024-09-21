package banquemisr.challenge05.techExercise.controller;

import banquemisr.challenge05.techExercise.payload.userDto.request.UserLoginDTO;
import banquemisr.challenge05.techExercise.payload.userDto.request.UserRegistrationDTO;
import banquemisr.challenge05.techExercise.payload.userDto.response.LoginResponseDTO;
import banquemisr.challenge05.techExercise.services.UserService.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix.auth}")
@AllArgsConstructor
@Slf4j
public class AuthController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<Void> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
    log.info("Received signup request for user: {}", userRegistrationDTO.getUsername());
    userService.signup(userRegistrationDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
    log.info("Received login request for user: {}", userLoginDTO.getUsername());
    LoginResponseDTO loginResponse = userService.authenticate(userLoginDTO);
    return ResponseEntity.ok(loginResponse);
  }
}
