package instituicao.ensino.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import instituicao.ensino.model.entity.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
}
