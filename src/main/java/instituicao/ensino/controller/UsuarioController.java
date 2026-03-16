package instituicao.ensino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import instituicao.ensino.model.dto.UsuarioDTO;
import instituicao.ensino.model.dto.UsuarioTurmaResponse;
import instituicao.ensino.model.entity.Usuario;
import instituicao.ensino.model.service.UsuarioService;
import instituicao.ensino.model.service.UsuarioTurmaService;
import instituicao.ensino.util.DefaultResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioTurmaService usuarioTurmaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @Valid UsuarioDTO dto) {
        try {
            Usuario salvo = usuarioService.cadastrarUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id){
        try{
            usuarioService.deletarUsuario(id);
            return ResponseEntity.ok(
                    DefaultResponse.construir(
                            HttpStatus.OK.value(),
                            "Usuário deletado com sucesso",
                            null));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(
                            HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuario = usuarioService.ConsultarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<UsuarioTurmaResponse>> listarTurmas(@PathVariable Long id) {
        List<UsuarioTurmaResponse> turmas = usuarioTurmaService.findByUsuarioId(id);
        return ResponseEntity.ok(turmas);
    }
}
