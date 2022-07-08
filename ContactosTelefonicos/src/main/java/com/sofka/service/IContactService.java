package com.sofka.service;

import com.sofka.domain.ContactDomain;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    public List<ContactDomain> list();
    public ContactDomain save( ContactDomain contact);
    public ContactDomain update(Long id, ContactDomain contact);
    public void delete(ContactDomain contact);
    public Optional<ContactDomain> findContact(ContactDomain contact);
}
