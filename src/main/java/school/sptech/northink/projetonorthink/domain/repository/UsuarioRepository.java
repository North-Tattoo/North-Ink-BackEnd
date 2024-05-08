package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // UTILIZADO NO JWT
    Optional<Usuario> findByEmail(String email);



}
