package service;

import model.PasswordResetToken;
import model.Usuario;
import repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailService emailService;
    
    @Transactional
    public void solicitarRecuperacaoSenha(String email) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // Remover tokens anteriores do usuário
            tokenRepository.deleteByUsuario(usuario);
            
            // Criar novo token
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
            
            PasswordResetToken resetToken = new PasswordResetToken(token, usuario, expiryDate);
            tokenRepository.save(resetToken);
            
            // Enviar email
            emailService.enviarEmailRecuperacaoSenha(email, token);
        }
        // Não revelar se o email existe ou não por segurança
    }
    
    public boolean validarToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        
        if (tokenOpt.isPresent()) {
            PasswordResetToken resetToken = tokenOpt.get();
            return !resetToken.isUsed() && !resetToken.isExpired();
        }
        
        return false;
    }
    
    @Transactional
    public boolean redefinirSenha(String token, String novaSenha) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        
        if (tokenOpt.isPresent()) {
            PasswordResetToken resetToken = tokenOpt.get();
            
            if (!resetToken.isUsed() && !resetToken.isExpired()) {
                // Atualizar senha do usuário
                usuarioService.atualizarSenha(resetToken.getUsuario(), novaSenha);
                
                // Marcar token como usado
                resetToken.setUsed(true);
                tokenRepository.save(resetToken);
                
                return true;
            }
        }
        
        return false;
    }
}
