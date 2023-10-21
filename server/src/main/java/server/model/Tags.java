package server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Tags")
@Getter
@Setter
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "note_id")
    private int noteId;
    private String description;
}
