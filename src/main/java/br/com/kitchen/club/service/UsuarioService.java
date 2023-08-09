package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.mapper.UsuarioMapper;
import br.com.kitchen.club.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public void cadastrarNovoUsuario(CadastroRequest cadastro) throws ParametroException {
        verificarExistênciaUsuario(cadastro);
        validarSenha(cadastro);
        validarCep(cadastro);
    }

    private void verificarExistênciaUsuario(CadastroRequest cadstro) throws ParametroException {
        var usuarioCadastrado = usuarioRepository.findByUsuario(cadstro.username().toLowerCase());
        if (usuarioCadastrado.isPresent())
            throw new ParametroException("Usuário já está cadastrado no sistema");
        logger.info("USUÁRIO NÃO EXISTE AINDA NO SISTEMA");
    }

    private void validarSenha(CadastroRequest cadastro) throws ParametroException {
        if (!cadastro.senha().equals(cadastro.confSenha()))
            throw new ParametroException("Senhas informadas não conferem");
        logger.info("SENHAS COMBINAM");
    }

    private void validarCep(CadastroRequest cadastro) {
        logger.info("VALIDAÇÃO CEP");
        //TODO: consultar API de validação de CEP (https://www.gov.br/conecta/catalogo/apis/cep-codigo-de-enderecamento-postal/swagger-json/swagger_view)
    }

    @Override
    public JpaRepository getRepository() {
        return usuarioRepository;
    }
}
