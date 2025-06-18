package Atividade._FBS.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import model.CarrinhoItem;


@Controller
public class CarrinhoController {
    @GetMapping("/carrinho")
    public String mostrarCarrinho(Model model, HttpSession session) {
        List<CarrinhoItem> itens = (List<CarrinhoItem>) session.getAttribute("itens");
        if (itens == null) {
            itens = new ArrayList<>();
        }
        double frete = 12.50;
        model.addAttribute("frete", frete);
        double total = itens.stream().mapToDouble(i -> i.getPreco() * i.getQuantidade()).sum() + frete;
        model.addAttribute("total", total);
        model.addAttribute("itens", itens);
        return "carrinho";
    }

    @PostMapping("/adicionar-carrinho")
    public String adicionarCarrinho(
            @RequestParam String nome,
            @RequestParam String imagem,
            @RequestParam String tamanho,
            @RequestParam double preco,
            @RequestParam(defaultValue = "1") int quantidade,
            HttpSession session
    ) {
        List<CarrinhoItem> itens = (List<CarrinhoItem>) session.getAttribute("itens");
        if (itens == null) {
            itens = new ArrayList<>();
        }
        String nomeCompleto = nome + " " + tamanho;
        itens.add(new CarrinhoItem(nomeCompleto, imagem, quantidade, preco));
        session.setAttribute("itens", itens);
        return "redirect:/carrinho";
    }
}
