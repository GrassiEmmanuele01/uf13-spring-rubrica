package it.marconi.rubrica.domain;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               //Getter e Setter
@NoArgsConstructor  //Costruttori vuoti
@AllArgsConstructor //Costruttori con tutti i parametri
@Entity
@Table(name="contacts")
public class Contact {
    @Id             //PrimaryKey
    @GeneratedValue //Autoincrementale
    @Column(name="id")
    private UUID id;

    @Column(name="name") 
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;
}
