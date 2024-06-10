package school.sptech.northink.projetonorthink.domain.entity;
import jakarta.persistence.*;
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
    private String CEP;
    private String bairro;
    private String cidade;
    private String estado;

    @OneToOne
    private Estudio estudio;


}
