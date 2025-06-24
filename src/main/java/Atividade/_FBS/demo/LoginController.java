package Atividade._FBS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import model.Usuario;
import service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        if (usuarioService.autenticar(usuario.getEmail(), usuario.getSenha())) {
            // Login bem-sucedido
            Usuario usuarioLogado = usuarioService.buscarPorEmail(usuario.getEmail()).get();
            session.setAttribute("usuarioLogado", usuarioLogado);
            return "redirect:/produtos";
        } else {
            // Login falhou
            model.addAttribute("erro", "Email ou senha incorretos!");
            model.addAttribute("usuario", new Usuario());
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}