package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.UsuarioCadastro;

@Controller
public class CadastroController {

     @GetMapping("/cadastro")
    public String mostrarCadastro(Model model) {
        model.addAttribute("usuarioCadastro", new UsuarioCadastro());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioCadastro usuarioCadastro, Model model) {
        // Aqui você pode adicionar lógica para salvar o usuário
        // Exemplo: salvar no banco de dados

        // Após cadastro, redireciona para a tela de produtos
        return "redirect:/produtos";
    }
}