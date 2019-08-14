package com.johnny.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HelloController {

    @GetMapping("/hello")
    String sayHello(@RequestParam(defaultValue = 'world',required = false)String name, Model model){
        model.addAttribute('user', name)
        return 'hello'
    }
}
