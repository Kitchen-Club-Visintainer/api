package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.itensReceita.ItensReceitaDto;
import br.com.kitchen.club.entity.ItensReceita;
import br.com.kitchen.club.entity.Receita;
import br.com.kitchen.club.entity.enums.MedidasCulinarias;
import br.com.kitchen.club.repository.ItensReceitaRepository;
import br.com.kitchen.club.repository.ReceitaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItensReceitaService extends BaseService<ItensReceita> {

    private final IngredientesService ingredientesService;
    private final ItensReceitaRepository repository;
    private final ReceitaRepository receitaRepository;

    public ItensReceitaService(RestClient restClient,
                               IngredientesService ingredientesService,
                               ItensReceitaRepository repository,
                               ReceitaRepository receitaRepository) {
        super(restClient);
        this.ingredientesService = ingredientesService;
        this.repository = repository;
        this.receitaRepository = receitaRepository;
    }

    public ItensReceita dtoToEntity(ItensReceitaDto dto, ItensReceita itensReceita) {
        itensReceita.setQuantidade(Float.parseFloat(dto.quantidade()));
        itensReceita.setUnidadeMedida(MedidasCulinarias.valueOf(dto.unidadeMedida()));

        ingredientesService.buscarIngredienteCadastrado(dto.ingredientes())
                .ifPresentOrElse(itensReceita::setIngredientes, () -> {
                    throw new ParametroException("Ingrediente informado não foi encontrado");
                });
        return itensReceita;
    }

    public void apagarItensDaReceita(Receita receita) {
        repository.findByReceita(receita).forEach(this::delete);
        receita.setItensReceitas(new ArrayList<>());
        receitaRepository.save(receita);
    }

    @Override
    public JpaRepository<ItensReceita, Long> getRepository() {
        return repository;
    }

    @Override
    public void validateUser(String username) {

    }
}
