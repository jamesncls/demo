package Atividade._FBS.demo;

import model.PagamentoPix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagamentoPixController {

    @GetMapping("/pagamento-pix")
    public String mostrarPix(Model model) {
        PagamentoPix pagamentoPix = new PagamentoPix(
            "IUDFE787S8BTE0IRMG=0923574RFVT/6Q34CTIQ39=T8NG4IVTGRGQGHQHJOJDPCOOKJ'L",
            "/img/qrcode-pix.png"
        );
        model.addAttribute("pagamentoPix", pagamentoPix);
        return "pagamento-pix";
    }
}