package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSobrenome(usuarioCriacaoDto.getSobrenome());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setCelular(usuarioCriacaoDto.getCelular());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setResumo(usuarioCriacaoDto.getResumo());
        Set<Estilo> estilos = usuarioCriacaoDto.getEstilos().stream()
                .map(estiloDto -> {
                    Estilo estilo = new Estilo();
                    estilo.setNome(estiloDto.getNome());
                    return estilo;
                })
                .collect(Collectors.toSet());
        usuario.setEstilos(estilos);

        return usuario;
    }

    // convertendo em autenticação
    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setSobrenome(usuario.getSobrenome()); // Novo campo
        usuarioTokenDto.setCpf(usuario.getCpf()); // Novo campo
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setAssinante(usuario.getAssinante() != null ? usuario.getAssinante():false); // Novo campo
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
        usuarioListagemDto.setResumo(usuario.getResumo());
        usuarioListagemDto.setAnosExperiencia(usuario.getAnosExperiencia());
        usuarioListagemDto.setPrecoMinimo(usuario.getPrecoMinimo());
        usuarioListagemDto.setInstagram(usuario.getInstagram());
        List<Estilo> estilos = new ArrayList<>(usuario.getEstilos());
        List<EstiloListagemDto> estilosListagemDto = EstiloMapper.toDto(estilos);
        usuarioListagemDto.setEstilos(estilosListagemDto);
        usuarioListagemDto.setTatuagens(usuario.getTatuagens());

        return usuarioListagemDto;
    }

    public static UsuarioListagemGeralDto toUsuarioListagemGeralDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioListagemGeralDto usuarioListagemGeralDto = new UsuarioListagemGeralDto();
        usuarioListagemGeralDto.setId(usuario.getId());
        usuarioListagemGeralDto.setNome(usuario.getNome());
        usuarioListagemGeralDto.setSobrenome(usuario.getSobrenome());
        usuarioListagemGeralDto.setPrecoMin(usuario.getPrecoMinimo());
        List<Estilo> estilos = new ArrayList<>(usuario.getEstilos());
        usuarioListagemGeralDto.setEstilos(estilos);
        usuarioListagemGeralDto.setEstudio(usuario.getEstudio());

        return usuarioListagemGeralDto;
    }

    // Método sobrecarregado para mapear uma lista de entidades em uma lista de DTOs
    public static List<UsuarioListagemDto> toDto(List<Usuario> usuarios) {
        if (usuarios == null) return null;
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }

    /*
    Este método abaixo não está sendo utilizado no momento (28/08/2024).
    Ele deve ser chamado quando você precisar atualizar um usuário existente com base nos dados de um DTO de atualização.
    Nese caso, essa atualização é feita no Menu na aba de perfil
    - Daniel Zapatta 28/08/2024 */
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
        usuarioPortfolioDto.setSobrenome(usuario.getSobrenome());
        usuarioPortfolioDto.setValorMin(usuario.getPrecoMinimo());
        usuarioPortfolioDto.setAnosExperiencia(usuario.getAnosExperiencia());
        usuarioPortfolioDto.setResumo(usuario.getResumo());
        usuarioPortfolioDto.setTelefone(usuario.getCelular());
        usuarioPortfolioDto.setInstagram(usuario.getInstagram());
        List<Estilo> estilos = new ArrayList<>(usuario.getEstilos());
        usuarioPortfolioDto.setEstilos(estilos);
        usuarioPortfolioDto.setEstudio(usuario.getEstudio());

        return usuarioPortfolioDto;
    }

    // Método usado para enviar dados necessários ao front-end para atualizar o portfólio do usuário
    // Daniel Zapatta 28/08/2024
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

    // Método necessário para receber os dados do front-end e atualizar o portfólio do usuário
// Daniel Zapatta 28/08/2024
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
            Set<Estilo> estilos = estilosDto.stream()
                    .map(estiloDto -> {
                        Estilo estilo = new Estilo();
                        estilo.setNome(estiloDto.getNome());
                        // Aqui você pode definir os outros campos do Estilo se necessário
                        return estilo;
                    })
                    .collect(Collectors.toSet());
            usuarioExistente.setEstilos(estilos);
        }

        return usuarioExistente;
    }
}
