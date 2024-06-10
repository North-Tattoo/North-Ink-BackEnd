package school.sptech.northink.projetonorthink.domain.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String rua;

    @NotBlank
    @NotNull
    @Positive
    private Integer numero;

    private String complemento;

    @NotBlank
    @NotNull
    @Positive
    @Size(min = 9, max = 9, message = "CEP deve conter 9 caracteres")
    private String CEP;

    @NotBlank
    @NotNull
    private String bairro;

    @NotBlank
    @NotNull
    private String cidade;

    @NotBlank
    @NotNull
    private String estado;

    @OneToOne
    private Estudio estudio;


}
