package school.sptech.northink.projetonorthink.domain.service.usuario.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoAtualizacaoDto {

    private String CEP;

    private String rua;

    private String bairro;

    private String complemento;

    private Integer numero;

    private String estado;

    private String cidade;


}
