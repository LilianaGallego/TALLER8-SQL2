package com.sofka.repository;

import com.sofka.domain.ContactDomain;
import com.sofka.domain.PhoneDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para la entidad Telefono
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
public interface PhoneRepository extends JpaRepository<PhoneDomain, Integer> {

    /**
     * Actualiza solamente el numero del telefono basado en el identificador de la tupla
     *
     * @param id
     * @param number
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Modifying
    @Query(value = "update PhoneDomain pho set pho.number = :number where pho.id = :id")
    public void updateNumberPhone(@Param(value = "id") Integer id, @Param(value = "number") String number);

    /**
     * Selecciona los teléfonos de un contacto en específico
     *
     * @param contact Objeto del contacto
     * @return Listado de teléfonos encontrados
     *
     * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
     * @since 1.0.0
     */
    @Query(value = "SELECT pho FROM PhoneDomain pho WHERE pho.contact = :contact")
    public List<PhoneDomain> findAllByContact(@Param(value = "contact") ContactDomain contact);
}