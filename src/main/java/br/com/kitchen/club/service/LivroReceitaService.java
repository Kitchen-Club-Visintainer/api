package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.exception.ServiceException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.entity.LivroReceita;
import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.repository.LivroReceitaRepository;
import br.com.kitchen.club.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroReceitaService extends BaseService<LivroReceita> {

    private final LivroReceitaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    protected LivroReceitaService(RestClient restClient,
                                  LivroReceitaRepository repository,
                                  UsuarioRepository usuarioRepository,
                                  UsuarioService usuarioService) {
        super(restClient);
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    public List<LivroReceita> buscarLivroReceitaPorUsuario(String username) {
        return usuarioService.buscarUsuarioPeloUsername(username)
                .map(user -> {
                    var livroOpt = repository.findByUsuario(user);
                    if (livroOpt.isPresent() && (!livroOpt.get().isEmpty()))
                        return livroOpt.get();
                    return List.of(criarLivroReceitas(user));
                }).orElseThrow(() -> new ServiceException("Usuário não identificado"));
    }

    public LivroReceita buscarLivroReceitaPorId(Integer livroId) {
        Optional<LivroReceita> livroReceita = findById(Long.parseLong(livroId.toString()));
        if(livroReceita.isPresent()) {
            return livroReceita.get();
        } else {
            throw new ParametroException("ID do Livro de receitas não foi encontrato");
        }
    }

    private LivroReceita criarLivroReceitas(Usuario user) {
        var novoLivro = new LivroReceita();
        novoLivro.setUsuario(user);
        List<LivroReceita> livroReceitas = user.getLivroReceitas();
        livroReceitas.add(novoLivro);
        usuarioRepository.save(user);
        save(novoLivro);
        return novoLivro;
    }

    @Override
    public JpaRepository<LivroReceita, Long> getRepository() {
        return repository;
    }

    @Override
    public void validateUser(String username) {

    }
}