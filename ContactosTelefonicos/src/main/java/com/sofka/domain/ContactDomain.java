package com.sofka.domain;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entidad del Contacto
 *
 * @version 1.0.0 2022-07-08
 * @author Martha Liliana Gallego<lilianagallegom@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "contact")
public class ContactDomain {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id", nullable = false)
    private Integer id;

    /**
     * Nombre completo del contacto
     */
    @Column(name = "con_full_name", nullable = false, length = 45)
    private String fullName;

    /**
     * Correo electronico del contacto
     */
    @Column(name = "con_email", nullable = false, length = 45)
    private String email;

    /**
     * Fecha de nacimiento del contacto
     */
    @Column(name = "con_birthday", nullable = false)
    private String birthday;

    /**
     * Punto de enlace entre la entidad del Contacto y Teléfono (un contacto puede tener muchos números de teléfono)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = PhoneDomain.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "contact"
    )
    @JsonManagedReference
    private List<PhoneDomain> phones = new ArrayList<>();

}