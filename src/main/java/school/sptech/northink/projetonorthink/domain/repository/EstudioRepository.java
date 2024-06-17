package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;

import java.util.Optional;

public interface EstudioRepository extends JpaRepository<Estudio, Long> {



}
