package br.com.kitchen.club.bases;

import java.util.List;
import java.util.Optional;

public interface ServiceContract<Entity, Filter, ShallowDto> {

    public Entity cadastrarEntidade(Filter filter, String currentUser);

    public Entity atualizarEntidade();

    public Filter buscarEntidadePorId(Long id);

    public List<ShallowDto> buscarTodosEntidade();

}
