package school.sptech.northink.projetonorthink.api.controller.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.northink.projetonorthink.domain.entity.Endereco;
import school.sptech.northink.projetonorthink.domain.service.usuario.EnderecoService;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoCriacaoDto;
import school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco.EnderecoListagemDto;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class    EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> criar(@RequestBody EnderecoCriacaoDto enderecoDto) {
        Endereco endereco = enderecoService.salvar(enderecoDto, enderecoDto.getFkEstudio());
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoListagemDto>> listar() {
        return ResponseEntity.ok(enderecoService.listar());
    }
}
