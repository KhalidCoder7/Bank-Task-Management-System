package banquemisr.challenge05.techExercise.services.TaskService;

import banquemisr.challenge05.techExercise.exception.userException.ResourceNotFoundException;
import banquemisr.challenge05.techExercise.mapper.TaskMapper;
import banquemisr.challenge05.techExercise.model.Task;
import banquemisr.challenge05.techExercise.model.specification.TaskSpecification;
import banquemisr.challenge05.techExercise.payload.taskDto.CreateTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.GetTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.TaskSearchCriteria;
import banquemisr.challenge05.techExercise.payload.taskDto.UpdateTaskDTO;
import banquemisr.challenge05.techExercise.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public void createTask(CreateTaskDTO createTaskDTO) {
        Task task = taskMapper.toTaskEntity(createTaskDTO);
        taskRepository.save(task);
        log.info("Task created with ID: {}", task.getId());
    }

    @Override
    public UpdateTaskDTO updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskMapper.updateEntityFromDTO(updateTaskDTO, existingTask);
        Task updatedTask = taskRepository.save(existingTask);
        log.info("Task updated with ID: {}", updatedTask.getId());
        return taskMapper.toUpdateDTO(updatedTask);
    }

    @Override
    public Page<GetTaskDTO> getAllTasks(TaskSearchCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(criteria.getSortBy()));
        Specification<Task> specification = new TaskSpecification(criteria);

        Page<Task> tasks = taskRepository.findAll(specification, pageable);
        log.info("Retrieved {} tasks", tasks.getTotalElements());

        return tasks.map(taskMapper::toGetDTO);
    }

    @Override
    public GetTaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        log.info("Retrieved task with ID: {}", task.getId());
        return taskMapper.toGetDTO(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
        log.info("Deleted task with ID: {}", id);
    }
}
