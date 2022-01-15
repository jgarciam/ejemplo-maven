package com.devopsusach2021.rest;

import com.devopsusach2021.service.CollatzService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping(path = "/collatz")
public class RestData {

    CollatzService collatzService;

    @GetMapping(path = "/generar", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Integer> generaCollatz(@RequestParam(name = "inicio") Integer inicio) {
        return collatzService.obtieneResultadoCollatz(inicio);
    }
}
