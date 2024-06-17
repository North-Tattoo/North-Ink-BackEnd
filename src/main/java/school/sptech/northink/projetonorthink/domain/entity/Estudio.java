package school.sptech.northink.projetonorthink.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Estudio {

    /*Classe que representa oe estudio cadastrado pelo usuario 1*1*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @OneToOne
    @JsonBackReference
    private Usuario usuario;

    @OneToOne
    private Endereco endereco;
}
