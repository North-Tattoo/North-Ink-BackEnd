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
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoMapper;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class EnderecoService {

    @Autowired
    private final EnderecoRepository enderecoRepository;

    @Autowired
    private EstudioRepository estudioRepository;

    @Autowired
    private EstudioService estudioService;

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado"));
    }

    public Endereco salvar(EnderecoCriacaoDto enderecoCriacaoDto, Long fkEstudio) {
        Endereco endereco = EnderecoMapper.toEntity(enderecoCriacaoDto, estudioService);

        Estudio estudio = estudioRepository.findById(fkEstudio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudio não encontrado"));
        endereco.setEstudio(estudio);

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Update the Estudio with the saved Endereco and save the Estudio
        estudio.setEndereco(enderecoSalvo);
        estudioService.salvar(estudio);
        return enderecoSalvo;
    }

    public void deletar(Long id) {
        if (!enderecoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado");
        enderecoRepository.deleteById(id);
    }
}
