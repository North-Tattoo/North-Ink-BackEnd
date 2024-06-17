package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long > {
}
