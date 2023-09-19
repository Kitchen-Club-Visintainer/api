package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.bases.ServiceContract;
import br.com.kitchen.club.config.exception.ParametroException;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.itensReceita.ItensReceitaDto;
import br.com.kitchen.club.dto.itensReceita.ItensReceitaShallowDto;
import br.com.kitchen.club.dto.receitas.ReceitaDto;
import br.com.kitchen.club.dto.receitas.ReceitaShallowDto;
import br.com.kitchen.club.entity.Receita;
import br.com.kitchen.club.mapper.ItensReceitaMapper;
import br.com.kitchen.club.repository.ItensReceitaRepository;
import br.com.kitchen.club.repository.ReceitaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService extends BaseService<Receita> implements ServiceContract<Receita, ReceitaDto, ReceitaShallowDto> {

    private final ReceitaRepository receitaRepository;
    private final UsuarioService usuarioService;
    private final ItensReceitaMapper itensMapper;
    private final LivroReceitaService livroReceitaService;
    private final ItensReceitaRepository itensReceitaRepository;


    protected ReceitaService(RestClient restClient,
                             ReceitaRepository receitaRepository,
                             UsuarioService usuarioService,
                             ItensReceitaMapper itensMapper,
                             LivroReceitaService livroReceitaService,
                             ItensReceitaRepository itensReceitaRepository) {
        super(restClient);
        this.receitaRepository = receitaRepository;
        this.usuarioService = usuarioService;
        this.itensMapper = itensMapper;
        this.livroReceitaService = livroReceitaService;
        this.itensReceitaRepository = itensReceitaRepository;
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

    @Override
    public Receita cadastrarEntidade(ReceitaDto receitaDto, String currentUser) {
        var livroReceita = livroReceitaService.buscarLivroReceita(currentUser);
        //TODO: ler quais são os livros de receitas que serão salvos para cadastrar nos livros solicitados na requisção

        var receita = new Receita(receitaDto.nomeReceita(), livroReceita);
        save(receita);

        receitaDto.itensReceitaDtos()
                .forEach(itensDto -> {
                    var item = itensMapper.toEntity(itensDto);
                    item.setReceita(receita);
                    itensReceitaRepository.save(item);
                });
        return receita;
    }

    @Override
    public Receita atualizarEntidade(ReceitaDto receitaDto, Long id) {
        ReceitaDto receitaCadastradaDto = buscarEntidadePorId(id);
        BeanUtils.copyProperties(receitaDto, receitaCadastradaDto);
//        save(receitaCadastradaDto);
        return null;
    }

    @Override
    public ReceitaDto buscarEntidadePorId(Long id) {
        var receitaDto = receitaRepository.findById(id).map(r -> {
            var itensReceitaDtos = r.getItensReceitas().stream().map(item ->
                    new ItensReceitaDto(
                            item.getIngredientes().getNome(),
                            item.getQuantidade().toString(),
                            item.getUnidadeMedida().getDescricao())).toList();

            var livrosId = r.getLivroReceita().stream()
                    .map(livro -> livro.getId().intValue()).toList();

            return new ReceitaDto(r.getNomeReceita(), livrosId, itensReceitaDtos);
        });
        if (receitaDto.isPresent())
            return receitaDto.get();
        throw new ParametroException("Nenhuma receita foi encontrada com o ID: " + id);
    }

    @Override
    public List<ReceitaShallowDto> buscarTodosEntidade() {
        var receitaList = findAll();
        return receitaList.stream().map(receita -> {
            var itensReceitaShallowDtos = receita.getItensReceitas().stream()
                    .map(item -> new ItensReceitaShallowDto(item.getIngredientes().getNome())).toList();
            return new ReceitaShallowDto(receita.getNomeReceita(), itensReceitaShallowDtos);
        }).toList();
    }
}
