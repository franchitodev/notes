package notesAPP.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Nota
{
    // === Atributos ===
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "content", nullable = true)
    private String content;

    // === Constructor ===
    public Nota() {}
    public Nota(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    // === Getters and Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
