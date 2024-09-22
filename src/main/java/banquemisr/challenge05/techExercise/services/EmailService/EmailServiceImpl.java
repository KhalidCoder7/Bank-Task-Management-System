package banquemisr.challenge05.techExercise.services.EmailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            log.info("Preparing to send email to: {}", to);
            mailSender.send(message);
            log.info("Email sent successfully to {}", to);
        } catch (MailException e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }

}
