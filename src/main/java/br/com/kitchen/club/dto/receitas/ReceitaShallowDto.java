package br.com.kitchen.club.dto.receitas;

import br.com.kitchen.club.dto.itensReceita.ItensReceitaShallowDto;

import java.util.List;

public record ReceitaShallowDto(
        String nomeReceita,
        List<ItensReceitaShallowDto> itensReceita) {
}
