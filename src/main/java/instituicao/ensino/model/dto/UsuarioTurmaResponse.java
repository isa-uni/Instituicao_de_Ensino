package instituicao.ensino.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioTurmaResponse {

    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private String tipo;
    private Long turmaId;
    private String turmaNome;
    private BigDecimal nota;
}
