package coysevox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coysevox.Entities.Devi;

@Repository
public interface DeviRepository extends JpaRepository<Devi,Long> {
}
