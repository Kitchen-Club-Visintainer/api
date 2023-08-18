package br.com.kitchen.club.service;

import br.com.kitchen.club.bases.BaseService;
import br.com.kitchen.club.config.Util;
import br.com.kitchen.club.config.webclient.RestClient;
import br.com.kitchen.club.dto.response.ConsultaCepResponse;
import br.com.kitchen.club.entity.Enderecos;
import br.com.kitchen.club.repository.EnderecosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnderecosService extends BaseService<Enderecos> {

    @Value("${api.cep.endpoint:null}")
    private String API_CEP;

    private final EnderecosRepository enderecosRepository;


    public EnderecosService(RestClient restClient, EnderecosRepository enderecosRepository) {
        super(restClient);
        this.enderecosRepository = enderecosRepository;
    }

    public Optional<ConsultaCepResponse> procurarCep(String cep) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put("cep", cep);

        var requisicao = restClient.fazerRequest(API_CEP, "/{cep}/json/", HttpMethod.GET, params, null);
        if (Objects.isNull(requisicao))
            return Optional.empty();
        var json = Util.toJson(requisicao);
        return Optional.of(new ObjectMapper().readValue(json, ConsultaCepResponse.class));
    }

    @Override
    public JpaRepository getRepository() {
        return enderecosRepository;
    }
}
