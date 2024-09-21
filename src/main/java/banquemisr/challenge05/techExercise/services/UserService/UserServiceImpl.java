package banquemisr.challenge05.techExercise.services.UserService;
import banquemisr.challenge05.techExercise.exception.userException.UserLoginException;
import banquemisr.challenge05.techExercise.exception.userException.UserRegistrationException;
import banquemisr.challenge05.techExercise.mapper.UserMapper;
import banquemisr.challenge05.techExercise.model.User;
import banquemisr.challenge05.techExercise.payload.userDto.request.UserLoginDTO;
import banquemisr.challenge05.techExercise.payload.userDto.request.UserRegistrationDTO;
import banquemisr.challenge05.techExercise.payload.userDto.response.LoginResponseDTO;
import banquemisr.challenge05.techExercise.repository.UserRepository;
import banquemisr.challenge05.techExercise.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtService;
    private final UserMapper userMapper;

    @Override
    public void signup(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new UserRegistrationException("Email is already in use");
        }

        User user = userMapper.toUserEntity(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        log.info("After save create user timestamp: " + user.getCreatedAt());
        log.info("After save create user timestamp: " + user.getUpdatedAt());
        userRepository.save(user);
        log.info("User registered successfully: {}", user.getUsername());
    }

    @Override
    public LoginResponseDTO authenticate(UserLoginDTO userLoginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword()
                    )
            );

            User authenticatedUser = userRepository.findByEmail(userLoginDTO.getUsername())
                    .orElseThrow(() -> new UserLoginException("Invalid username or password"));

            String jwtToken = jwtService.generateToken(authenticatedUser);
            return new LoginResponseDTO(jwtToken);

        } catch (Exception e) {
            log.error("Authentication failed for user: {}", userLoginDTO.getUsername(), e);
            throw new UserLoginException("Authentication failed");
        }
    }
}
