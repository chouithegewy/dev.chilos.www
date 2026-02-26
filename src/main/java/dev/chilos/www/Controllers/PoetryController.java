package dev.chilos.www;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class PoetryController {

    @GetMapping("/poetry")
	public String poetry(Model model) {
        return "pages/poetry";
	}

}
