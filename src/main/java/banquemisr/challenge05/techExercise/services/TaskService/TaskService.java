package banquemisr.challenge05.techExercise.services.TaskService;

import banquemisr.challenge05.techExercise.payload.taskDto.CreateTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.GetTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.TaskSearchCriteria;
import banquemisr.challenge05.techExercise.payload.taskDto.UpdateTaskDTO;
import org.springframework.data.domain.Page;

public interface TaskService {
    void createTask(CreateTaskDTO createTaskDTO);
    UpdateTaskDTO updateTask(Long id, UpdateTaskDTO updateTaskDTO);
    Page<GetTaskDTO> getAllTasks(TaskSearchCriteria criteria);
    GetTaskDTO getTaskById(Long id);
    void deleteTask(Long id);

}
