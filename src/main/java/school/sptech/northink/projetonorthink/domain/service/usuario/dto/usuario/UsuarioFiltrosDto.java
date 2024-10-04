package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import lombok.Data;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioFiltrosDto {

    private String nome;
    private String cidade;
    private Double precoMinimo;
    private List<Estilo> estilos;

    public static UsuarioFiltrosDto from(Usuario usuario) {
        UsuarioFiltrosDto dto = new UsuarioFiltrosDto();
        dto.setNome(usuario.getNome());
        dto.setCidade(usuario.getEstudio().getEndereco().getCidade()); // supondo que Estudio tem um método getEndereco() e Endereco tem um método getCidade()
        dto.setPrecoMinimo(usuario.getPrecoMinimo());
        dto.setEstilos(new ArrayList<>(usuario.getEstilos()));
        return dto;
    }

}
