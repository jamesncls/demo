package Atividade._FBS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import model.Pedido;
import model.Usuario;
import service.PedidoService;

@Controller
public class PedidoSucessoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedido-sucesso")
    public String pedidoSucesso(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        try {
            // Criar pedido e esvaziar carrinho
            Pedido pedido = pedidoService.criarPedido(usuarioLogado, 12.50);
            model.addAttribute("pedido", pedido);
        } catch (Exception e) {
            // Se carrinho estiver vazio, redirecionar
            return "redirect:/carrinho";
        }
        
        return "pedido-sucesso";
    }
}