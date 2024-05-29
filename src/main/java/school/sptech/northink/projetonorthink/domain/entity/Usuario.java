package school.sptech.northink.projetonorthink.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String celular;
    private String email;
    private String senha;
    private String resumo;
    private List<String> estilos;
    private List<String> anosExperiencia;
    private Double precoMinimo;
    private String instagram;

    @OneToMany(mappedBy = "estilo")
    private List<Estilo> estilos;

    @OneToMany(mappedBy = "tatuagens")
    private List<Tatuagem> tatuagens;

}
