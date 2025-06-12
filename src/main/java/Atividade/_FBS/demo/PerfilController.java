package Atividade._FBS.demo;

import model.Perfil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        // Simule buscar o usuário logado (substitua pela sua lógica real)
        Perfil perfil = new Perfil("João Silva", "joao@email.com", "12345678900");
        model.addAttribute("usuario", perfil);
        return "perfil";
    }

    @PostMapping("/perfil")
    public String atualizarPerfil(@ModelAttribute Perfil perfil, Model model) {
        // Aqui você pode salvar as alterações do usuário
        model.addAttribute("usuario", perfil);
        model.addAttribute("mensagem", "Perfil atualizado com sucesso!");
        return "perfil";
    }
}