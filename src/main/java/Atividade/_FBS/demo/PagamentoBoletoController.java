package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.PagamentoBoleto;

@Controller
public class PagamentoBoletoController {

    @GetMapping("/pagamento-boleto")
    public String mostrarBoleto(Model model) {
        model.addAttribute("pagamentoBoleto", new PagamentoBoleto());
        return "pagamento-boleto";
    }

    @PostMapping("/pagamento-boleto")
    public String processarBoleto(@ModelAttribute PagamentoBoleto pagamentoBoleto, Model model) {
        // Aqui você pode salvar ou processar os dados do boleto
        // Exemplo: gerar boleto, enviar por email, etc.
        return "pagamento-boleto";
    }
}