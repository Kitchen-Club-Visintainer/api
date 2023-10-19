package br.com.kitchen.club.service;

import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.dto.ingredientes.IngredientesDto;
import br.com.kitchen.club.entity.Ingredientes;
import br.com.kitchen.club.entity.enums.GrupoAlimentar;
import br.com.kitchen.club.repository.IngredientesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class IngredienteServiceTest {

    @InjectMocks
    private IngredientesService ingredientesService;

    @Mock
    private IngredientesRepository ingredientesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCadastrarIngrediente() {
        IngredientesDto ingredientesDto = new IngredientesDto("Alface", "10.0", "VEGETAIS");
        Mockito.when(ingredientesRepository.findByNome("Alface")).thenReturn(Optional.empty());
        Mockito.when(ingredientesRepository.save(any(Ingredientes.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Ingredientes resultado = ingredientesService.cadastrarIngrediente(ingredientesDto);

        assertEquals("Alface", resultado.getNome());
        assertEquals("10.0", resultado.getValorNutricional());
        assertEquals(GrupoAlimentar.VEGETAIS, resultado.getGrupoAlimentar());
    }

    @Test
    void testAtualizarIngrediente() {
        Long id = 1L;
        IngredientesDto ingredientesDto = new IngredientesDto("Tomate", "15.0", "VEGETAIS");
        Ingredientes existente = new Ingredientes("Tomate", "20.0", GrupoAlimentar.VEGETAIS);
        Mockito.when(ingredientesRepository.findById(id)).thenReturn(Optional.of(existente));
        Mockito.when(ingredientesRepository.save(any(Ingredientes.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Ingredientes resultado = ingredientesService.atualizarIngrediente(ingredientesDto, id);

        assertEquals("Tomate", resultado.getNome());
        assertEquals("15.0", resultado.getValorNutricional());
        assertEquals(GrupoAlimentar.VEGETAIS, resultado.getGrupoAlimentar());
    }

    @Test
    void testBuscarIngredienteCadastradoPorId_IngredienteNaoEncontrado() {
        Long id = 1L;
        Mockito.when(ingredientesRepository.findById(id)).thenReturn(Optional.empty());

        try {
            ingredientesService.buscarIngredienteCadastradoPorId(id);
        } catch (ParametroException ex) {
            assertEquals("Nenhum ingrediente encontrado com o ID: 1", ex.getMessage());
        }
    }
}
