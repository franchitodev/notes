package notesAPP.Repository;
import notesAPP.Models.WebAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
    =-=-=  Repositorio para acceso a datos de usuarios =-=-=
*/


@Repository
public interface WebAppUserRepository extends JpaRepository<WebAppUser, Long>
{
    Optional<WebAppUser> findByUsername(String username);
    boolean existsByUsername(String username);
}


