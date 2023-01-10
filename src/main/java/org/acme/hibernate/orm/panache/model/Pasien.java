package org.acme.hibernate.orm.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pasien extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false)
    public String id;
    @Column(length = 50)
    public String nama;

    @Column(length = 20)
    public String no_bpjs;

    @Column(length = 50)
    public String nik;

    @Column(length = 50)
    public String gender;

    @Column(length = 50)
    public String status;

    @Column(length = 50)
    public String nama_ibu;

    @Column(length = 50)
    public String nama_ayah;
}

