package notesAPP.Models;

import jakarta.persistence.*;
import java.util.List;


/*
    =-=-=  Modelo para los usuarios de la WebAPP =-=-=
 */


@Entity
@Table(name="users")
public class WebAppUser
{
    public WebAppUser(Long id, String username, String password, String role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public WebAppUser(){};


//  ==== Atributos ====

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length =30)
    private String role;


    /*
    Entidad principal (WebAppUser) posee varias notas y aseguro que las operaciones sobre la principal se propaguen a sus notas y que,
     si una nota queda sin dueño, se elimina automáticamente.
    */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Note> notes = new java.util.ArrayList<>();



//  ==== Getters & Setters ====
    public List<Note> getNotes()
    {
        return notes;
    }

    public void setNotes(List<Note> notes)
    {
        this.notes = notes;
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

}
