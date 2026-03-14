package instituicao.ensino.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "papeis")
public class Papel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //role
    @Column(unique = true)
    private String papel;
}