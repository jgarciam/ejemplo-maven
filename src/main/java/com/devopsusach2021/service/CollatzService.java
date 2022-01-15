package com.devopsusach2021.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CollatzService {

    public List<Integer> obtieneResultadoCollatz(int inicio) {
        List<Integer> resultados = new ArrayList<>();
        while (inicio != 1) {
            log.debug(" {}", inicio);
            if ((inicio & 1) == 1) {
                inicio = 3 * inicio + 1;
            } else {
                inicio = inicio / 2;
            }
            resultados.add(inicio);
        }
        return resultados;
    }
}
