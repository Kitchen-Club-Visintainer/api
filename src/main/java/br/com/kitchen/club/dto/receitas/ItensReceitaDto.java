package br.com.kitchen.club.dto.receitas;

public record ItensReceitaDto(
        String ingredientes,
        String quantidade,
        String unidadeMedida,
        Integer livroReceitaId) {
}
