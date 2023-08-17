package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.dto.request.IngredientesRequest;
import br.com.kitchen.club.entity.Ingredientes;
import br.com.kitchen.club.entity.enums.GrupoAlimentar;
import br.com.kitchen.club.repository.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IngredientesService extends BaseService {

    @Autowired
    private IngredientesRepository repository;

    public IngredientesService() {
        super(restClient);
    }

    public ResponseEntity<Ingredientes> consultarTodosIngredientes(){

        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<String> consultarIngredientes(){

        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Ingredientes> cadastrarIngrediente(IngredientesRequest request) {
        var ingredientes = new Ingredientes();
        ingredientes.setNome(request.nome());
        ingredientes.setValorNutricional(request.valorNutricional());
        if (Objects.nonNull(request.grupoAlimentar()))
            ingredientes.setGrupoAlimentar(GrupoAlimentar.valueOf(request.grupoAlimentar()));
        repository.save(ingredientes);

        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }


    @Override
    public JpaRepository getRepository() {
        return repository;
    }


}
