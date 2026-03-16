package instituicao.ensino.controller;

import instituicao.ensino.model.dto.AtualizarNotaDTO;
import instituicao.ensino.model.dto.UsuarioTurmaDTO;
import instituicao.ensino.model.dto.UsuarioTurmaResponse;
import instituicao.ensino.model.service.UsuarioTurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario-turma")
public class UsuarioTurmaController {

    @Autowired
    private UsuarioTurmaService usuarioTurmaService;

    @PostMapping
    public ResponseEntity<UsuarioTurmaResponse> inscrever(@Valid @RequestBody UsuarioTurmaDTO dto) {
        UsuarioTurmaResponse saved = usuarioTurmaService.inscrever(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioTurmaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioTurmaService.findById(id));
    }

    @PatchMapping("/{id}/nota")
    public ResponseEntity<UsuarioTurmaResponse> atualizarNota(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarNotaDTO dto) {
        return ResponseEntity.ok(usuarioTurmaService.atualizarNota(id, dto.getNota()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        usuarioTurmaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
