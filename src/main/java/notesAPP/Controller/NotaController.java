package notesAPP.Controller;

import notesAPP.Service.NotaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotaController
{

//  - Conexion Servicio -
    private final NotaService notasService;
    public NotaController(NotaService notasService)
    {this.notasService = notasService;}


    @GetMapping("/notes")
    public String listNotes(Model model, @AuthenticationPrincipal User authUser)
    {
        model.addAttribute("notes",notasService.findAllFor(authUser.getUsername()));
        return "notes";
    }

   }