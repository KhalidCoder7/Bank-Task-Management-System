package banquemisr.challenge05.techExercise.EmailUtils;

import banquemisr.challenge05.techExercise.model.Task;
import banquemisr.challenge05.techExercise.model.User;
import banquemisr.challenge05.techExercise.repository.TaskRepository;
import banquemisr.challenge05.techExercise.services.EmailService.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@AllArgsConstructor
@Slf4j
public class EmailReminderJob implements Job {

    private final TaskRepository taskRepository;
    private final EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Email reminder job started.");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourFromNow = now.plusHours(1);
        List<Task> tasks = taskRepository.findTasksWithUpcomingDeadlineRange(now, oneHourFromNow);
        log.info("Fetched tasks: {}", tasks);

        log.info("Email reminder job started at {}", now);
        AtomicInteger emailSentCount = new AtomicInteger();

        tasks.forEach(task -> {
            User assignedUser = task.getAssignedTo();
            if (assignedUser != null && assignedUser.getEmail() != null) {
                String subject = "Task Deadline Reminder";
                String body = "Your task '" + task.getTitle() + "' is due soon: " + task.getDueDate();
                emailService.sendEmail(assignedUser.getEmail(), subject, body);
                emailSentCount.getAndIncrement();
            } else {
                log.warn("Task '{}' does not have an assigned user or email!", task.getTitle());
            }
        });

        log.info("Email reminder job completed. {} emails sent.", emailSentCount);

    }
}


