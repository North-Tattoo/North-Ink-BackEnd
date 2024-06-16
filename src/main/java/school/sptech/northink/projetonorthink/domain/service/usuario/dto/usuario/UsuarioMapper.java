package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    // O que esta acontecento aqui é que estamos pegando as informações do front end e convertendo em uma entity

    // me ajude a entender o que é um DTO




    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSobrenome(usuarioCriacaoDto.getSobrenome());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setCelular(usuarioCriacaoDto.getCelular());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setResumo(usuarioCriacaoDto.getResumo());
        List<Estilo> estilos = usuarioCriacaoDto.getEstilos().stream()
                .map(estiloDto -> {
                    Estilo estilo = new Estilo();
                    estilo.setNome(estiloDto.getNome());
                    return estilo;
                })
                .collect(Collectors.toList());
        usuario.setEstilos(estilos);

        return usuario;
    }

    // convertendo em autenticação
    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    // metodo convertendo a entity em uma dto
    public static UsuarioListagemDto toDto(Usuario usuario ) {
        if (usuario == null) return null;

        UsuarioListagemDto usuarioListagemDto = new UsuarioListagemDto();

        usuarioListagemDto.setNome(usuario.getNome());
        usuarioListagemDto.setSobrenome(usuario.getSobrenome());
        usuarioListagemDto.setCpf(usuario.getCpf());
        usuarioListagemDto.setCelular(usuario.getCelular());
        usuarioListagemDto.setEmail(usuario.getEmail());
        //usuarioListagemDto.setSenha(usuario.getSenha());
        usuarioListagemDto.setResumo(usuario.getResumo());
        usuarioListagemDto.setPrecoMinimo(usuario.getPrecoMinimo());
        usuarioListagemDto.setInstagram(usuario.getInstagram());
        List<EstiloListagemDto> estilosListagemDto = EstiloMapper.toDto(usuario.getEstilos());
        usuarioListagemDto.setEstilos(estilosListagemDto);
        usuarioListagemDto.setTatuagens(usuario.getTatuagens());

        return usuarioListagemDto;
    }



    private static List<UsuarioListagemDto.EstiloDto> toEstiloDto(List<Estilo> entities) {
        return entities.stream().map(e -> {
            UsuarioListagemDto.EstiloDto dto = new UsuarioListagemDto.EstiloDto();
            dto.setId(e.getId());
            dto.setNome(e.getNome());
            return dto;
        }).toList();
    }

    // Método sobrecarregado para mapear uma lista de entidades em uma lista de DTOs
    public static List<UsuarioListagemDto> toDto(List<Usuario> usuarios) {
        if (usuarios == null) return null;
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }



    public static Usuario atualizarUsuario(Usuario usuarioExistente, UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        // Atualize os campos do usuário existente com base nos dados do DTO de atualização
        usuarioExistente.setNome(usuarioAtualizacaoDto.getNome());
        usuarioExistente.setSobrenome(usuarioAtualizacaoDto.getSobrenome());
        usuarioExistente.setEmail(usuarioAtualizacaoDto.getEmail());
        usuarioExistente.setSenha(usuarioAtualizacaoDto.getSenha());
        usuarioExistente.setSenha(usuarioAtualizacaoDto.getNovaSenha());
        usuarioExistente.setCelular(usuarioAtualizacaoDto.getCelular());

        return usuarioExistente;
    }

    public static UsuarioFotoDto of(Usuario u) {
        return new UsuarioFotoDto(
                u.getId(),
                u.getNome(),
                u.getFotoPerfil()
        );
    }

    public static UsuarioListagemPortfolioDto toPortfolioDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioListagemPortfolioDto usuarioPortfolioDto = new UsuarioListagemPortfolioDto();
        usuarioPortfolioDto.setId(usuario.getId());
        usuarioPortfolioDto.setFotoPerfil(usuario.getFotoPerfil());
        usuarioPortfolioDto.setNome(usuario.getNome());
        usuarioPortfolioDto.setValorMin(usuario.getPrecoMinimo());
        usuarioPortfolioDto.setAnosExperiencia(usuario.getAnosExperiencia());
        usuarioPortfolioDto.setResumo(usuario.getResumo());
        usuarioPortfolioDto.setDescricao(usuario.getDescricao());
        usuarioPortfolioDto.setInstagram(usuario.getInstagram());
        usuarioPortfolioDto.setEstilos(usuario.getEstilos());
        usuarioPortfolioDto.setEstudio(usuario.getEstudio());

        return usuarioPortfolioDto;
    }

    public static UsuarioAtualizaçãoPortfolioDto toAtualizacaoPortfolioDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioAtualizaçãoPortfolioDto usuarioAtualizacaoPortfolioDto = new UsuarioAtualizaçãoPortfolioDto();
        usuarioAtualizacaoPortfolioDto.setId(usuario.getId()); // Use the correct method to set the ID
        usuarioAtualizacaoPortfolioDto.setPrecoMin(usuario.getPrecoMinimo());
        usuarioAtualizacaoPortfolioDto.setAnosExperiencia(usuario.getAnosExperiencia());
        usuarioAtualizacaoPortfolioDto.setResumo(usuario.getResumo());
        usuarioAtualizacaoPortfolioDto.setInstagram(usuario.getInstagram());
        // Aqui você precisa converter a lista de Estilo da entidade para a lista de Estilo do DTO
        // Você pode usar o método map do Stream para isso, semelhante ao que você fez no método of
        List<Estilo> estilos = usuario.getEstilos().stream()
                .map(estilo -> {
                    Estilo estiloDto = new Estilo();
                    estiloDto.setNome(estilo.getNome());
                    // Aqui você pode definir os outros campos do Estilo se necessário
                    return estiloDto;
                })
                .collect(Collectors.toList());
        usuarioAtualizacaoPortfolioDto.setEstilos(estilos);

        return usuarioAtualizacaoPortfolioDto;
    }

    public static Usuario atualizarUsuarioPortfolio(Usuario usuarioExistente, UsuarioAtualizaçãoPortfolioDto usuarioAtualizacaoPortfolioDto) {
        // Atualize os campos do usuário existente com base nos dados do DTO de atualização
        usuarioExistente.setId(usuarioAtualizacaoPortfolioDto.getId());
        usuarioExistente.setPrecoMinimo(usuarioAtualizacaoPortfolioDto.getPrecoMin());
        usuarioExistente.setAnosExperiencia(usuarioAtualizacaoPortfolioDto.getAnosExperiencia());
        usuarioExistente.setResumo(usuarioAtualizacaoPortfolioDto.getResumo());
        usuarioExistente.setInstagram(usuarioAtualizacaoPortfolioDto.getInstagram());
        // Aqui você precisa converter a lista de Estilo do DTO para a lista de Estilo da entidade
        // Você pode usar o método map do Stream para isso, semelhante ao que você fez no método of
        List<Estilo> estilosDto = usuarioAtualizacaoPortfolioDto.getEstilos();
        if (estilosDto != null) {
            List<Estilo> estilos = estilosDto.stream()
                    .map(estiloDto -> {
                        Estilo estilo = new Estilo();
                        estilo.setNome(estiloDto.getNome());
                        // Aqui você pode definir os outros campos do Estilo se necessário
                        return estilo;
                    })
                    .collect(Collectors.toList());
            usuarioExistente.setEstilos(estilos);
        }

        return usuarioExistente;
    }
}
