package org.acme.hibernate.orm.panache.service;

import org.acme.hibernate.orm.panache.model.Pasien;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class PasienService {

    @Transactional
    public JsonObject updateNama(String namaLama, String namaBaru){
    Pasien.update("nama = ?1 where nama = ?2", namaBaru, namaLama);
    return new JsonObject();
    }
}
