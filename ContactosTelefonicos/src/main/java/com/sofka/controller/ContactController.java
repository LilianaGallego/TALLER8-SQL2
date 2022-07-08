package com.sofka.controller;

import com.sofka.domain.ContactDomain;
import com.sofka.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ContactController{

    @Autowired
    private ContactService contactService;

    @GetMapping(path="/")
    public String index(){
        return "hola mundo";
    }
    
    @GetMapping(path="/contacts")
    public List<ContactDomain> listed(){
        return contactService.list();
    }

    @PostMapping(path="/contact")
    public ResponseEntity<ContactDomain> create(ContactDomain contact){
        log.info("Contacto a crear: {}",contact);
        contactService.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }


    @DeleteMapping(path="/contact/{id}")
    public ResponseEntity<ContactDomain> delete(ContactDomain contact){
        log.info("Contacto a borrar: {}",contact);
        contactService.delete(contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping(path="/contact/{id}")
    public ResponseEntity<ContactDomain> update(ContactDomain contact, @PathVariable("id") Long id ){
        log.info("Contacto a borrar: {}",contact);
        contactService.update(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path="/contact/name/{id}")
    public ResponseEntity<ContactDomain> updateName(ContactDomain contact, @PathVariable("id") Long id ){
        contactService.updateName(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path="/contact/phone/{id}")
    public ResponseEntity<ContactDomain> updatePhone(ContactDomain contact, @PathVariable("id") Long id ){
        contactService.updatePhone(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path="/contact/email/{id}")
    public ResponseEntity<ContactDomain> updateEmail(ContactDomain contact, @PathVariable("id") Long id ){
        contactService.updateEmail(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PatchMapping(path="/contact/birthday/{id}")
    public ResponseEntity<ContactDomain> updateBirthday(ContactDomain contact, @PathVariable("id") Long id ){
        contactService.updateBirthday(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
