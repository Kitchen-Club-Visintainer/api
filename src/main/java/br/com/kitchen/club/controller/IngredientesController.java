package br.com.kitchen.club.controller;

import br.com.kitchen.club.entity.Ingredientes;
import br.com.kitchen.club.repository.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {

    @Autowired
    private IngredientesRepository repository;

    @GetMapping
    public Page<Ingredientes> listarTodosIngredientes(){
        Page<Ingredientes> ingredientes = (Page<Ingredientes>) repository.findAll();
        return ingredientes;
    }

}
