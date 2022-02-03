package com.example.springboot_with_kotlin.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageController {

    @GetMapping("/main")
    fun main() : String{
        return "main.html";
    }
}