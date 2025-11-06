package notesAPP.Service;
import notesAPP.Models.Note;
import notesAPP.Models.WebAppUser;
import notesAPP.Repository.NotaRepository;
import notesAPP.Repository.WebAppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
    =-=-=  Logica para las notas =-=-=
*/

@Service
public class NoteService
{

//   ==== Dependency Injection ====
    private final NotaRepository repoNotas;
    private final WebAppUserRepository repoUsers;


    public NoteService(NotaRepository repoNotas, WebAppUserRepository repoUsers)
    {
        this.repoNotas = repoNotas;
        this.repoUsers = repoUsers;
    }


//  ====  Methods ====

    // --- Main features ---
    public List<Note> findAllFor(String username)
    {
        return repoNotas.findByOwnerUsername(username);
    }

    public Optional<Note> findByIdFor(Long id, String username)
    {
        return repoNotas.findByIdAndOwnerUsername(id, username);
    }

    public Note createFor(Note note, String username)
    {
        Optional<WebAppUser> owner = repoUsers.findByUsername(username);
        note.setOwner(owner.get());
        return repoNotas.save(note);
    }

    public Note updateFor(Long id, Note updated, String username)
    {
        Note n = repoNotas.findByIdAndOwnerUsername(id, username).orElseThrow();
        n.setTitle(updated.getTitle());
        n.setContent(updated.getContent());
        return repoNotas.save(n);
    }

    public void deleteByIdFor(Long id, String username)
    {
        Note n = repoNotas.findByIdAndOwnerUsername(id, username).orElseThrow();
        repoNotas.delete(n);
    }
}
