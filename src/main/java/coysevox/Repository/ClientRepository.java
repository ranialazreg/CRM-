package coysevox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coysevox.Entities.client;


@Repository
public interface ClientRepository extends JpaRepository<client,Long> {
}
