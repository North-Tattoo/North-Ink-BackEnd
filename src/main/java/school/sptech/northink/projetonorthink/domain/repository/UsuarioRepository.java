package school.sptech.northink.projetonorthink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioListagemDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // UTILIZADO NO JWT
    Optional<Usuario> findByEmail(String email);

    List<UsuarioListagemDto> findUsuarioByEstilos(List<EstiloListagemDto> estilos);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.estilos WHERE u.id = :id")
    Usuario findByIdAndFetchEstilosEagerly(@Param("id") Long id);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.estilos")
    List<Usuario> findAllAndFetchEstilosEagerly();



}
