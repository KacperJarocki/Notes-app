package server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String urlAddress;
    private boolean favorite;
    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts accounts;
    @OneToMany()
    @JoinColumn(name = "note_id")
    private List<Tags> tags;
}