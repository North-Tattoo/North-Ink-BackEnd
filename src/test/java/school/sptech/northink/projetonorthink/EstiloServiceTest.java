package school.sptech.northink.projetonorthink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.repository.EstiloRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstiloService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCriacaoDto;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstiloServiceTest {

    @Mock
    private EstiloRepository estiloRepository;

    @InjectMocks
    private EstiloService estiloService;

    @BeforeEach
    void configurar() {
        estiloService = new EstiloService(estiloRepository);
    }

    @Test
    void deveChamarFindByIdESaveAoAtualizarEstilo() {
        Long id = 1L;
        EstiloAtualizacaoDto estiloAtualizacaoDto = new EstiloAtualizacaoDto();
        when(estiloRepository.findById(id)).thenReturn(Optional.of(new Estilo()));
        estiloService.atualizarEstilo(id, estiloAtualizacaoDto);
        verify(estiloRepository).findById(id);
        verify(estiloRepository).save(any(Estilo.class));
    }

    @Test
    void deveChamarDeleteByIdAoDeletarEstilo() {
        Long id = 1L;
        estiloService.deletarEstilo(id);
        verify(estiloRepository).deleteById(id);
    }

    @Test
    void deveChamarFindByIdAoBuscarEstiloPorId() {
        Long id = 1L;
        when(estiloRepository.findById(id)).thenReturn(Optional.of(new Estilo()));
        estiloService.buscarEstiloPorId(id);
        verify(estiloRepository).findById(id);
    }

    @Test
    void deveChamarFindAllAoListarEstilos() {
        estiloService.listarEstilos();
        verify(estiloRepository).findAll();
    }
}