package br.com.kitchen.club.service;

import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.dto.CadastroRequest;
import br.com.kitchen.club.dto.usuario.UsuarioShallowDto;
import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.mapper.UsuarioMapper;
import br.com.kitchen.club.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private EnderecosService enderecosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBuscarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.buscarTodosUsuarios();

        assertSame(usuarios, resultado);
    }

    @Test
    void testBuscarUsuario_UsuarioEncontrado() throws ParametroException {
        Long id = 1L;
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuario(id);

        assertSame(usuario, resultado);
    }

    @Test
    void testBuscarUsuario_UsuarioNaoEncontrado() {
        Long id = 1L;
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ParametroException.class, () -> {
            usuarioService.buscarUsuario(id);
        });
    }

    @Test
    void testAtualizarUsuario_UsuarioEncontrado() throws ParametroException {
        var usuarioShallowDto = new UsuarioShallowDto(
                "João da Silva",
                "joao123",
                "joao@example.com",
                true,
                "ADMIN");

        CadastroRequest cadastroRequest = new CadastroRequest(
                usuarioShallowDto.nomeCompleto(),
                usuarioShallowDto.usuario(),
                usuarioShallowDto.email(),
                "novaSenha", // Nova senha
                "novaSenha", // Confirmação da nova senha
                null, // Endereço
                null, // Logradouro
                null, // Complemento
                null, // Número
                null // UF
        );

        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        Mockito.when(usuarioRepository.findByUsuario("joao123")).thenReturn(usuarioOptional);
        Mockito.when(usuarioMapper.dtoToEntity(cadastroRequest)).thenReturn(usuario);

        Optional<Usuario> resultado = usuarioService.atualizarUsuario(usuarioShallowDto);

        assertSame(usuarioOptional.get(), resultado.get());
        assertEquals("joao123", resultado.get().getUsuario());
    }

    @Test
    void testAtualizarUsuario_UsuarioNaoEncontrado() {
        var usuarioShallowDto = new UsuarioShallowDto(
                "João da Silva",
                "joao123",
                "joao@example.com",
                true,
                "ADMIN");

        Mockito.when(usuarioRepository.findByUsuario("joao")).thenReturn(Optional.empty());

        assertThrows(ParametroException.class, () -> {
            usuarioService.atualizarUsuario(usuarioShallowDto);
        });
    }
}
