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

    /**
     * Este método retorna uma lista de todas as tatuagens.
     *
     * @return Uma lista de `Tatuagem`.
     */
    public List<Tatuagem> listar() {
        return repository.findAll();
    }

    /**
     * Este método busca uma tatuagem pelo seu ID.
     * Se a tatuagem não for encontrada, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID da tatuagem a ser buscada.
     * @return A `Tatuagem` encontrada.
     */
    public Tatuagem buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Este método cria uma nova tatuagem.
     * Ele primeiro busca o usuário pelo ID, define o usuário para a nova tatuagem e salva a nova tatuagem no repositório.
     *
     * @param novaTatuagem A nova tatuagem a ser criada.
     * @param usuarioId O ID do usuário que está criando a tatuagem.
     * @return A `Tatuagem` criada.
     */
    public Tatuagem criar(Tatuagem novaTatuagem, Long usuarioId) {
        Usuario usuario = usuarioService.porId(usuarioId);
        novaTatuagem.setFkUsuario(usuario);
        return repository.save(novaTatuagem);
    }

    /**
     * Este método deleta uma tatuagem pelo seu ID.
     * Se a tatuagem não for encontrada, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID da tatuagem a ser deletada.
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado");
        repository.deleteById(id);
    }
}
