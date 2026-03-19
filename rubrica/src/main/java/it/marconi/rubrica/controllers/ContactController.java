package it.marconi.rubrica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.rubrica.domain.Contact;
import it.marconi.rubrica.domain.ContactForm;
import it.marconi.rubrica.services.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ModelAndView showContactList(){
        return new ModelAndView("contact-list");
    }

    @GetMapping("/new")
    public ModelAndView newContactForm(){
        //passo una istanza vuota di form alla pagina web
        return new ModelAndView("contact-form").addObject(new ContactForm());
    }

    @PostMapping("/new")
    public ModelAndView handleNewContact(@ModelAttribute ContactForm contactForm){
        Contact c =contactService.save(contactForm);
        return new ModelAndView("contact-form");
    }
}
