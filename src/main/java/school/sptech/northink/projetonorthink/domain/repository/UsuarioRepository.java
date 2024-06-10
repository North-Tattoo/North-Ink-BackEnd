package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // UTILIZADO NO JWT
    Optional<Usuario> findByEmail(String email);

    List<UsuarioListagemDto> findUsuarioByEstilos(List<Estilo> estilos);

    @Query("SELECT e FROM Estilo e JOIN e.fkUsuario u GROUP BY e.id ORDER BY COUNT(u) DESC")
    Estilo findEstiloMaisCadastrado();


}
