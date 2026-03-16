package instituicao.ensino.model.repository;

import instituicao.ensino.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
