package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository repository;

    public AutorController(AutorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listarAutores(Model model) {

        List<Autor> autores = repository.findAll();
        model.addAttribute("autores", autores);
        return "autores/lista";
    }

    @GetMapping("/novo")
    public String novoAutor(Model model) {

        model.addAttribute("autor", new Autor());

        return "autores/form";
    }

    @PostMapping
    public String cadastrarAutor(@ModelAttribute Autor autor) {

        repository.save(autor);
        return "redirect:/autores";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        repository.deleteById(id);
        return "redirect:/autores";
    }
}
