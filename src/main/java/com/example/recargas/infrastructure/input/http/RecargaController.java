package com.example.recargas.infrastructure.input.http;

import com.example.recargas.application.recarga.comando.RecargaComprar;
import com.example.recargas.application.recarga.comando.RecargaVender;
import com.example.recargas.application.recarga.consulta.RecargaListar;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.recargas.application.recarga.comando.RecargaCrear;
import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;

import java.util.List;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class RecargaController {

    private final RecargaListar recargaListar;
    private final RecargaCrear recargaCrear;
    private final RecargaComprar recargaComprar;
    private final RecargaVender recargaVender;

    public RecargaController(RecargaCrear recargaCrear, RecargaListar recargaListar, RecargaComprar recargaComprar, RecargaVender recargaVender ) {
        this.recargaListar = recargaListar;
        this.recargaCrear = recargaCrear;
        this.recargaComprar = recargaComprar;
        this.recargaVender = recargaVender;
    }

    @GetMapping("/listar")
    public List<RecargaRespuestaDto> listar() {
        return recargaListar.ejecutar();
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public RecargaRespuestaDto create(@RequestBody RecargaCrearDto recargaCrearDto) {
        return recargaCrear.ejecutar(recargaCrearDto);
    }

    @PostMapping("/comprar")
    @ResponseStatus(HttpStatus.OK)
    public RecargaRespuestaDto compra(@RequestBody RecargaCrearDto recargaCrearDto) {
        return recargaComprar.ejecutar(recargaCrearDto);
    }

    @PostMapping("/vender")
    @ResponseStatus(HttpStatus.OK)
    public RecargaRespuestaDto venta(@RequestBody RecargaCrearDto recargaCrearDto) {
        return recargaVender.ejecutar(recargaCrearDto);
    }

}
