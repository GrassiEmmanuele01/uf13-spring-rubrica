package it.marconi.rubrica.controllers;

import java.util.UUID;

import javax.naming.Binding;

import java.net.http.HttpClient.Redirect;
import java.util.Optional;

import org.springframework.aot.hint.BindingReflectionHintsRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.marconi.rubrica.domain.Contact;
import it.marconi.rubrica.domain.ContactForm;
import it.marconi.rubrica.services.ContactService;
import jakarta.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ModelAndView showContactList(){
        //passo alla webpage  la lista dei contatti letta dal db
        return new ModelAndView("contact-list").addObject("contacts", contactService.findAll());
    }

    @GetMapping("/new")
    public ModelAndView newContactForm(){
        //passo una istanza vuota di form alla pagina web
        return new ModelAndView("contact-form").addObject(new ContactForm());
    }

    @PostMapping("/new")
    public ModelAndView handleNewContact(
        @ModelAttribute @Valid ContactForm contactForm,
        BindingResult br, //esito della validazione (subito dopo patametro da validare)
        RedirectAttributes ra
    ){
        //controllo esito della validazione
        if (br.hasErrors()){
            return new ModelAndView("contact-form");
        }

        //aggiungo un parametro speciale che sopravviva al redirect
        ra.addFlashAttribute("newContact",true);
        Contact c =contactService.save(contactForm);
        return new ModelAndView("redirect:/contact?id="+c.getId());
    }

    @GetMapping(path="contact", params="id")
    public ModelAndView showContact(@RequestParam("id") UUID contactID){
        
        Optional<Contact> opContact = contactService.get(contactID);
        //controllo se il dato è presente
        if (opContact.isPresent()){
            return new ModelAndView("contact-detail").addObject(opContact.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contatto non trovato");
        }   
    }

    @GetMapping(path = "contact/delete/{id}")
    public ModelAndView deleteContact(
        @PathVariable("id") 
        UUID contactId,
        RedirectAttributes attr
        ){
        contactService.deleteById(contactId);

        attr.addFlashAttribute("contactDeleted", true);
        return new ModelAndView("redirect:/");
        
    }
}
