package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.CadastroRequest;
import br.com.kitchen.club.dto.usuario.UsuarioShallowDto;
import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.mapper.UsuarioMapper;
import br.com.kitchen.club.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario> {

    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;
    private final EnderecosService enderecosService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          RestClient restClient,
                          EnderecosService enderecosService) {
        super(restClient);
        this.repository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.enderecosService = enderecosService;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return findAll();
    }

    public Usuario buscarUsuario(Long id) throws ParametroException {
        var usuarioOpt = findById(id);
        if (usuarioOpt.isEmpty())
            throw new ParametroException("ID do usuário não foi encontrado");
        return usuarioOpt.get();
    }

    public Optional<Usuario> atualizarUsuario(UsuarioShallowDto usuarioShallowDto) throws ParametroException {
        var usuario = buscarUsuarioPeloUsername(usuarioShallowDto.usuario());
        if (usuario.isEmpty())
            throw new ParametroException("Usuario " + usuarioShallowDto.usuario() + " não encontrado!");

        BeanUtils.copyProperties(usuarioShallowDto, usuario.get());
        return usuario;
    }

    public void cadastrarNovoUsuario(CadastroRequest cadastro) throws ParametroException {
        verificarExistenciaUsuario(cadastro);
        validarSenha(cadastro);
        enderecosService.validarCep(cadastro);

        var usuario = usuarioMapper.dtoToEntity(cadastro);
        save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPeloUsername(String username) {
        return repository.findByUsuario(username);
    }

    private void verificarExistenciaUsuario(CadastroRequest cadastro) throws ParametroException {
        var usuarioCadastrado = buscarUsuarioPeloUsername(cadastro.username());
        if (usuarioCadastrado.isPresent()) {
            throw new ParametroException("Usuário já está cadastrado no sistema");
        }
        logger.info("USUÁRIO NÃO EXISTE AINDA NO SISTEMA");
    }

    private void validarSenha(CadastroRequest cadastro) throws ParametroException {
        if (!cadastro.senha().equals(cadastro.confSenha()))
            throw new ParametroException("Senhas informadas não conferem");
        logger.info("SENHAS COMBINAM");
    }

    @Override
    public JpaRepository getRepository() {
        return repository;
    }

    @Override
    public void validateUser(String username) {
        verificarExistenciaUsuario(CadastroRequest.of(username));
    }
}
