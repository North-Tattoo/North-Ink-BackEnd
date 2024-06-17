package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCountDto;

import java.util.List;

@AllArgsConstructor
@Service
public class DashboardService {

    @Autowired
    private EstiloService estiloService;

    public List<Estilo> getTop3Estilos() {
        return estiloService.findTop3Estilos();
    }

    public Estilo findEstiloMaisCadastrado (){
        return estiloService.findEstiloMaisCadastrado();
    }
}
