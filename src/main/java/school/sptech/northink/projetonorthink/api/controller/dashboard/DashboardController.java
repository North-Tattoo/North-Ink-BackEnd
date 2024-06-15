package school.sptech.northink.projetonorthink.api.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.DashboardService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCountDto;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/top3Estilos")
    public List<EstiloCountDto> getTop3Estilos() {
        return dashboardService.getTop3Estilos();
    }

    @GetMapping("/findTopEstilo")
    public Estilo findEstiloMaisCadastrado() {
        return dashboardService.findEstiloMaisCadastrado();
    }
}