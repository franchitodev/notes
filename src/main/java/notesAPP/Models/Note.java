package notesAPP.Models;

import jakarta.persistence.*;

import java.util.Optional;

/*
    =-=-=  Modelo para las notas de los usuarios =-=-=
*/

@Entity
@Table(name = "notes")
public class Note
{

    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

//  ==== Atributos ====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",nullable = false)
    private WebAppUser owner;


//  ==== Getters & Setters ====
    public WebAppUser getOwner()
    {
        return owner;
    }

    public void setOwner(WebAppUser owner) {
        this.owner = owner;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public String getContent()
    {
        return content;
    }
}
