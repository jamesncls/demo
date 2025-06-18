package Atividade._FBS.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.CarrinhoItem;


@Controller
public class CarrinhoController {
    @GetMapping("/carrinho")
    public String mostrarCarrinho(Model model) {
        List<CarrinhoItem> itens = new ArrayList<>();
        itens.add(new CarrinhoItem("CAMISETA ROXA P", "camiseta-roxa.png", 1, 100.00));
        itens.add(new CarrinhoItem("CAMISETA AZUL M", "camiseta-azul.png", 1, 120.00));

        // ...existing code...
        double frete = 12.50;
        model.addAttribute("frete", frete);
        double total = itens.stream().mapToDouble(i -> i.getPreco() * i.getQuantidade()).sum() + frete;
        model.addAttribute("total", total);
        // ...existing code...

        model.addAttribute("itens", itens);
       /* model.addAttribute("frete", frete);
        model.addAttribute("total", total);
         model.addAttribute("itens", itens);*/

        return "carrinho";
    }
}
