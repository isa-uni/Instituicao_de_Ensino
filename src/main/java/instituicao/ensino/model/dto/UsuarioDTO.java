package instituicao.ensino.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    @Email
    @NotBlank(message = "O email é obrigatória")
    private String email;
    @NotBlank(message = "O cpf é obrigatória")
    private String cpf;
    @NotBlank(message = "O nome é obrigatória")
    private String nome;
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    @NotBlank(message = "O genêro é obrigatória")
    private String genero;
    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;
    @NotNull(message = "O papel é obrigatória")
    private Long papelId;
}
