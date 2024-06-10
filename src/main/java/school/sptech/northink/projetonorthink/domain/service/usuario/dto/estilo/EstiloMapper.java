package school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo;

import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemMapper;

import java.util.List;

public class EstiloMapper {


    public static EstiloListagemDto toDto(Estilo entity) {
        if (entity == null) return null;

        EstiloListagemDto estiloListagemDto = new EstiloListagemDto();

        estiloListagemDto.setNome(entity.getNome());


        return estiloListagemDto;
    }


    public static Estilo atualizarEstilo(Estilo estiloExistente, EstiloAtualizacaoDto estiloAtualizacaoDto) {

        estiloExistente.setNome(estiloAtualizacaoDto.getNome());

        return estiloExistente;
    }

    public static List<EstiloListagemDto> toDto(List<Estilo> entities) {
        // Aqui é utilizado um método que mapea um a um e reutilizado para poder fazer a passagem de lista sem duplicar código
        return entities.stream().map(EstiloMapper::toDto).toList();
    }

    public static Estilo toEntity(EstiloCriacaoDto dto){
        if (dto == null) return null;

        Estilo estilo = new Estilo();
        estilo.setNome(dto.getNome());

        return estilo;
    }
}
