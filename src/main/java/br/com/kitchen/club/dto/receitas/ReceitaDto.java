package br.com.kitchen.club.dto.receitas;

import br.com.kitchen.club.dto.itensReceita.ItensReceitaDto;

import java.util.List;

public record ReceitaDto(
        String nomeReceita,
        List<Integer> livroReceitaId,
        List<ItensReceitaDto> itensReceitaDtos) {
}
