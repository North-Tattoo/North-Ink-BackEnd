package school.sptech.northink.projetonorthink.api.controller.estilo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstiloService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;

import java.util.List;

@RestController
@RequestMapping("/estilos")
public class EstiloController {

    @Autowired
    private EstiloService estiloService;

    /*@GetMapping("/mais-cadastrado")
    public ResponseEntity<Estilo> findEstiloMaisCadastrado() {
        Estilo estilo = estiloService.findEstiloMaisCadastrado();

        return new ResponseEntity<>(estilo, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<EstiloListagemDto>> listarTodos() {
        List<EstiloListagemDto> estilos = estiloService.listarEstilos();
        return new ResponseEntity<>(estilos, HttpStatus.OK);
    }

    // faça um getmapping para retornar todos os estilos cadastrados
}

