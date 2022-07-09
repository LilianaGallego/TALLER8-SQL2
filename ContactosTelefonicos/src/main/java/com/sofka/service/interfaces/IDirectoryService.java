package com.sofka.service.interfaces;

import com.sofka.domain.ContactDomain;
import com.sofka.domain.PhoneDomain;
import org.springframework.data.domain.Sort;

import java.util.List;


/**
 * Interface para el servicio Directorio
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
public interface IDirectoryService {


    /**
     * Devuelve una lista de Contactos con todos los contactos guardados
     *
     * @return
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public List<ContactDomain> getList();

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema ordenados por el campo indicado
     * (fullName) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de contactos
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public List<ContactDomain> getList(String field, Sort.Direction order);

    /**
     * Busca un dato dado entre el nombre y/o los apellidos en un contacto
     *
     * @param dataToSearch Dato a buscar
     * @return Lita de contactos
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public List<ContactDomain> searchContact(String dataToSearch);

    /**
     * Crea un contacto en el sistema
     *
     * @param contact Objeto del contacto a crear
     * @return Objeto del contacto creado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public ContactDomain createContact(ContactDomain contact);

    /**
     * Crea un teléfono en el sistema a nombre de un contacto
     *
     * @param phone Objeto del teléfono a crear
     * @return Objeto del teléfono creado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public PhoneDomain createPhone(PhoneDomain phone);

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
    public ContactDomain updateContact(Integer id, ContactDomain contact);

    /**
     * Actualiza el nombre de un contacto basado en su identificador
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public ContactDomain updateFullName(Integer id, ContactDomain contact);

    /**
     * Actualiza el apellido de un contacto basado en su identificador
     *
     * @param id Identificador del contacto a actualizar
     * @param contact Objeto del contacto a actualizar
     * @return Objeto del contacto actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public ContactDomain updateEmail(Integer id, ContactDomain contact);

    /**
     * Actualiza la tupla completa de un teléfono en el sistema basado en su identificador
     *
     * @param id Identificador del teléfono a actualizar
     * @param contact Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */

    public ContactDomain updateBirthday(Integer id, ContactDomain contact);

    /**
     * Actualiza la tupla completa de un teléfono en el sistema basado en su identificador
     *
     * @param id Identificador del teléfono a actualizar
     * @param phone Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */

    public PhoneDomain updatePhone(Integer id, PhoneDomain phone);

    /**
     * Actualiza solamente el teléfono de un contacto a partir del ID de la tupla del teléfono
     *
     * @param id Identificador del teléfono a actualizar
     * @param phone Objeto del teléfono a actualizar
     * @return Objeto del teléfono actualizado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    public PhoneDomain updateOnlyNumberPhone(Integer id, PhoneDomain phone);

    /**
     * Borra un contacto del sistema basado en su identificador
     *
     * @param id Identificación del contacto a borrar
     * @return Objeto del contacto borrado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    ContactDomain deleteContact(Integer id);

    /**
     * Borra un teléfono del sistema basado en su identificador
     *
     * @param id Identificación del teléfono a borrar
     * @return Objeto del teléfono borrado
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    PhoneDomain deletePhone(Integer id);
}


