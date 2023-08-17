package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.entity.enums.Uf;
import br.com.kitchen.club.mapper.UsuarioMapper;
import br.com.kitchen.club.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EnderecosService enderecosService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          RestClient restClient,
                          EnderecosService enderecosService) {
        super(restClient);
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.enderecosService = enderecosService;
    }

    public void cadastrarNovoUsuario(CadastroRequest cadastro) throws ParametroException {
        verificarExistenciaUsuario(cadastro);
        validarSenha(cadastro);
        validarCep(cadastro);

        var usuario = usuarioMapper.dtoToEntity(cadastro);
        save(usuario);
    }

    private void verificarExistenciaUsuario(CadastroRequest cadstro) throws ParametroException {
        var usuarioCadastrado = usuarioRepository.findByUsuario(cadstro.username());
        if (usuarioCadastrado.isPresent())
            throw new ParametroException("Usuário já está cadastrado no sistema");
        logger.info("USUÁRIO NÃO EXISTE AINDA NO SISTEMA");
    }

    private void validarSenha(CadastroRequest cadastro) throws ParametroException {
        if (!cadastro.senha().equals(cadastro.confSenha()))
            throw new ParametroException("Senhas informadas não conferem");
        logger.info("SENHAS COMBINAM");
    }

    private void validarCep(CadastroRequest cadastro) throws ParametroException {
        logger.info("VALIDAÇÃO CEP");
        try {
            var consulta = enderecosService.procurarCep(cadastro.cep());
            if (consulta.isPresent()) {
                if (!consulta.get().uf().equals(Uf.fromUF(cadastro.uf()).sigla()))
                    throw new ParametroException("UF informada não é válida");
            }
        } catch (JsonProcessingException e) {
            throw new ParametroException("Erro na validação do CEP");
        }
    }

    @Override
    public JpaRepository getRepository() {
        return usuarioRepository;
    }
}
