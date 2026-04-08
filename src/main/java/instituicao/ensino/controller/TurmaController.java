package instituicao.ensino.controller;

import instituicao.ensino.model.dto.TurmaDTO;
import instituicao.ensino.model.dto.UsuarioTurmaResponse;
import instituicao.ensino.model.entity.Turma;
import instituicao.ensino.model.service.TurmaService;
import instituicao.ensino.model.service.UsuarioTurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @Autowired
    private UsuarioTurmaService usuarioTurmaService;

    // lista todas as turmas
    @GetMapping
    public ResponseEntity<?> list() {
        List<Turma> professors = turmaService.findAll();
        return ResponseEntity.ok(professors);
    }

    // Encontra uma turma por id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma);
    }

    // Lista usuários inscritos na turma (com tipo e nota quando aluno)
    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioTurmaResponse>> listarUsuarios(@PathVariable Long id) {
        List<UsuarioTurmaResponse> usuarios = usuarioTurmaService.findByTurmaId(id);
        return ResponseEntity.ok(usuarios);
    }

    // Cria uma nova turma
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TurmaDTO turma) throws Exception {
        Turma saved = turmaService.create(turma);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

//    // atualiza uma turma
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Turma turma) throws Exception {
//        Turma saved = turmaService.update(id, turma);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(saved);
//    }
//
    // deleta uma turma
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

