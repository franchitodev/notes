package notesAPP.Service;
import notesAPP.Models.WebAppUser;
import notesAPP.Repository.WebAppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
    =-=-=  Logica para la autenticacion =-=-=
 */



@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private final WebAppUserRepository repo;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailsService(WebAppUserRepository repo, PasswordEncoder passwordEncoder)
    {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        WebAppUser userz = repo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username Or Password incorrect"));

        return org.springframework.security.core.userdetails.User.
                withUsername(userz.getUsername())
                .password(userz.getPassword())
                .authorities(new SimpleGrantedAuthority(userz.getRole()))
                .build();
    }


    public WebAppUser registerUser(WebAppUser user)
    {
        if (repo.findByUsername(user.getUsername()).isPresent())
        {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        try
        {
            return repo.save(user);
        } catch (Exception e)
        {
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }
    }


    public WebAppUser loginUser(String username, String rawPassword)
    {
        return repo.findByUsername(username).filter(user ->
                passwordEncoder.matches(rawPassword,user.getPassword()))
                .orElseThrow( () -> new IllegalArgumentException("Username or password incorrect"));
    }

}
