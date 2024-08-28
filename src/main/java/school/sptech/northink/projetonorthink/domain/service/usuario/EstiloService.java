package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.EstiloRepository;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estilo.*;

import java.util.List;

@AllArgsConstructor
@Service
public class EstiloService {

    private final EstiloRepository estiloRepository;

    /**
     * Este método cria um novo estilo.
     * Ele primeiro mapeia o DTO de criação de estilo para uma entidade de estilo, salva a entidade no repositório e retorna o estilo mapeado para o DTO.
     *
     * @param estiloCriacaoDto O DTO contendo as informações para a criação do estilo.
     * @param usuario O usuário que está criando o estilo.
     * @return O `EstiloListagemDto` do estilo criado.
     */
    public EstiloListagemDto criarEstilo(EstiloCriacaoDto estiloCriacaoDto, Usuario usuario) {
        Estilo estilo = EstiloMapper.toEntity(estiloCriacaoDto, usuario);
        estilo = estiloRepository.save(estilo);
        return EstiloMapper.toDto(estilo);
    }

    /**
     * Este método atualiza um estilo existente.
     * Ele primeiro busca o estilo pelo ID, atualiza o estilo com as informações do DTO de atualização, salva o estilo atualizado no repositório e retorna o estilo mapeado para o DTO.
     *
     * @param id O ID do estilo a ser atualizado.
     * @param estiloAtualizacaoDto O DTO contendo as informações para a atualização do estilo.
     * @return O `EstiloListagemDto` do estilo atualizado.
     */
    public EstiloListagemDto atualizarEstilo(Long id, EstiloAtualizacaoDto estiloAtualizacaoDto) {
        Estilo estiloExistente = estiloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estilo não encontrado com o id: " + id));
        estiloExistente = EstiloMapper.atualizarEstilo(estiloExistente, estiloAtualizacaoDto);
        estiloExistente = estiloRepository.save(estiloExistente);
        return EstiloMapper.toDto(estiloExistente);
    }

    /**
     * Este método deleta um estilo pelo seu ID.
     *
     * @param id O ID do estilo a ser deletado.
     */
    public void deletarEstilo(Long id) {
        estiloRepository.deleteById(id);
    }

    /**
     * Este método busca um estilo pelo seu ID e retorna o estilo mapeado para o DTO.
     *
     * @param id O ID do estilo a ser buscado.
     * @return O `EstiloListagemDto` do estilo encontrado.
     */
    public EstiloListagemDto buscarEstiloPorId(Long id) {
        Estilo estilo = estiloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estilo não encontrado com o id: " + id));
        return EstiloMapper.toDto(estilo);
    }

    /**
     * Este método retorna uma lista de todos os estilos mapeados para o DTO `EstiloListagemDto`.
     *
     * @return Uma lista de `EstiloListagemDto`.
     */
    public List<EstiloListagemDto> listarEstilos() {
        List<Estilo> estilos = estiloRepository.findAll();
        return EstiloMapper.toDto(estilos);
    }

    /**
     * Este método retorna o estilo mais cadastrado.
     *
     * @return O `Estilo` mais cadastrado.
     */
    public Estilo findEstiloMaisCadastrado() {
        return estiloRepository.findEstiloMaisCadastrado();
    }

    /**
     * Este método retorna os três principais estilos.
     *
     * @return Uma lista dos três principais `EstiloCountDto`.
     */
    public List<EstiloCountDto> findTop3Estilos() {
        return estiloRepository.findTop3Estilos();
    }
}
