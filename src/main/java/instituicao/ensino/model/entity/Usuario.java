package instituicao.ensino.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String matricula;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private Long cpf;

    private String nome;
    private String senha;
    private String genero;
    private Date data_matricula;

    @JoinTable(
            name = "papeis_usuario",
            joinColumns = @JoinColumn(name= "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_id")
    )
    private List<Papel> papeis;
}
