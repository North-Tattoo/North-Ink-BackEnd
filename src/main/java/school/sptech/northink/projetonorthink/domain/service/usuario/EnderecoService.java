package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.EnderecoRepository;
import school.sptech.northink.projetonorthink.domain.repository.EstudioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoMapper;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnderecoService {

    @Autowired
    private final EnderecoRepository enderecoRepository;

    @Autowired
    private EstudioRepository estudioRepository;

    @Autowired
    private EstudioService estudioService;

    /**
     * Este método retorna uma lista de endereços mapeados para o DTO `EnderecoListagemDto`.
     * Ele faz isso buscando todos os endereços do repositório e mapeando cada um para o DTO usando o `EnderecoMapper`.
     *
     * @return Uma lista de `EnderecoListagemDto`.
     */
    public List<EnderecoListagemDto> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream()
                .map(EnderecoMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Este método busca um endereço pelo seu ID.
     * Se o endereço não for encontrado, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID do endereço a ser buscado.
     * @return O `Endereco` encontrado.
     */
    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado"));
    }

    /**
     * Este método salva um novo endereço no repositório.
     * Ele primeiro verifica se o estúdio correspondente já tem um endereço. Se tiver, lança uma exceção.
     * Se não tiver, ele cria um novo endereço, salva no repositório e atualiza o estúdio com o endereço salvo.
     *
     * @param enderecoCriacaoDto O DTO contendo as informações para a criação do endereço.
     * @param fkEstudio O ID do estúdio correspondente.
     * @return O `Endereco` salvo.
     */
    public Endereco salvar(EnderecoCriacaoDto enderecoCriacaoDto, Long fkEstudio) {
        // Buscar o Estudio correspondente
        Estudio estudio = estudioRepository.findById(fkEstudio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudio não encontrado"));

        // Verificar se o Estudio já tem um Endereco
        if (estudio.getEndereco() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estudio já tem um Endereco");
        }

        // Se não tiver, prosseguir com a criação do Endereco
        Endereco endereco = EnderecoMapper.toEntity(enderecoCriacaoDto, estudioService);
        endereco.setEstudio(estudio);

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Atualizar o Estudio com o Endereco salvo e salvar o Estudio
        estudio.setEndereco(enderecoSalvo);
        estudioService.salvar(estudio);

        return enderecoSalvo;
    }

    /**
     * Este método deleta um endereço pelo seu ID.
     * Se o endereço não for encontrado, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID do endereço a ser deletado.
     */
    public void deletar(Long id) {
        if (!enderecoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado");
        enderecoRepository.deleteById(id);
    }
}
