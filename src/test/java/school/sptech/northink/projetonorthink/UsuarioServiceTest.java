package school.sptech.northink.projetonorthink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.northink.projetonorthink.api.configuration.security.jwt.GerenciadorTokenJWT;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario.UsuarioCriacaoDto;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private GerenciadorTokenJWT gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void configurar() {
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void deveChamarFindAllAoListarUsuarios() {
        usuarioService.listarUsuarios();
        verify(usuarioRepository).findAll();
    }

    @Test
    void deveChamarFindByIdAoListarUsuarioId() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(new Usuario()));
        usuarioService.listarUsuarioId(id);
        verify(usuarioRepository).findById(id);
    }

    @Test
    void deveChamarSaveAoAtualizarUsuario() {
        Long id = 1L;
        UsuarioAtualizacaoDto usuarioAtualizacaoDto = new UsuarioAtualizacaoDto();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(new Usuario()));
        usuarioService.atualizarUsuario(id, usuarioAtualizacaoDto);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void deveChamarDeleteAoDeletarUsuario() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(new Usuario()));
        usuarioService.deletarUsuario(id);
        verify(usuarioRepository).delete(any(Usuario.class));
    }

    // Adicione mais testes para outros métodos em UsuarioService
}