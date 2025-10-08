package org.amanda.timecapsule.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.amanda.timecapsule.model.TimeCapsule;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class MailService {

    public final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendConfirmationMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("visioncapsule.project@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    public void sendCapsuleMail(TimeCapsule timeCapsule) throws MessagingException {
        Context context = new Context();
        context.setVariable("createdDate", timeCapsule.getCreatedAt());
        context.setVariable("message", timeCapsule.getMessage());

        String htmlContent = templateEngine.process("vision-mail-template.html", context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(timeCapsule.getEmail());
        helper.setSubject("Your Vision Capsule");
        helper.setText(htmlContent, true);

        javaMailSender.send(message);

    }
}
