package Atividade._FBS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import model.Pedido;
import model.Usuario;
import service.PedidoService;

import java.util.List;

@Controller
public class HistoricoPedidosController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/historico-pedidos")
    public String mostrarHistorico(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        List<Pedido> pedidos = pedidoService.buscarPedidosUsuario(usuarioLogado);
        model.addAttribute("pedidos", pedidos);
        
        return "historico-pedidos";
    }

    @GetMapping("/pedido/{id}")
    public String detalhesPedido(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        
        // Buscar pedido e verificar se pertence ao usuário
        List<Pedido> pedidos = pedidoService.buscarPedidosUsuario(usuarioLogado);
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (pedido == null) {
            return "redirect:/historico-pedidos";
        }
        
        model.addAttribute("pedido", pedido);
        
        return "detalhes-pedido";
    }
}

