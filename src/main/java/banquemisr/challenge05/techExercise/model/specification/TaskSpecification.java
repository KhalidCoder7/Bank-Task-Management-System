package banquemisr.challenge05.techExercise.model.specification;

import banquemisr.challenge05.techExercise.model.Task;
import banquemisr.challenge05.techExercise.payload.taskDto.TaskSearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class TaskSpecification implements Specification<Task> {

    private final TaskSearchCriteria criteria;

    public TaskSpecification(TaskSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Search by title
        if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
            log.info("Searching for title: {}", criteria.getTitle().toLowerCase());

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + criteria.getTitle().toLowerCase() + "%"));
        }

        // Search by description
        if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + criteria.getDescription().toLowerCase() + "%"));
        }

        // Filter by priority
        if (criteria.getPriority() != null) {
            predicates.add(criteriaBuilder.equal(root.get("priority"), criteria.getPriority()));
        }

        // Filter by status
        if (criteria.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
        }

        // Filter by due date range
        if (criteria.getDueDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), criteria.getDueDateFrom()));
        }

        if (criteria.getDueDateTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), criteria.getDueDateTo()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
