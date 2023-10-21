package server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="Category")
@Getter
@Setter
public class Category {
    @Id
    private int categoryId;
    private String name;

}
