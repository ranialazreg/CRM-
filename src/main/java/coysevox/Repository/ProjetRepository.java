package coysevox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coysevox.Entities.Projet;


@Repository
public interface ProjetRepository  extends JpaRepository<Projet,Long> {
}
