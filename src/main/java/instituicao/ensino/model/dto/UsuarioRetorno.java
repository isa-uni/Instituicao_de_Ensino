package instituicao.ensino.model.dto;

public record UsuarioRetorno (
        Long id,
        String nome,
        String email,
        String papel
) {}
