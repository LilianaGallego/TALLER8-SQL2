package com.sofka.service;
import com.sofka.dao.ContactDao;
import com.sofka.domain.ContactDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService{

    @Autowired // para inyectar contacto dado
    private ContactDao contactDao;

    @Override
    @Transactional(readOnly = true)
    public List<ContactDomain> list() {
        return (List<ContactDomain>) contactDao.findAll();
    }

    @Transactional
    @Override
    public ContactDomain save(ContactDomain contact) {
        return contactDao.save(contact);
    }

    @Transactional
    public  void updateName(Long id, ContactDomain contact){
        contactDao.updateName(id, contact.getFullName());
    }

    @Transactional
    public  void updatePhone(Long id, ContactDomain contact){
        contactDao.updatePhone(id, contact.getPhone());
    }

    @Transactional
    public  void updateEmail(Long id, ContactDomain contact){
        contactDao.updateEmail(id, contact.getEmail());
    }

    @Transactional
    public  void updateBirthday(Long id, ContactDomain contact){
        contactDao.updateBirthday(id, contact.getBirthday());
    }
    @Transactional
    @Override
    public ContactDomain update(Long id, ContactDomain contact) {
        return contactDao.save(contact);
    }



    @Transactional
    @Override
    public void delete(ContactDomain contact) {
        contactDao.delete(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactDomain> findContact(ContactDomain contact) {
        return contactDao.findById(contact.getId());
    }
}
