package notesAPP.Controller;
import notesAPP.Models.Note;
import notesAPP.Service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//=============================================================================
//    =-=-=  Controlador - Notas =-=-=
//=============================================================================

@Controller
@RequestMapping("/notes")
public class NoteController
{

    private final NoteService noteService;
    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

//  ======= Endpoints =======

    @GetMapping
    public String listNotes(Model model, Authentication authentication)
    {
        String username = authentication.getName();
        model.addAttribute("notes", noteService.findAllFor(username));
        model.addAttribute("noteForm", new Note()); // formulario vacío
        return "notes";
    }

    @PostMapping("/create")
    public String createNote(@ModelAttribute("noteForm") Note note, Authentication authentication)
    {
        String username = authentication.getName();
        noteService.createFor(note, username);
        return "redirect:/notes";
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note updated, Authentication authentication)
    {
        String username = authentication.getName();
        noteService.updateFor(id, updated, username);
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id, Authentication authentication)
    {
        String username = authentication.getName();
        noteService.deleteByIdFor(id, username);
        return "redirect:/notes";
    }
}
