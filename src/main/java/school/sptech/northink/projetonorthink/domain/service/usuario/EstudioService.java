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

    public List<Estudio> listar() {
        return estudioRepository.findAll();
    }

    public Estudio buscarPorId(Long id) {
        return estudioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

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

    public Estudio salvar(Estudio estudio) {
        return estudioRepository.save(estudio);
    }


    public void deletar(Long id) {
        if (!estudioRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        estudioRepository.deleteById(id);
    }
}
