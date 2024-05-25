package school.sptech.northink.projetonorthink.domain.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String CEP;
    private String rua;
    private String bairro;
    private String complemento;
    private Integer numero;
    private String estado;
    private String cidade;


    // inseir o id do estudio
}
