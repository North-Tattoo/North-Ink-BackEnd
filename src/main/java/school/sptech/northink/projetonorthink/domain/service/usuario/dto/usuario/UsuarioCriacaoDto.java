package school.sptech.northink.projetonorthink.domain.service.usuario.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.northink.projetonorthink.domain.entity.Estilo;
import school.sptech.northink.projetonorthink.domain.entity.Tatuagem;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioCriacaoDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String nome;

    @NotNull
    @Size(min = 3, max = 30)
    private String sobrenome;

    @NotNull
    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve conter apenas dígitos e ter 11 caracteres")
    private String cpf;

    @NotNull(message = "O celular não pode ser nulo")
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^[0-9]+$", message = "O celular deve conter apenas dígitos")
    private String celular;

    @NotNull
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotNull
    @Size(min = 50, max = 500)
    private String resumo;

    private String anosExperiencia;

    @NotNull
    @NotBlank
    private Double precoMinimo;

    private String instagram;

    private List<Estilo> estilos;

    private List<Tatuagem> tatuagens;
}