
package notesAPP.Controller;
import jakarta.validation.Valid;
import notesAPP.Models.WebAppUser;
import notesAPP.Repository.WebAppUserRepository;
import notesAPP.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/*
    =-=-=  Controlador - Autenticación =-=-=
*/

@Controller
public class AuthController
{

    private final WebAppUserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AuthController(WebAppUserRepository userRepository, CustomUserDetailsService userDetailsService)
    {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String login(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
        return "login";
    }


    @GetMapping("/signup")
    public String signup(Model model, CsrfToken csrfToken)
    {
        model.addAttribute("_csrf", csrfToken);
        return "signup";
    }

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<WebAppUser> signup(@Valid @ModelAttribute WebAppUser userForm)
    {
        try
        {
            WebAppUser newUser = userDetailsService.registerUser(userForm);
            return ResponseEntity.ok(newUser);
        }
        catch (IllegalArgumentException err)
        {
            System.out.println("Error durante el registro: " + err.getMessage());
            return ResponseEntity.status(401).build();
        }
    }


 }






