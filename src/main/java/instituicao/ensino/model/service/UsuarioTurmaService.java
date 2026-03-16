package instituicao.ensino.model.service;

import instituicao.ensino.exeption.ApiException;
import instituicao.ensino.model.dto.UsuarioTurmaDTO;
import instituicao.ensino.model.dto.UsuarioTurmaResponse;
import instituicao.ensino.model.entity.Turma;
import instituicao.ensino.model.entity.Usuario;
import instituicao.ensino.model.entity.UsuarioTurma;
import instituicao.ensino.model.repository.TurmaRepository;
import instituicao.ensino.model.repository.UsuarioRepository;
import instituicao.ensino.model.repository.UsuarioTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioTurmaService {

    private static final String PAPEL_ALUNO = "aluno";

    @Autowired
    private UsuarioTurmaRepository usuarioTurmaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    private boolean isAluno(Usuario usuario) {
        return usuario.getPapel() != null
                && PAPEL_ALUNO.equalsIgnoreCase(usuario.getPapel().getPapel());
    }

    @Transactional
    public UsuarioTurmaResponse inscrever(UsuarioTurmaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND));
        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ApiException("Turma não encontrada", HttpStatus.NOT_FOUND));

        if (usuarioTurmaRepository.existsByUsuarioIdAndTurmaId(usuario.getId(), turma.getId())) {
            throw new ApiException("Usuário já está inscrito nesta turma", HttpStatus.BAD_REQUEST);
        }

        if (dto.getNota() != null && !isAluno(usuario)) {
            throw new ApiException("Apenas alunos podem ter nota. Este usuário é professor.", HttpStatus.BAD_REQUEST);
        }

        UsuarioTurma ut = new UsuarioTurma();
        ut.setUsuario(usuario);
        ut.setTurma(turma);
        ut.setNota(isAluno(usuario) ? dto.getNota() : null);

        ut = usuarioTurmaRepository.save(ut);
        return toResponse(ut);
    }

    public UsuarioTurmaResponse findById(Long id) {
        UsuarioTurma ut = usuarioTurmaRepository.findById(id)
                .orElseThrow(() -> new ApiException("Inscrição não encontrada", HttpStatus.NOT_FOUND));
        return toResponse(ut);
    }

    public List<UsuarioTurmaResponse> findByTurmaId(Long turmaId) {
        if (!turmaRepository.existsById(turmaId)) {
            throw new ApiException("Turma não encontrada", HttpStatus.NOT_FOUND);
        }
        return usuarioTurmaRepository.findByTurmaId(turmaId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioTurmaResponse> findByUsuarioId(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        return usuarioTurmaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioTurmaResponse atualizarNota(Long id, BigDecimal nota) {
        UsuarioTurma ut = usuarioTurmaRepository.findById(id)
                .orElseThrow(() -> new ApiException("Inscrição não encontrada", HttpStatus.NOT_FOUND));
        if (!isAluno(ut.getUsuario())) {
            throw new ApiException("Apenas alunos podem ter nota. Este usuário é professor.", HttpStatus.BAD_REQUEST);
        }
        ut.setNota(nota);
        ut = usuarioTurmaRepository.save(ut);
        return toResponse(ut);
    }

    @Transactional
    public void remover(Long id) {
        UsuarioTurma ut = usuarioTurmaRepository.findById(id)
                .orElseThrow(() -> new ApiException("Inscrição não encontrada", HttpStatus.NOT_FOUND));
        usuarioTurmaRepository.delete(ut);
    }

    private UsuarioTurmaResponse toResponse(UsuarioTurma ut) {
        Usuario u = ut.getUsuario();
        Turma t = ut.getTurma();
        String tipo = u.getPapel() != null ? u.getPapel().getPapel() : null;
        return new UsuarioTurmaResponse(
                ut.getId(),
                u.getId(),
                u.getNome(),
                tipo,
                t.getId(),
                t.getNome(),
                ut.getNota()
        );
    }
}
