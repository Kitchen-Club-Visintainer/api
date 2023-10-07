package br.com.kitchen.club.controller;

import br.com.kitchen.club.bases.BaseController;
import br.com.kitchen.club.dto.receitas.ReceitaDto;
import br.com.kitchen.club.dto.receitas.ReceitaShallowDto;
import br.com.kitchen.club.entity.Ingredientes;
import br.com.kitchen.club.entity.Receita;
import br.com.kitchen.club.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaController extends BaseController<Receita, ReceitaDto, ReceitaShallowDto, ReceitaService> {

    private final ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @Override
    protected ReceitaService getEntityService() {
        return service;
    }

    @Override
    public ResponseEntity<List<ReceitaShallowDto>> listAll() {
        var receitaShallowDtos = service.buscarTodosEntidade();
        return new ResponseEntity<>(receitaShallowDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReceitaDto> listById(@PathVariable Long id) {
        var receitaDto = service.buscarEntidadePorId(id);
        return new ResponseEntity<>(receitaDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createNew(@Valid @RequestBody ReceitaDto receitaDto) {
        service.validateUser(getCurrentUser());
        var receita = service.cadastrarEntidade(receitaDto, getCurrentUser());
        return new ResponseEntity<>(receita.toString(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Receita> update(@Valid @RequestBody ReceitaDto receitaDto, @PathVariable Long id) {
        return null;
    }
}
