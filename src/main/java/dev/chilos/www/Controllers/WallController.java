package dev.chilos.www;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class WallController {

    @GetMapping("/wall")
	public String home(Model model) {
        model.addAttribute("title", "wall");
        return "pages/wall";
	}

}
