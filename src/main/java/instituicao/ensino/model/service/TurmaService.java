package instituicao.ensino.model.service;

import instituicao.ensino.model.dto.TurmaDTO;
import instituicao.ensino.model.entity.Turma;
import instituicao.ensino.model.repository.TurmaRepository;
import instituicao.ensino.model.repository.UsuarioTurmaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private UsuarioTurmaRepository usuarioTurmaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) throws Exception {
        return turmaRepository.findById(id).orElseThrow(() -> new Exception("turma com id " + id + " não encontrada"));
    }

    @Transactional
    public Turma create(TurmaDTO turmaDTO) throws Exception {

        Turma turma = new Turma();

        turma.setNome(turmaDTO.getNome());
        turma.setDescricao(turmaDTO.getDescricao());

        turmaRepository.saveAndFlush(turma);

        Optional<Turma> optionalEntity = turmaRepository.findById(turma.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new Exception("turma com id " + turma.getId() + " não encontrada"));
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new Exception("Turma com id " + id + " não encontrada"));
        usuarioTurmaRepository.deleteAll(usuarioTurmaRepository.findByTurmaId(id));
        turmaRepository.delete(turma);
    }

}
