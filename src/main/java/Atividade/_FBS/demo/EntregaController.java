package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.Entrega;

@Controller
public class EntregaController {

    @GetMapping("/entrega")
    public String mostrarEntrega(Model model) {
        model.addAttribute("entrega", new Entrega());
        return "entrega";
    }

    @PostMapping("/entrega")
    public String processarEntrega(@ModelAttribute Entrega entrega, Model model) {
        // Aqui você pode salvar/processar os dados e redirecionar
        // return "redirect:/pagamento";
        return "entrega";
    }
}