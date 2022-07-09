package com.sofka.service;

import com.sofka.domain.ContactDomain;
import com.sofka.domain.PhoneDomain;
import com.sofka.repository.ContactRepository;
import com.sofka.repository.PhoneRepository;
import com.sofka.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * Clase tipo Servicio para el manejo del directorio telefonico
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
public class ContactsService implements IContactService {

    /**
     * Repositorio del Contacto
     */
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Repositorio del Telefono
     */
    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * Devuelve una lista de Contactos con todos los contactos guardados
     *
     * @return
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactDomain> getList() {
        return contactRepository.findAll();
    }

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema ordenados por el campo indicado ya sea ascendente
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de contactos
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactDomain> getList(String field, Sort.Direction order) {
        return contactRepository.findAll(Sort.by(order, field));
    }

    /**
     * Busca un dato entre el nombre o los apellidos en un contacto
     *
     * @param dataToSearch Dato a buscar
     * @return Lita de contactos
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactDomain> searchContacto(String dataToSearch) {
        var contacto1 = contactRepository.findByFullNameStartingWith(dataToSearch);
        var contacto2 = contactRepository.findByFullNameContains(dataToSearch);
        var contacto3 = contactRepository.findByFullNameEndingWith(dataToSearch);
        var answer = new HashSet<ContactDomain>();
        answer.addAll(contacto1);
        answer.addAll(contacto2);
        answer.addAll(contacto3);
        return answer.stream().toList();
    }

    /**
     * Crea un contacto en el sistema
     *
     * @param contact Objeto del contacto a crear
     * @return Objeto del contacto creado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain createContact(ContactDomain contact) {
        return contactRepository.save(contact);
    }

    /**
     * Crea un teléfono en el sistema a nombre de un contacto
     *
     * @param phone Objeto del teléfono a crear
     * @return Objeto del teléfono creado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public PhoneDomain createPhone(PhoneDomain phone) {
        return phoneRepository.save(phone);
    }

    /**
     * Actualiza una tupla completa de un contacto
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain updateContact(Integer id, ContactDomain contact) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    /**
     * Actualiza el nombre de un contacto
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain updateFullName(Integer id, ContactDomain contact) {
        contact.setId(id);
        contactRepository.updateNombre(id, contact.getFullName());
        return contact;
    }

     /**
     * Actualiza el correo electronico de un contacto
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain updateEmail(Integer id, ContactDomain contact) {
        contact.setId(id);
        contactRepository.updateEmail(id, contact.getEmail());
        return contact;
    }

    /**
     * Actualiza la fecha de nacimiento de un contacto
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain updateBirthday(Integer id, ContactDomain contact) {
        contact.setId(id);
        contactRepository.updateBirthday(id, contact.getBirthday());
        return contact;
    }

    /**
     * Actualiza la tupla completa de un teléfono en el sistema
     *
     * @param id Identificador del teléfono a actualizar
     * @param phone Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public PhoneDomain updatePhone(Integer id, PhoneDomain phone) {
        phone.setId(id);
        phoneRepository.save(phone);
        return phone;
    }

    /**
     * Actualiza solamente el numero del teléfono de un contacto a partir del ID de la tupla del teléfono
     *
     * @param id Identificador del teléfono a actualizar
     * @param phone Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public PhoneDomain updateOnlyNumber(Integer id, PhoneDomain phone) {
        phone.setId(id);
        phoneRepository.updateNumberPhone(id, phone.getNumber());
        return phone;
    }

    /**
     * Borra un contacto del sistema
     *
     * @param id Identificación del contacto a borrar
     * @return Objeto del contacto borrado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public ContactDomain deleteContact(Integer id) {
        var contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return contact.get();
        } else {
            return null;
        }
    }

    /**
     * Borra un teléfono del sistema
     *
     * @param id Identificación del teléfono a borrar
     * @return Objeto del teléfono borrado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public PhoneDomain deletePhone(Integer id) {
        var phone = phoneRepository.findById(id);
        if (phone.isPresent()) {
            phoneRepository.delete(phone.get());
            return phone.get();
        } else {
            return null;
        }
    }
}
