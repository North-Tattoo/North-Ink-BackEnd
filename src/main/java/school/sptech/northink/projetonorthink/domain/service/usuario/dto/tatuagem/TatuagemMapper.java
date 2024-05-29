package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;

import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;

import java.util.List;

public class TatuagemMapper {

    public static TatuagemListagemDto toDto(Tatuagem tatuagem) {
        if (tatuagem == null) return null;

        TatuagemListagemDto tatuagemListagemDto = new TatuagemListagemDto();

        tatuagemListagemDto.setEstilo(tatuagem.getEstilo());
        tatuagemListagemDto.setPreco(tatuagem.getPreco());

        return tatuagemListagemDto;
    }

    public static Tatuagem toEntity(TatuagemCriacaoDto tatuagemCriacaoDto) {
        if (tatuagemCriacaoDto == null) return null;

        Tatuagem tatuagem = new Tatuagem();

        tatuagem.setEstilo(tatuagemCriacaoDto.getEstilo());
        tatuagem.setPreco(tatuagemCriacaoDto.getPreco());
        tatuagem.setFkUsuario(tatuagemCriacaoDto.getFkUsuario());

        return tatuagem;
    }


    public static Tatuagem atualizarTatuagem(Tatuagem tatuagemExistente, TatuagemAtualizacaoDto tatuagemAtualizacaoDto) {

        tatuagemExistente.setEstilo(tatuagemAtualizacaoDto.getEstilo());
        tatuagemExistente.setPreco(tatuagemAtualizacaoDto.getPreco());


        return tatuagemExistente;
    }

//    private static TatuagemListagemDto.UsuarioDto toDiretorDto(Usuario entity) {
//        if (entity == null) return null;
//
//        TatuagemListagemDto.UsuarioDto usuarioDto = new TatuagemListagemDto.UsuarioDto();
//        usuarioDto.setId(entity.getId());
//        usuarioDto.setNome(entity.getNome());
//        usuarioDto.setSobrenome(entity.getSobrenome());
//        usuarioDto.setCpf(entity.getCpf());
//        usuarioDto.setCelular(entity.getCelular());
//        usuarioDto.setEmail(entity.getEmail());
//        usuarioDto.setSenha(entity.getSenha());
//        usuarioDto.setResumo(entity.getResumo());
//        usuarioDto.setAnosExperiencia(entity.getAnosExperiencia());
//        usuarioDto.setPrecoMinimo(entity.getPrecoMinimo());
//        usuarioDto.setInstagram(entity.getInstagram());
//
//        return usuarioDto;
//    }

    public static List<TatuagemListagemDto> toDto(List<Tatuagem> entities) {
        // Aqui é utilizado um método que mapea um a um e reutilizado para poder fazer a passagem de lista sem duplicar código
        return entities.stream().map(TatuagemMapper::toDto).toList();
    }

}
