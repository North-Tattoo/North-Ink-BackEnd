package school.sptech.northink.projetonorthink.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
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
    private String email;
    private String descricao;
    private Double precoMin;
    private Double anosExperiencia;

    //private String horarioFuncionamento;


}
