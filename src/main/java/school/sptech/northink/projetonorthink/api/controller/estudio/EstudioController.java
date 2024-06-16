package school.sptech.northink.projetonorthink.api.controller.estudio;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.northink.projetonorthink.domain.entity.Estudio;
import school.sptech.northink.projetonorthink.domain.service.usuario.EstudioService;


import java.util.List;


@RestController
@RequestMapping("/estudios")
@Tag(name = "Estúdios", description = "Operações relacionadas aos estúdios")
public class EstudioController {

    @Autowired
    private EstudioService service;
    @GetMapping
    public ResponseEntity<List<Estudio>> listar() {
        List<Estudio> estudios = service.listar();

        if (estudios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(estudios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudio> buscarPorId(@PathVariable Long id) {
        Estudio estudio = this.service.buscarPorId(id);

        if(estudio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(estudio);
    }

    @PostMapping("/shows/{showId}")
    public ResponseEntity<Estudio> salvar(
            @PathVariable  Long usuarioId,
            @Valid @RequestBody Estudio estudio
    ) {
        Estudio estudioSalvo = this.service.salvar(estudio, usuarioId);

        return ResponseEntity.status(201).body(estudioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
