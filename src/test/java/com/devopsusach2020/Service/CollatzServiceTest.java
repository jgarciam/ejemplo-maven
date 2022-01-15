package com.devopsusach2020.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CollatzServiceTest {

    @Spy
    private CollatzService collatzService;

    @Test
    void obtieneResultadoCollatz() {
        List<Integer> integers = collatzService.obtieneResultadoCollatz(10);
        assertNotNull(integers);
        assertEquals(6,integers.size());
    }
}