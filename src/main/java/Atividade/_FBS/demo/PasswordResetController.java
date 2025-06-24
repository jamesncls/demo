package Atividade._FBS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.PasswordResetService;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/esqueci-senha")
    public String mostrarEsqueciSenha() {
        return "esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    public String processarEsqueciSenha(@RequestParam String email, Model model) {
        try {
            passwordResetService.solicitarRecuperacaoSenha(email);
            model.addAttribute("sucesso", "Se o email estiver cadastrado, você receberá as instruções para recuperação.");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar solicitação. Tente novamente.");
        }
        return "esqueci-senha";
    }

    @GetMapping("/redefinir-senha")
    public String mostrarRedefinirSenha(@RequestParam String token, Model model) {
        if (passwordResetService.validarToken(token)) {
            model.addAttribute("token", token);
            return "redefinir-senha";
        } else {
            model.addAttribute("erro", "Token inválido ou expirado.");
            return "login";
        }
    }

    @PostMapping("/redefinir-senha")
    public String processarRedefinirSenha(@RequestParam String token, 
                                        @RequestParam String novaSenha, 
                                        @RequestParam String confirmarSenha, 
                                        Model model) {
        if (!novaSenha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            model.addAttribute("token", token);
            return "redefinir-senha";
        }

        if (novaSenha.length() < 6) {
            model.addAttribute("erro", "A senha deve ter pelo menos 6 caracteres.");
            model.addAttribute("token", token);
            return "redefinir-senha";
        }

        if (passwordResetService.redefinirSenha(token, novaSenha)) {
            model.addAttribute("sucesso", "Senha redefinida com sucesso! Faça login com sua nova senha.");
            return "login";
        } else {
            model.addAttribute("erro", "Token inválido ou expirado.");
            return "login";
        }
    }
}

