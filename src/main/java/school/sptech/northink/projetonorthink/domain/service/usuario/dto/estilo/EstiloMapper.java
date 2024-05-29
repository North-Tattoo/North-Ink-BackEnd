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

    public static EstiloListagemDto.UsuarioDto toUsuarioDto(Usuario entity) {
        if (entity == null) return null;

        EstiloListagemDto.UsuarioDto usuarioDto = new EstiloListagemDto.UsuarioDto();

        usuarioDto.setId(entity.getId());
        usuarioDto.setNome(entity.getNome());
        usuarioDto.setSobrenome(entity.getSobrenome());
        usuarioDto.setCpf(entity.getCpf());
        usuarioDto.setCelular(entity.getCelular());
        usuarioDto.setEmail(entity.getEmail());
        usuarioDto.setSenha(entity.getSenha());
        usuarioDto.setResumo(entity.getResumo());
        usuarioDto.setAnosExperiencia(entity.getAnosExperiencia());
        usuarioDto.setPrecoMinimo(entity.getPrecoMinimo());
        usuarioDto.setInstagram(entity.getInstagram());

        return usuarioDto;
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
