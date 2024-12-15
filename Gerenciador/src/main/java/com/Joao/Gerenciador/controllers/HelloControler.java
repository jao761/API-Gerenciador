package com.Joao.Gerenciador.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hello")
public class HelloControler {

    @GetMapping
    public String OlaMundo() { return "Ola Mundo"; }
}
