package school.sptech.northink.projetonorthink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.TatuagemRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.TatuagemService;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TatuagemServiceTest {

    @Mock
    private TatuagemRepository tatuagemRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private TatuagemService tatuagemService;

    @BeforeEach
    void configurar() {
        tatuagemService = new TatuagemService(tatuagemRepository, usuarioService);
    }

    @Test
    void deveChamarFindAllAoListar() {
        tatuagemService.listar();
        verify(tatuagemRepository).findAll();
    }

    @Test
    void deveChamarFindByIdAoBuscarPorId() {
        Long id = 1L;
        when(tatuagemRepository.findById(id)).thenReturn(Optional.of(new Tatuagem()));
        tatuagemService.buscarPorId(id);
        verify(tatuagemRepository).findById(id);
    }

    @Test
    void deveChamarSaveAoCriar() {
        Long usuarioId = 1L;
        Tatuagem novaTatuagem = new Tatuagem();
        when(usuarioService.porId(usuarioId)).thenReturn(new Usuario());
        tatuagemService.criar(novaTatuagem, usuarioId);
        verify(tatuagemRepository).save(any(Tatuagem.class));
    }

    @Test
    void deveChamarDeleteByIdAoDeletar() {
        Long id = 1L;
        when(tatuagemRepository.existsById(id)).thenReturn(true);
        tatuagemService.deletar(id);
        verify(tatuagemRepository).deleteById(id);
    }
}