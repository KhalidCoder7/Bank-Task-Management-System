package banquemisr.challenge05.techExercise.mapper;

import banquemisr.challenge05.techExercise.model.Task;
import banquemisr.challenge05.techExercise.payload.taskDto.CreateTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.GetTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.UpdateTaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;



@Mapper(componentModel = "spring")
public interface TaskMapper {
    GetTaskDTO toGetDTO(Task task);

    Task toTaskEntity(CreateTaskDTO createTaskDTO);

    UpdateTaskDTO toUpdateDTO(Task task);
    void updateEntityFromDTO(UpdateTaskDTO dto, @MappingTarget Task entity);

}
