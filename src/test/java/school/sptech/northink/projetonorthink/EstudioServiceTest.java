package school.sptech.northink.projetonorthink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.EstudioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstudioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.UsuarioService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioCriacaoDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudioServiceTest {

    @InjectMocks
    private EstudioService estudioService;

    @Mock
    private EstudioRepository estudioRepository;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        Estudio estudio1 = new Estudio();
        Estudio estudio2 = new Estudio();
        when(estudioRepository.findAll()).thenReturn(Arrays.asList(estudio1, estudio2));

        List<Estudio> estudios = estudioService.listar();

        assertEquals(2, estudios.size());
        verify(estudioRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId() {
        Estudio estudio = new Estudio();
        when(estudioRepository.findById(1L)).thenReturn(Optional.of(estudio));

        Estudio foundEstudio = estudioService.buscarPorId(1L);

        assertNotNull(foundEstudio);
        verify(estudioRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletar() {
        when(estudioRepository.existsById(1L)).thenReturn(true);

        estudioService.deletar(1L);

        verify(estudioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletarNotFound() {
        when(estudioRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> estudioService.deletar(1L));

        verify(estudioRepository, times(1)).existsById(1L);
    }
}