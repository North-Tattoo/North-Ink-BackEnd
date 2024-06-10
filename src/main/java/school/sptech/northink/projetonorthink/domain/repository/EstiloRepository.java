package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;

public interface EstiloRepository extends JpaRepository<Estilo, Long > {

    @Query("SELECT e FROM Estilo e JOIN e.fkUsuario u GROUP BY e.id ORDER BY COUNT(u) DESC")
    Estilo findEstiloMaisCadastrado();
}
