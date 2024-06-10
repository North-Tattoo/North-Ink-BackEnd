package school.sptech.northink.projetonorthink.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Lob
    //@Column(length = 16 * 1024 * 1024) // 16 MB
    private String fotoPerfil;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String sobrenome;
    @NotNull
    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @NotBlank
    private String celular;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    @NotNull
    @NotBlank
    private String resumo;

    @NotNull
    @NotBlank
    private String anosExperiencia;

    @Positive
    private Double precoMinimo;
    private String instagram;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    private String descricao;

    @OneToMany(mappedBy = "usuario")
    private List<Estilo> estilos;

    @OneToMany(mappedBy = "usuario")
    private List<Tatuagem> tatuagens;

    @OneToOne
    private Estudio estudio;

}
