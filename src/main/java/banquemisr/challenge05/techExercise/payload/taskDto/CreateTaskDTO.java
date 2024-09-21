package banquemisr.challenge05.techExercise.payload.taskDto;

import banquemisr.challenge05.techExercise.model.enums.TaskPriority;
import banquemisr.challenge05.techExercise.model.enums.TaskStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters long")
    private String description;

    @NotNull(message = "Priority is mandatory")
    private TaskPriority priority;

    @NotNull(message = "Due date is mandatory")
    private LocalDateTime dueDate;

    @NotNull(message = "Status is mandatory")
    private TaskStatus status;
}