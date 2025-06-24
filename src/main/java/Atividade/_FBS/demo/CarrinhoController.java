package Atividade._FBS.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import model.CarrinhoItem;
import model.Usuario;
import service.CarrinhoService;

@Controller
public class CarrinhoController {
    
    @Autowired
    private CarrinhoService carrinhoService;
    
    @GetMapping("/carrinho")
    public String mostrarCarrinho(Model model, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        List<CarrinhoItem> itens = carrinhoService.buscarItensCarrinho(usuarioLogado);
        double frete = 12.50;
        double subtotal = carrinhoService.calcularTotal(usuarioLogado);
        double total = subtotal + frete;
        
        model.addAttribute("itens", itens);
        model.addAttribute("frete", frete);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        
        return "carrinho";
    }

    @PostMapping("/remover-carrinho")
    public String removerCarrinho(@RequestParam Long itemId, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        carrinhoService.removerItem(itemId);
        return "redirect:/carrinho";
    }
    
    @PostMapping("/atualizar-quantidade")
    public String atualizarQuantidade(@RequestParam Long itemId, @RequestParam int quantidade, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        carrinhoService.atualizarQuantidade(itemId, quantidade);
        return "redirect:/carrinho";
    }
    
    @PostMapping("/adicionar-carrinho")
    public String adicionarCarrinho(
            @RequestParam String nome,
            @RequestParam String imagem,
            @RequestParam String tamanho,
            @RequestParam double preco,
            HttpSession session
    ) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        String nomeCompleto = nome + " " + tamanho;
        carrinhoService.adicionarItem(usuarioLogado, nomeCompleto, imagem, preco);
        
        return "redirect:/carrinho";
    }
}
