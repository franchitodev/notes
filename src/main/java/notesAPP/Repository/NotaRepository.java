package notesAPP.Repository;

// --- Capa Acceso a datos ---


import org.springframework.data.jpa.repository.JpaRepository;
import notesAPP.Models.Nota;
import org.springframework.stereotype.Repository;

/*

JpaRepository es una interfaz genérica.
Que ya trae todos los métodos CRUD listos:
    findAll() → devuelve todas las notas
    findById(Long id) → busca por ID
    save(Nota nota) → inserta o actualiza
    deleteById(Long id) → elimina


*/

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>
{}
