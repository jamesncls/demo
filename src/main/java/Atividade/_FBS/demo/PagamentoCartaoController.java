package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import model.PagamentoCartao;

@Controller
public class PagamentoCartaoController {

    @GetMapping("/pagamento-cartao")
    public String mostrarPagamentoCartao(Model model) {
        model.addAttribute("pagamentoCartao", new PagamentoCartao());
        return "pagamento-cartao";
    }

    @PostMapping("/pagamento-cartao")
    public String processarPagamentoCartao(@ModelAttribute PagamentoCartao pagamentoCartao, Model model) {
        // Aqui você pode processar o pagamento do cartão
        // Exemplo: validar dados, salvar, redirecionar para sucesso, etc.
        // return "redirect:/sucesso";
        return "pagamento-cartao";
    }
}