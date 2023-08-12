package br.com.kitchen.club.mapper;

import br.com.kitchen.club.dto.request.CadastroRequest;
import br.com.kitchen.club.entity.Enderecos;
import br.com.kitchen.club.entity.Usuario;
import br.com.kitchen.club.entity.enums.Uf;
import br.com.kitchen.club.repository.EnderecosRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final EnderecosRepository enderecosRepository;

    public UsuarioMapper(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }

    public Usuario dtoToEntity(CadastroRequest cadastro) {
        var usuario = new Usuario(
                cadastro.nomeCompleto(),
                cadastro.username(),
                cadastro.email(),
                cadastro.senha()
        );
        var endereco = new Enderecos(
                cadastro.cep(),
                cadastro.logradouro(),
                cadastro.complemento(),
                cadastro.numero()
        );
        endereco.setUf(Strings.isNotBlank(cadastro.uf()) ? Uf.fromUF(cadastro.uf()) : null);
        enderecosRepository.save(endereco);
        usuario.setEndereco(endereco);

        //TODO: Colocar o perfil
        return usuario;
    }

}
