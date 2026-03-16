package instituicao.ensino.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioTurmaDTO {

    @NotNull(message = "O id do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "O id da turma é obrigatório")
    private Long turmaId;

    private BigDecimal nota;
}
