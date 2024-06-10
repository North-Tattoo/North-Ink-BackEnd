package school.sptech.northink.projetonorthink.domain.service.usuario.dto.tatuagem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
public class TatuagemListagemDto {
    private String estilo;
    private Double preco;
    private UsuarioDto fkUsuario;
    private String imagemUrl;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UsuarioDto {
        private Long id;
        private String nome;
        private String sobrenome;
        private String cpf;
        private String celular;
        private String email;
        private String senha;
        private String resumo;
        private String anosExperiencia;
        private Double precoMinimo;
        private String instagram;
    }
}
