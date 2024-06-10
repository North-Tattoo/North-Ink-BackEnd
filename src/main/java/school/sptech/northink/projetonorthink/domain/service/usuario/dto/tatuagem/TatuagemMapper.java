package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;

import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

import java.util.List;

public class TatuagemMapper {

    public static TatuagemListagemDto toDto(Tatuagem entity) {
        if (entity == null) return null;

        TatuagemListagemDto tatuagemDto = new TatuagemListagemDto();
        tatuagemDto.setEstilo(entity.getEstilo());
        tatuagemDto.setPreco(entity.getPreco());
        tatuagemDto.setFkUsuario(toUsuarioDto(entity.getFkUsuario()));
        tatuagemDto.setImagemUrl(entity.getImagemUrl());  // Adicione essa linha

        return tatuagemDto;
    }

    private static TatuagemListagemDto.UsuarioDto toUsuarioDto(Usuario entity) {
        if (entity == null) return null;

        TatuagemListagemDto.UsuarioDto usuarioDto = new TatuagemListagemDto.UsuarioDto();
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

    public static List<TatuagemListagemDto> toDto(List<Tatuagem> entities) {
        return entities.stream().map(TatuagemMapper::toDto).toList();
    }

    public static Tatuagem toEntity(TatuagemCriacaoDto dto) {
        if (dto == null) return null;

        Tatuagem tatuagem = new Tatuagem();
        tatuagem.setEstilo(dto.getEstilo());
        tatuagem.setPreco(dto.getPreco());
        tatuagem.setId(dto.getUsuarioId());
        tatuagem.setImagemUrl(dto.getImagemUrl());  // Adicione essa linha se o DTO tiver esse campo

        return tatuagem;
    }
}
