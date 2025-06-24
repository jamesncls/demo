package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailRecuperacaoSenha(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@yourdomain.com"); // Altere para o seu email de remetente
        message.setTo(to);
        message.setSubject("Recuperação de Senha - Concept");
        message.setText("Para redefinir sua senha, clique no link:\n\n" +
                        "http://localhost:8080/redefinir-senha?token=" + token + "\n\n" +
                        "Se você não solicitou a redefinição de senha, ignore este email.");
        mailSender.send(message);
    }
}


