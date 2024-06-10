package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.repository.EnderecoRepository;
import school.sptech.northink.projetonorthink.domain.repository.EstudioRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EnderecoService {

    @Autowired
    private final EnderecoRepository repository;

    @Autowired
    private final EnderecoRepository enderecoRepository;
    @Autowired
    private EstudioRepository estudioRepository;

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado"));
    }

    public Endereco salvar(Endereco endereco, Long estudioId) {
        endereco.setEstudio(estudioRepository.findById(estudioId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show não encontrado")));
        return repository.save(endereco);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado");
        repository.deleteById(id);
    }
}
