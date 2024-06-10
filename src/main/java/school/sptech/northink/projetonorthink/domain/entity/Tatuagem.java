package school.sptech.northink.projetonorthink.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tatuagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estilo;
    private Double preco;
    private String Descricao;
    @ManyToOne
    private Usuario fkUsuario;

}
