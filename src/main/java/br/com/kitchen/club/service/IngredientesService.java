package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.ingredientes.IngredientesDto;
import br.com.kitchen.club.dto.ingredientes.IngredientesShallowDto;
import br.com.kitchen.club.entity.Ingredientes;
import br.com.kitchen.club.entity.enums.GrupoAlimentar;
import br.com.kitchen.club.repository.IngredientesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.kitchen.club.config.Util.toCaptalize;

@Service
public class IngredientesService extends BaseService<Ingredientes> {

    private final IngredientesRepository repository;
    private final UsuarioService usuarioService;

    public IngredientesService(RestClient restClient, IngredientesRepository repository, UsuarioService usuarioService) {
        super(restClient);
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public Ingredientes cadastrarIngrediente(IngredientesDto request) {
        var ingredienteCadastrado = buscarIngredienteCadastrado(toCaptalize(request.nome()));
        if (ingredienteCadastrado.isPresent())
            return ingredienteCadastrado.get();

        var ingredientes = new Ingredientes(toCaptalize(request.nome()), request.valorNutricional());
        if (Objects.nonNull(request.grupoAlimentar()))
            ingredientes.setGrupoAlimentar(GrupoAlimentar.valueOf(request.grupoAlimentar()));

        save(ingredientes);
        return ingredientes;
    }

    public Optional<Ingredientes> buscarIngredienteCadastrado(String nomeIngrediente) {
        return repository.findByNome(nomeIngrediente);
    }

    public Ingredientes buscarIngredienteCadastradoPorId(Long id) {
        var ingrediente = repository.findById(id);
        if (ingrediente.isPresent())
            return ingrediente.get();
        throw new ParametroException("Nenhum ingrediente encontrado com o ID: " + id);

    }

    public List<IngredientesShallowDto> buscarTodosIngredientes() {
        var ingredientes = repository.findAll();
        List<IngredientesShallowDto> shallowDto = ingredientes.stream()
                .map(ing -> {
                    return new IngredientesShallowDto(ing.getNome(), ing.getGrupoAlimentar().getDescricao());
                }).toList();
        return shallowDto;
    }

    @Override
    public JpaRepository getRepository() {
        return repository;
    }

    @Override
    public void validateUser(String username) {
        usuarioService.validateUser(username);
    }
}
