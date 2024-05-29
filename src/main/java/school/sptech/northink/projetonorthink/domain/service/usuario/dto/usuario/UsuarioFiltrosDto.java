package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import school.sptech.northink.projetonorthink.domain.entity.Estilo;

import java.util.List;

public class UsuarioFiltrosDto {

    private Double precoMin;
    private List<Estilo> estilos;
    private String nome;
    private String bairro;
    private String CEP;
    private String cidade;
}
