package school.sptech.northink.projetonorthink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.repository.EnderecoRepository;
import school.sptech.northink.projetonorthink.domain.repository.EstudioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.EnderecoService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private EstudioRepository estudioRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void deveChamarFindAllAoListar() {
        enderecoService.listar();
        verify(enderecoRepository).findAll();
    }

    @Test
    void deveChamarFindByIdAoBuscarPorId() {
        Long id = 1L;
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(new Endereco()));
        enderecoService.buscarPorId(id);
        verify(enderecoRepository).findById(id);
    }

   /* @Test
    void deveChamarSaveAoSalvar() {
        Long estudioId = 1L;
        Endereco endereco = new Endereco();
        when(estudioRepository.findById(estudioId)).thenReturn(Optional.of(new Estudio()));
        enderecoService.salvar(endereco, estudioId);
        verify(enderecoRepository).save(any(Endereco.class));
    }*/

    @Test
    void deveChamarDeleteByIdAoDeletar() {
        Long id = 1L;
        when(enderecoRepository.existsById(id)).thenReturn(true);
        enderecoService.deletar(id);
        verify(enderecoRepository).deleteById(id);
    }
}