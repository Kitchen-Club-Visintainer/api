package br.com.kitchen.club.mapper;

import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.dto.itensReceita.ItensReceitaDto;
import br.com.kitchen.club.entity.ItensReceita;
import br.com.kitchen.club.entity.enums.MedidasCulinarias;
import br.com.kitchen.club.service.IngredientesService;
import org.springframework.stereotype.Component;

@Component
public class ItensReceitaMapper {

    private final IngredientesService ingredientesService;

    public ItensReceitaMapper(IngredientesService ingredientesService) {
        this.ingredientesService = ingredientesService;
    }

    public ItensReceita toEntity(ItensReceitaDto itensDto) {
        var item = new ItensReceita();
        ingredientesService.buscarIngredienteCadastrado(itensDto.ingredientes())
                .ifPresentOrElse(item::setIngredientes,
                        () -> {throw new ParametroException("Ingrediente " + itensDto.ingredientes() + " não foi encontrado");
                });
        item.setQuantidade(convertToInteger(itensDto));

        var medida = MedidasCulinarias.valueOf(itensDto.unidadeMedida());
        item.setUnidadeMedida(medida);

        return item;
    }

    private Float convertToInteger(ItensReceitaDto itensDto) {
        try {
            return Float.parseFloat(itensDto.quantidade());
        } catch (Exception e) {
            throw new ParametroException("A quantidade informada do ingrediente " + itensDto.ingredientes() + " não pode ser convertida para número");
        }
    }
}
