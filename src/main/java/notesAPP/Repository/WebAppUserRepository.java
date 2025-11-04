
package notesAPP.Repository;

// --- Capa Acceso a datos ---

import notesAPP.Models.WebAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebAppUserRepository extends JpaRepository<WebAppUser, Long>
{
    Optional<WebAppUser> findByUsername(String username);
}


