package jureczko.page.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectorController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String getRoot(){
        return "forward:/";
    }

 }
