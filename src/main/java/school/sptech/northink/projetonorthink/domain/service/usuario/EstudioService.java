package school.sptech.northink.projetonorthink.domain.service.usuario;

import lombok.AllArgsConstructor;
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

    private final EstudioRepository repository;

    private final UsuarioService usuarioService;

    public List<Estudio> listar() {
        return repository.findAll();
    }

    public Estudio buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Estudio salvar(EstudioCriacaoDto estudioCriacaoDto) {
        Estudio estudio = EstudioMapper.toEntity(estudioCriacaoDto, usuarioService);
        Estudio estudioSalvo = repository.save(estudio);

        // Buscar o usuário correspondente
        Usuario usuario = usuarioService.porId(estudioCriacaoDto.getFkUsuario());
        // Definir o estudio para o usuário
        usuario.setEstudio(estudioSalvo);
        // Salvar o usuário
        usuarioService.salvar(usuario);

        return estudioSalvo;
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}
