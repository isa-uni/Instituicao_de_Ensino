package instituicao.ensino.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import instituicao.ensino.model.entity.Usuario;

@Service
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
