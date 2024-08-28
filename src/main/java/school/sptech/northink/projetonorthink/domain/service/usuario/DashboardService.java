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

    /**
     * Este método retorna os três principais estilos com base em algum critério definido no método `findTop3Estilos()`
     * do serviço `EstiloService`. O critério exato depende da implementação desse método no serviço `EstiloService`.
     *
     * @return Uma lista dos três principais `EstiloCountDto`.
     */
    public List<EstiloCountDto> getTop3Estilos() {
        return estiloService.findTop3Estilos();
    }

    /**
     * Este método retorna o estilo mais cadastrado com base em algum critério definido no método `findEstiloMaisCadastrado()`
     * do serviço `EstiloService`. O critério exato depende da implementação desse método no serviço `EstiloService`.
     *
     * @return O `Estilo` que é o mais cadastrado.
     */
    public Estilo findEstiloMaisCadastrado (){
        return estiloService.findEstiloMaisCadastrado();
    }
}