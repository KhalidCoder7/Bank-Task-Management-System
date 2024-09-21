package banquemisr.challenge05.techExercise.mapper;

import banquemisr.challenge05.techExercise.model.User;
import banquemisr.challenge05.techExercise.payload.userDto.request.UserRegistrationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserRegistrationDTO registrationDTO);
}
