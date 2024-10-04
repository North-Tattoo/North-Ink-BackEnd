package school.sptech.northink.projetonorthink.api.controller.api.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.northink.projetonorthink.api.controller.api.ApiController;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.DashboardService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCountDto;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController extends ApiController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/top3Estilos")
    public List<EstiloCountDto> getTop3Estilos() {
        return dashboardService.getTop3Estilos();
    }

    /*@GetMapping("/estiloMaisCadastrado")
    public Estilo getEstiloMaisCadastrado() {
        return dashboardService.findEstiloMaisCadastrado();
    }*/
}
