package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio;

import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioMapper;

import java.util.List;

public class EstudioMapper {


    // metodo de criação do estudio no sistema
    public static Estudio of(EstudioCriacaoDto estudioCriacaoDto) {
        Estudio estudio = new Estudio();

            estudio.setNome(estudioCriacaoDto.getNome());
            estudio.setEmail(estudioCriacaoDto.getEmail());
            estudio.setDescricao(estudioCriacaoDto.getDescricao());
            estudio.setPrecoMin(estudioCriacaoDto.getPrecoMin());
            estudio.setAnosExperiencia(estudioCriacaoDto.getAnosExperiencia());

        return estudio;
    }


    // metodo convertendo a entity em uma dto
    public static EstudioListagemDto toDto(Estudio estudio) {
        if (estudio == null) return null;

        EstudioListagemDto estudioListagemDto = new EstudioListagemDto();

        estudioListagemDto.setId(estudio.getId());
        estudioListagemDto.setNome(estudio.getNome());
        estudioListagemDto.setDescricao(estudio.getDescricao());
        estudioListagemDto.setPrecoMin(estudio.getPrecoMin());
        estudioListagemDto.setAnosExperiencia(estudio.getAnosExperiencia());

        return estudioListagemDto;
    }

    // convertendo uma dto para um entidade
    public static Estudio toEntity(EstudioCriacaoDto estudioCriacaoDto) {
        if (estudioCriacaoDto == null) return null;

        Estudio estudio = new Estudio();

        estudio.setNome(estudioCriacaoDto.getNome());
        estudio.setEmail(estudioCriacaoDto.getEmail());
        estudio.setDescricao(estudioCriacaoDto.getDescricao());
        estudio.setPrecoMin(estudioCriacaoDto.getPrecoMin());
        estudio.setAnosExperiencia(estudioCriacaoDto.getAnosExperiencia());

        return estudio;
    }

    public static List<EstudioListagemDto> toDto(List<Estudio> entities) {
        return entities.stream().map(EstudioMapper::toDto).toList();
    }

    public static Estudio atualizarEstudio(Estudio estudioExistente, EstudioAtualizacaoDto estudioAtualizacaoDto) {

        estudioExistente.setNome(estudioAtualizacaoDto.getNome());
        estudioExistente.setEmail(estudioAtualizacaoDto.getEmail());
        estudioExistente.setDescricao(estudioAtualizacaoDto.getDescricao());
        estudioExistente.setPrecoMin(estudioAtualizacaoDto.getPrecoMin());
        estudioExistente.setAnosExperiencia(estudioAtualizacaoDto.getAnosExperiencia());

        return estudioExistente;
    }
}
