package school.sptech.northink.projetonorthink.api.controller.tatuagem;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;
import school.sptech.northink.projetonorthink.domain.service.usuario.TatuagemService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemListagemDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem.TatuagemMapper;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/tatuagens")
@RequiredArgsConstructor
public class TatuagemController {

    private TatuagemService service;

    @PostMapping
    public ResponseEntity<TatuagemListagemDto> criar(@RequestBody TatuagemCriacaoDto tatuagemDto) {
        Tatuagem tatuagemEntity = TatuagemMapper.toEntity(tatuagemDto);

        Tatuagem tatuagemCriada = service.criar(tatuagemEntity, tatuagemDto.getUsuarioId());

        TatuagemListagemDto dto = TatuagemMapper.toDto(tatuagemCriada);

        URI uri = URI.create("/tatuagens/" + dto.getFkUsuario());
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<TatuagemListagemDto>> listar() {
        List<Tatuagem> tatuagens = service.listar();

        List<TatuagemListagemDto> dtos = TatuagemMapper.toDto(tatuagens);

        return ResponseEntity.ok(dtos);
    }

}
