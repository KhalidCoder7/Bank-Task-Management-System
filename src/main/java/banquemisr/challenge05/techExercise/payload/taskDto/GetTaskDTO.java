package banquemisr.challenge05.techExercise.payload.taskDto;

import banquemisr.challenge05.techExercise.model.enums.TaskPriority;
import banquemisr.challenge05.techExercise.model.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskDTO {

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private Long userId;
}
