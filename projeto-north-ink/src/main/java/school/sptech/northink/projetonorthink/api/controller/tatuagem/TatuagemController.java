package school.sptech.northink.projetonorthink.api.controller.tatuagem;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tatuagens")
@Tag(name = "Tatuagens", description = "Operações relacionadas as tatuagens")
public class TatuagemController {

    // Classe responsavel pela requisições que o usuario fará com as tatuagens

}
