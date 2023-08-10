package br.com.kitchen.club.controller;

import br.com.kitchen.club.entity.enums.Uf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EnderecosController {

    /**
     * Verificação dos estados disponíveis no sistema para cadastrar um novo usuário
     *
     * @return ResponseEntity<List<String>>
     * @version 1.0.0
     * @auhor Davi Visintainer
     * */
    @GetMapping
    public ResponseEntity<List<String>> buscarEstados() {
        Uf[] estados = Uf.values();
        var resposta = Arrays.stream(estados)
                .map(Uf::nome)
                .toList();
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
