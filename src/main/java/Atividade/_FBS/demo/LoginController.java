package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.Usuario;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@ModelAttribute Usuario usuario, Model model) {
        // Aqui você pode adicionar lógica de autenticação
        // Exemplo: se email e senha forem válidos, redireciona para produtos
        // if (usuario.getEmail().equals("teste@teste.com") && usuario.getSenha().equals("123")) {
        //     return "redirect:/produtos";
        // }
        // model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }
}