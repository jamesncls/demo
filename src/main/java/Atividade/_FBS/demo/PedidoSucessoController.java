package Atividade._FBS.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoSucessoController {

    @GetMapping("/pedido-sucesso")
    public String pedidoSucesso() {
        return "pedido-sucesso";
    }
}