package school.sptech.northink.projetonorthink.domain.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String rua;
    private Integer numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    @OneToOne
    @JoinColumn(name = "fkEstudio")
    @JsonBackReference
    private Estudio estudio;

}
