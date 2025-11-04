package notesAPP.Service;

import notesAPP.Models.Nota;
import notesAPP.Repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// --- Logica de negocio ---

@Service
public class NotaService
{
    private final NotaRepository repo;

    /* [i] Inyeccion del repo  */
    public NotaService(NotaRepository repo)
    {this.repo = repo;}
    

    /* === Retornar Notas === */
    public List<Nota> devolverNotas()
    {
        return repo.findAll();
    }

    /* === Crear Nota === */
    public Nota crearNota(Nota nota)
    {
        if ( nota.getTitle().isEmpty() )
        {
            throw new IllegalArgumentException("Error! - El titulo no puede estar vacio ");
        }
        return repo.save(nota);
    }

    /* === Borrar Nota === */
    public void borrarNota(Long id)
    {
        repo.deleteById(id);
    }

    /* === Actualizar Nota === */
    public Nota actualizarNota(Long id, Nota notaActualizada)
    {
        Optional<Nota> notaExistente = repo.findById(id);
        if (notaExistente.isPresent())
         {
            notaExistente.get().setTitle(notaActualizada.getTitle());
            notaExistente.get().setContent(notaActualizada.getContent());
            return repo.save(notaExistente.get());

         }
        else
            {
                throw new IllegalArgumentException("Error! - Esta nota no existe");
            }
    }

    // === Buscar Nota por id ===
    public Nota buscarNotaPorId(Long id)
    {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Error! - Nota no encontrada"));
    }



}
