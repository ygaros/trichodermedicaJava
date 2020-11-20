package jureczko.page.web.error;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageNotFoundController implements ErrorController {

    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "forward:/";
    }
}
