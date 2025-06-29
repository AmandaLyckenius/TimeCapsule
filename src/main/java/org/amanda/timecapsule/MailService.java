package org.amanda.timecapsule;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    public final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("visioncapsule.project@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}
