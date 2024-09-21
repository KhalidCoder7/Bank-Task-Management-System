package banquemisr.challenge05.techExercise.controller;

import banquemisr.challenge05.techExercise.model.enums.TaskPriority;
import banquemisr.challenge05.techExercise.model.enums.TaskStatus;
import banquemisr.challenge05.techExercise.payload.taskDto.CreateTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.GetTaskDTO;
import banquemisr.challenge05.techExercise.payload.taskDto.TaskSearchCriteria;
import banquemisr.challenge05.techExercise.payload.taskDto.UpdateTaskDTO;
import banquemisr.challenge05.techExercise.services.TaskService.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("${api.prefix.tasks}")
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateTaskDTO createTaskDTO) {
        log.info("Received request to create a new task: {}", createTaskDTO.getTitle());
        taskService.createTask(createTaskDTO);
        log.info("Task created successfully: {}", createTaskDTO.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO updateTaskDTO) {
        log.info("Received request to update task with id: {}", id);
        UpdateTaskDTO updatedTaskDTO = taskService.updateTask(id, updateTaskDTO);
        log.info("Task with id: {} updated successfully", id);
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @GetMapping
    public ResponseEntity<Page<GetTaskDTO>> getAllTasks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "priority", required = false) TaskPriority priority,
            @RequestParam(value = "status", required = false) TaskStatus status,
            @RequestParam(value = "dueDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDateFrom,
            @RequestParam(value = "dueDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDateTo,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        TaskSearchCriteria criteria = TaskSearchCriteria.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .status(status)
                .dueDateFrom(dueDateFrom)
                .dueDateTo(dueDateTo)
                .sortBy(sortBy)
                .page(page)
                .size(size)
                .build();

        Page<GetTaskDTO> tasks = taskService.getAllTasks(criteria);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskDTO> getTaskById(@PathVariable Long id) {
        log.info("Received request to retrieve task with id: {}", id);
        GetTaskDTO taskDTO = taskService.getTaskById(id);
        log.info("Task with id: {} retrieved successfully", id);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("Received request to delete task with id: {}", id);
        taskService.deleteTask(id);
        log.info("Task with id: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}