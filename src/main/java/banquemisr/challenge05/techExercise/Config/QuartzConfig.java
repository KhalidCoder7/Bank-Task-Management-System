package banquemisr.challenge05.techExercise.Config;

import banquemisr.challenge05.techExercise.EmailUtils.EmailReminderJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail emailReminderJobDetail() {
        return JobBuilder.newJob(EmailReminderJob.class)
                .withIdentity("emailReminderJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger emailReminderTrigger(JobDetail emailReminderJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(emailReminderJobDetail)
                .withIdentity("emailReminderTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(1) // Run every 1 hour
                        .repeatForever())
                .build();
    }
}
