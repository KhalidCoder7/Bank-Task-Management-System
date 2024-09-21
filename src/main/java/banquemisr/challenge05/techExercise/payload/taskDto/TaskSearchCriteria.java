package banquemisr.challenge05.techExercise.payload.taskDto;

import banquemisr.challenge05.techExercise.model.enums.TaskPriority;
import banquemisr.challenge05.techExercise.model.enums.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchCriteria {
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDateTime dueDateFrom;
    private LocalDateTime dueDateTo;
    private String sortBy;
    private int page;
    private int size;
}
