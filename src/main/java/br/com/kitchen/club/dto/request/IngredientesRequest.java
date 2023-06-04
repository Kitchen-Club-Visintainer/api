package br.com.kitchen.club.dto.request;

public record IngredientesRequest(
        String nome,
        String valorNutricional,
        String grupoAlimentar) { }
