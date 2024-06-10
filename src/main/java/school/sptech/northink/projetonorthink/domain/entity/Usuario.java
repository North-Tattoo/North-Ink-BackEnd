package school.sptech.northink.projetonorthink.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Lob
    //@Column(length = 16 * 1024 * 1024) // 16 MB
    private String fotoPerfil;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String celular;
    private String email;
    private String senha;
    private String resumo;

    private String anosExperiencia;

    private Double precoMinimo;
    private String instagram;
    private String novaSenha;

    @OneToMany(mappedBy = "usuario")
    private List<Estilo> estilos;

    @OneToMany(mappedBy = "usuario")
    private List<Tatuagem> tatuagens;

    @OneToOne
    private Estudio estudio;

}
