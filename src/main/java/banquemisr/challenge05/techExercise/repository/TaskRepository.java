package banquemisr.challenge05.techExercise.repository;

import banquemisr.challenge05.techExercise.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :now AND :oneHourFromNow AND t.status IN ('TODO', 'IN_PROGRESS')")
    List<Task> findTasksWithUpcomingDeadlineRange(LocalDateTime now, LocalDateTime oneHourFromNow);


}

