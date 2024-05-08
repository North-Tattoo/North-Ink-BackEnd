package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.List;

public class UsuarioMapper {


    // pegando as informações do front end - CADASTRO
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSobrenome(usuarioCriacaoDto.getSobrenome());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setCelular(usuarioCriacaoDto.getCelular());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setResumo(usuarioCriacaoDto.getResumo());
        usuario.setEstilos(usuarioCriacaoDto.getEstilos());

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
    public static UsuarioListagemDto toDto(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioListagemDto usuarioListagemDto = new UsuarioListagemDto();

        usuarioListagemDto.setNome(usuario.getNome());
        usuarioListagemDto.setSobrenome(usuario.getSobrenome());
        usuarioListagemDto.setCpf(usuario.getCpf());
        usuarioListagemDto.setCelular(usuario.getCelular());
        usuarioListagemDto.setEmail(usuario.getEmail());
        usuarioListagemDto.setSenha(usuario.getSenha());
        usuarioListagemDto.setResumo(usuario.getResumo());
        usuarioListagemDto.setEstilos(usuario.getEstilos());

        return usuarioListagemDto;
    }

    // convertendo uma dto para um entidade
    public static Usuario toEntity(UsuarioCriacaoDto usuarioCriacaoDto) {
        if (usuarioCriacaoDto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSobrenome(usuarioCriacaoDto.getSobrenome());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setCelular(usuarioCriacaoDto.getCelular());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setResumo(usuarioCriacaoDto.getResumo());
        usuario.setEstilos(usuarioCriacaoDto.getEstilos());

        return usuario;
    }

    // Método sobrecarregado para mapear uma lista de entidades em uma lista de DTOs
    public static List<UsuarioListagemDto> toDto(List<Usuario> entities) {
        // Aqui é utilizado um método que mapea um a um e reutilizado para poder fazer a passagem de lista sem duplicar código
        return entities.stream().map(UsuarioMapper::toDto).toList();
    }


    public static Usuario atualizarUsuario(Usuario usuarioExistente, UsuarioAtualizacaoDto usuarioAtualizacaoDto) {
        // Atualize os campos do usuário existente com base nos dados do DTO de atualização
        usuarioExistente.setNome(usuarioAtualizacaoDto.getNome());
        usuarioExistente.setSobrenome(usuarioAtualizacaoDto.getSobrenome());
        usuarioExistente.setCpf(usuarioAtualizacaoDto.getCpf());
        usuarioExistente.setCelular(usuarioAtualizacaoDto.getCelular());
        usuarioExistente.setEmail(usuarioAtualizacaoDto.getEmail());
        usuarioExistente.setSenha(usuarioAtualizacaoDto.getSenha());
        usuarioExistente.setResumo(usuarioAtualizacaoDto.getSobreMim());
        usuarioExistente.setEstilos(usuarioAtualizacaoDto.getEstilos());

        return usuarioExistente;
    }

}
