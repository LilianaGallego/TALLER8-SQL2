package com.sofka.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad del Telefono
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "phone")
public class PhoneDomain {
    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pho_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Contacto (un contacto puede tener muchos números de teléfono)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ContactDomain.class, optional = false)
    @JoinColumn(name = "pho_contact_id", nullable = false)
    @JsonBackReference
    private ContactDomain contact;

    /**
     * Número de teléfono
     */
    @Column(name = "pho_number", nullable = false, length = 15)
    private String number;



}