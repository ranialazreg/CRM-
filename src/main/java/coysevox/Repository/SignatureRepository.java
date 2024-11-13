package coysevox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import coysevox.Entities.Signature;

public interface SignatureRepository extends JpaRepository<Signature, Long> {


    @Query("select S from Signature S ")
    Signature f ();

}