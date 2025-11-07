package notesAPP.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import notesAPP.Models.Note;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


//=============================================================================
//    =-=-=  Repositorio - Notas =-=-=
//=============================================================================


@Repository
public interface NotaRepository extends JpaRepository<Note, Long>
{
    List<Note> findByOwnerUsername(String username);
    Optional<Note> findByIdAndOwnerUsername(Long id, String username);
}
