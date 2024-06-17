package school.sptech.northink.projetonorthink.api.controller.estilo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.DashboardService;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstiloService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;

import java.util.List;

@RestController
@RequestMapping("/estilos")
public class EstiloController {

    @Autowired
    private EstiloService estiloService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/mais-cadastrado")
    public ResponseEntity<Estilo> findEstiloMaisCadastrado() {
        Estilo estilo = estiloService.findEstiloMaisCadastrado();

        return new ResponseEntity<>(estilo, HttpStatus.OK);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Estilo>> top3() {
        List<Estilo> top3 = dashboardService.getTop3Estilos();

        if (top3.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(top3);
    }

    @GetMapping("/top1")
    public ResponseEntity<Estilo> buscarPorId() {
        Estilo top1 = dashboardService.findEstiloMaisCadastrado();

        if(top1 == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(top1);
    }


}

