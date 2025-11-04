package notesAPP.Models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class WebAppUser
{
    // === Constructor ===
    public WebAppUser(Long id, String username, String password, String role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public WebAppUser() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId()
    {return id;}

    public void setId(Long id)
    {this.id = id;}

    public String getRole()
    {return role;}

    public void setRole(String role)
    {this.role = role;}



}
