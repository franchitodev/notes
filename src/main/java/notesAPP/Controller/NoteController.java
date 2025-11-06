package notesAPP.Controller;
import notesAPP.Models.Note;
import notesAPP.Service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
    =-=-=  Controlador - Notas =-=-=
*/

@Controller
@RequestMapping("/")
public class NoteController
{

    private final NoteService noteService;
    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @ModelAttribute("newNote")
    public Note newNote() {
        return new Note("",""); // nota vacía
    }


//  ==== Endpoints ====
    @GetMapping("/")
    public String listNotes(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser)
    {
        model.addAttribute("notes", noteService.findAllFor(authUser.getUsername()));
        return "index";
    }

    @GetMapping("/notes")
    public String listNotesAlias(Model model,
                                 @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        model.addAttribute("notes", noteService.findAllFor(authUser.getUsername()));
        return "index";
    }

    @GetMapping("/notes/{id}")
    public String viewNote(@PathVariable Long id, Model model,@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser)
    {
        var note = noteService.findByIdFor(id, authUser.getUsername()).orElse(null);
        if (note == null) return "redirect:/";
        model.addAttribute("note", note);
        return "view-note";
    }

    @PostMapping("/notes/new")
    public String createNote(@ModelAttribute("newNote") Note note,@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser)
    {
        noteService.createFor(note, authUser.getUsername());
        return "redirect:/";
    }

    @DeleteMapping("/notes/{id}")
    public String deleteNote(@PathVariable Long id,@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser)
    {
        noteService.deleteByIdFor(id, authUser.getUsername());
        return "redirect:/";
    }

    @PutMapping("/notes/{id}")
    public String updateNote(@PathVariable Long id,@ModelAttribute("note") Note updatedNote,@AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser)
    {
        noteService.updateFor(id, updatedNote, authUser.getUsername());
        return "redirect:/notes/" + id;
    }
}