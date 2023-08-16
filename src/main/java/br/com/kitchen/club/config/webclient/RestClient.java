package br.com.kitchen.club.config.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Component
public class RestClient {

    public Object fazerRequest(String baseUrl, String url, HttpMethod httpMethod, Map<String, String> params, Object corpo) {
        WebClient client = construirClient(baseUrl, params);
        return fazerRequest(client, url, httpMethod, corpo);
    }

    public Object fazerRequest(WebClient client, String url, HttpMethod httpMethod, Object corpo) {
        Mono<Object> objectMono = enviarRequisicao(client, url, httpMethod, corpo);
        return objectMono.block();
    }

    public WebClient construirClient(String baseUrl, Map<String, String> params) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .defaultUriVariables(params)
                .build();
    }

    public Mono<Object> enviarRequisicao(WebClient client, String url, HttpMethod httpMethod, Object corpo) {
        return client
                .method(httpMethod)
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Objects.nonNull(corpo) ? corpo : "")
                .exchangeToMono(resp -> resp.bodyToMono(Object.class));
    }

}
