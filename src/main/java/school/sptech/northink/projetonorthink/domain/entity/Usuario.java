package school.sptech.northink.projetonorthink.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Lob
    //@Column(length = 16 * 1024 * 1024) // 16 MB
    private String fotoPerfil;

//    @NotNull
//    @NotBlank
    private String nome;

//    @NotNull
//    @NotBlank
    private String sobrenome;

//    @NotNull
//    @NotBlank
//    @CPF
    private String cpf;

//    @NotNull
//    @NotBlank
    private String celular;

//    @NotNull
//    @NotBlank
    private String email;

//    @NotNull
//    @NotBlank
    private String senha;

//    @NotNull
//    @NotBlank
    private String resumo;

//    @NotNull
//    @NotBlank
    private String anosExperiencia;

//    @Positive
    private Double precoMinimo;
    private String instagram;

//    @NotNull
//    @NotBlank
//    @Size(min = 1, max = 500)'
    private String descricao;

    private Boolean assinante = false;

    @ManyToMany
    @JoinTable(
            name = "usuario_estilo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "estilo_id"))
    private Set<Estilo> estilos = new HashSet<>();

    @OneToMany(mappedBy = "fkUsuario")
    private List<Tatuagem> tatuagens;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkEstudio")
    @JsonManagedReference
    private Estudio estudio;

}
