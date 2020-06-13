package org.timo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(value = "timo")
    public String tmpl(Model model){
        model.addAttribute("id", 1);
        model.addAttribute("name", "지용호");
        model.addAttribute("nickname","지돌스타");
        model.addAttribute("photo", "images/timo.jpeg");
        return "test1";
    }
}
