package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCountDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;

import java.util.List;

public interface EstiloRepository extends JpaRepository<Estilo, Long > {

    @Query("SELECT e FROM Estilo e JOIN e.fkUsuario u GROUP BY e.id ORDER BY COUNT(u) DESC")
    Estilo findEstiloMaisCadastrado();

    @Query("SELECT new school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCountDto(e.nome, COUNT(u)) " +
            "FROM Estilo e JOIN e.fkUsuario u GROUP BY e.nome ORDER BY COUNT(u) DESC")
    List<EstiloCountDto> findTop3Estilos();

    @Override
    @Query("SELECT e FROM Estilo e")
    List<Estilo> findAll();
}
