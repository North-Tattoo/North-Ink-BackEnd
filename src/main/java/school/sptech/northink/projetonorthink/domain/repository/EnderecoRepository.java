package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long > {
}
