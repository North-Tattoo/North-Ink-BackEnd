package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.TatuagemRepository;
import school.sptech.northink.projetonorthink.domain.repository.UsuarioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemListagemDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TatuagemService {
    private final TatuagemRepository repository;

    private final UsuarioService usuarioService;

    public List<Tatuagem> listar() {

        return repository.findAll();
    }

    public Tatuagem buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Tatuagem criar(Tatuagem novaTatuagem, Long usuarioId) {
        Usuario usuario = usuarioService.porId(usuarioId);
        novaTatuagem.setFkUsuario(usuario);
        return repository.save(novaTatuagem);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado");
        repository.deleteById(id);
    }
}
