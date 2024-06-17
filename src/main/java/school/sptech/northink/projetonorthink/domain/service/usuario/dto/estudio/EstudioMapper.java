package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;

import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.*;

import java.util.List;

public class EstudioMapper {


    // metodo de criação do estudio no sistema
    public static Estudio of(EstudioCriacaoDto estudioCriacaoDto) {
        Estudio estudio = new Estudio();

            estudio.setNome(estudioCriacaoDto.getNome());
            estudio.setDescricao(estudioCriacaoDto.getDescricao());


        return estudio;
    }

    // convertendo uma dto para um entidade
    public static Estudio toEntity(EstudioCriacaoDto estudioCriacaoDto, UsuarioService usuarioService) {
        if (estudioCriacaoDto == null) return null;

        Estudio estudio = new Estudio();

        estudio.setNome(estudioCriacaoDto.getNome());
        estudio.setDescricao(estudioCriacaoDto.getDescricao());
        if (estudioCriacaoDto.getFkUsuario() != null) {
            Usuario usuario = usuarioService.porId(estudioCriacaoDto.getFkUsuario());
            estudio.setUsuario(usuario);
        }

        return estudio;
    }

}
