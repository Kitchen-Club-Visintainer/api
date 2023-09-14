package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.entity.Receita;
import br.com.kitchen.club.repository.ReceitaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService extends BaseService<Receita> {

    private final ReceitaRepository receitaRepository;
    private final UsuarioService usuarioService;

    protected ReceitaService(RestClient restClient, ReceitaRepository receitaRepository, UsuarioService usuarioService) {
        super(restClient);
        this.receitaRepository = receitaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public JpaRepository<Receita, Long> getRepository() {
        return receitaRepository;
    }

    @Override
    public void validateUser(String username) {
        usuarioService.buscarUsuarioPeloUsername(username)
                .orElseThrow(() -> new ParametroException("Usuário não encontrado"));
    }
}
