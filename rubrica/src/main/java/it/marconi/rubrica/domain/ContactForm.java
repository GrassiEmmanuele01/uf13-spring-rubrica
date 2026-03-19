package it.marconi.rubrica.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data           
@NoArgsConstructor
@AllArgsConstructor
public class ContactForm {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
