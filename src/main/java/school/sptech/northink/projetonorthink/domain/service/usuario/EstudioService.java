package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;
import school.sptech.northink.projetonorthink.domain.repository.EstudioRepository;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.estudio.EstudioMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class EstudioService {

    @Autowired
    private final EstudioRepository estudioRepository;

    @Autowired
    private final UsuarioService usuarioService;

    /**
     * Este método retorna uma lista de todos os estúdios.
     *
     * @return Uma lista de `Estudio`.
     */
    public List<Estudio> listar() {
        return estudioRepository.findAll();
    }

    /**
     * Este método busca um estúdio pelo seu ID.
     * Se o estúdio não for encontrado, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID do estúdio a ser buscado.
     * @return O `Estudio` encontrado.
     */
    public Estudio buscarPorId(Long id) {
        return estudioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Este método cria um novo estúdio.
     * Ele primeiro verifica se o usuário já tem um estúdio. Se tiver, lança uma exceção.
     * Se não tiver, ele cria um novo estúdio, salva no repositório e atualiza o usuário com o estúdio salvo.
     *
     * @param estudioCriacaoDto O DTO contendo as informações para a criação do estúdio.
     * @return O `Estudio` criado.
     */
    public Estudio criar(EstudioCriacaoDto estudioCriacaoDto) {
        // Verificar se já existe um estúdio com o mesmo usuario_id
        Usuario usuarioExistente = usuarioService.porId(estudioCriacaoDto.getFkUsuario());
        if (usuarioExistente != null && usuarioExistente.getEstudio() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já tem um estúdio");
        }

        // Se não existir, prosseguir com a criação do estúdio
        Estudio estudio = EstudioMapper.toEntity(estudioCriacaoDto, usuarioService);
        Estudio estudioSalvo = estudioRepository.save(estudio);

        // Buscar o usuário correspondente
        Usuario usuario = usuarioService.porId(estudioCriacaoDto.getFkUsuario());
        // Definir o estudio para o usuário
        usuario.setEstudio(estudioSalvo);
        // Salvar o usuário
        usuarioService.salvar(usuario);

        return estudioSalvo;
    }

    /**
     * Este método salva um estúdio existente no repositório.
     *
     * @param estudio O `Estudio` a ser salvo.
     * @return O `Estudio` salvo.
     */
    public Estudio salvar(Estudio estudio) {
        return estudioRepository.save(estudio);
    }

    /**
     * Este método deleta um estúdio pelo seu ID.
     * Se o estúdio não for encontrado, ele lança uma exceção `ResponseStatusException` com o status `NOT_FOUND`.
     *
     * @param id O ID do estúdio a ser deletado.
     */
    public void deletar(Long id) {
        if (!estudioRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        estudioRepository.deleteById(id);
    }
}
