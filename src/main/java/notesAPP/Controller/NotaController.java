package notesAPP.Controller;

import notesAPP.Models.Nota;
import notesAPP.Service.NotaService;
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


//  - Mostrar todas las notas y formulario de crear/editar -
    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("notas", notasService.devolverNotas());
        model.addAttribute("nota", new Nota()); // formulario vacío por defecto
        return "notes";
    }

//  - Guardar o actualizar nota -
    @PostMapping("/guardar")
    public String guardarNota(@ModelAttribute Nota nota)
    {
        if (nota.getId() == null) {
            notasService.crearNota(nota);
        } else
        {
            notasService.actualizarNota(nota.getId(), nota);
        }
        return "redirect:/";
    }

//  - Editar nota -
    @GetMapping("/editar/{id}")
    public String editarNota(@PathVariable Long id, Model model) {
        Nota notaExistente = notasService.buscarNotaPorId(id);
        model.addAttribute("nota", notaExistente); // para formulario
        model.addAttribute("notas", notasService.devolverNotas()); // lista completa
        return "notes";
    }

    // Eliminar nota
    @GetMapping("/eliminar/{id}")
    public String eliminarNota(@PathVariable Long id) {
        notasService.borrarNota(id);
        return "redirect:/";
    }
}
