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
            estudio.setDescricao(estudioCriacaoDto.getDescricao());


        return estudio;
    }


    // metodo convertendo a entity em uma dto
//    public static EstudioListagemDto toDto(Estudio estudio) {
//        if (estudio == null) return null;
//
//        EstudioListagemDto estudioListagemDto = new EstudioListagemDto();
//
//        estudioListagemDto.setNome(estudio.getNome());
//        estudioListagemDto.setDescricao(estudio.getDescricao());
//
//        return estudioListagemDto;
//    }

    // convertendo uma dto para um entidade
    // Adicionado instância do Usuario como argumento no método - Zapatta
    public static Estudio toEntity(EstudioCriacaoDto estudioCriacaoDto, Usuario usuario) {
        if (estudioCriacaoDto == null) return null;

        Estudio estudio = new Estudio();

        estudio.setNome(estudioCriacaoDto.getNome());
        estudio.setDescricao(estudioCriacaoDto.getDescricao());
        // estudio.setFkUsuario(estudioCriacaoDto.getFkUsuario());
        // Necessário puxar o Usuario pois ele está tendo relacionamento na entidade Estudio - Zapatta
        estudio.setUsuario(usuario);

        return estudio;
    }

//    public static List<EstudioListagemDto> toDto(List<Estudio> entities) {
//        return entities.stream().map(EstudioMapper::toDto).toList();
//    }
//
//    public static Estudio atualizarEstudio(Estudio estudioExistente, EstudioAtualizacaoDto estudioAtualizacaoDto) {
//
//        estudioExistente.setNome(estudioAtualizacaoDto.getNome());
//        estudioExistente.setDescricao(estudioAtualizacaoDto.getDescricao());
//
//
//        return estudioExistente;
//    }
}
