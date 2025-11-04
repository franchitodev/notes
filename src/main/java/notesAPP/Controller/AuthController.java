
package notesAPP.Controller;


import jakarta.validation.Valid;
import notesAPP.Models.WebAppUser; // Correcto
import notesAPP.Repository.WebAppUserRepository; // Correcto
import notesAPP.Service.CustomUserDetailsService; // Correcto
import org.springframework.beans.factory.annotation.Autowired; // Necesario para @Autowired
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity; // Necesario para ResponseEntity
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*; // Necesario para @RestController, @RequestMapping, @PostMapping

import java.awt.desktop.ScreenSleepListener;
import java.util.Optional;

@Controller
public class AuthController
{

    private final WebAppUserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AuthController(WebAppUserRepository userRepository, CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/signup")
    public String signup()
    {
        return "signup";
    }

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<WebAppUser> signup(@Valid @RequestBody WebAppUser userForm)
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

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody WebAppUser userForm) {
        try
        {
            userDetailsService.loginUser(userForm.getUsername(), userForm.getPassword());
            return  ResponseEntity.ok(userForm.getUsername());

        } catch (IllegalArgumentException e)
        {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
 }






