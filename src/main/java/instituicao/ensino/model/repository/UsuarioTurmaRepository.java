package instituicao.ensino.model.repository;

import instituicao.ensino.model.entity.UsuarioTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioTurmaRepository extends JpaRepository<UsuarioTurma, Long> {

    List<UsuarioTurma> findByTurmaId(Long turmaId);

    List<UsuarioTurma> findByUsuarioId(Long usuarioId);

    Optional<UsuarioTurma> findByUsuarioIdAndTurmaId(Long usuarioId, Long turmaId);

    boolean existsByUsuarioIdAndTurmaId(Long usuarioId, Long turmaId);
}
