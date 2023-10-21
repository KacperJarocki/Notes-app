package server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="AccountType")
@Getter
@Setter
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountTypeId;
    private String name;
    private int numberOfNotes;
    private boolean urlEdit;
}
