package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LivroController {

    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/livros")
    public String listarLivros(Model model) {

        List<Livro> livros = repository.findAll();
        model.addAttribute("livros", livros);
        return "livros/lista";
    }

    @GetMapping("/livros/novo")
    public String novoLivro(Model model) {

        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping("/livros")
    public String cadastrarLivro(
            @ModelAttribute Livro livro) {

        repository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/livros/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        repository.deleteById(id);
        return "redirect:/livros";
    }
}
