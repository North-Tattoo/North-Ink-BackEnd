package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.repository.EstiloRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloAtualizacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.EstiloMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class EstiloService {

    private final EstiloRepository estiloRepository;

    public EstiloListagemDto criarEstilo(EstiloCriacaoDto estiloCriacaoDto) {
        Estilo estilo = EstiloMapper.toEntity(estiloCriacaoDto);
        estilo = estiloRepository.save(estilo);
        return EstiloMapper.toDto(estilo);
    }

    public EstiloListagemDto atualizarEstilo(Long id, EstiloAtualizacaoDto estiloAtualizacaoDto) {
        Estilo estiloExistente = estiloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estilo não encontrado com o id: " + id));
        estiloExistente = EstiloMapper.atualizarEstilo(estiloExistente, estiloAtualizacaoDto);
        estiloExistente = estiloRepository.save(estiloExistente);
        return EstiloMapper.toDto(estiloExistente);
    }

    public void deletarEstilo(Long id) {
        estiloRepository.deleteById(id);
    }

    public EstiloListagemDto buscarEstiloPorId(Long id) {
        Estilo estilo = estiloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estilo não encontrado com o id: " + id));
        return EstiloMapper.toDto(estilo);
    }

    public List<EstiloListagemDto> listarEstilos() {
        List<Estilo> estilos = estiloRepository.findAll();
        return EstiloMapper.toDto(estilos);
    }
}
