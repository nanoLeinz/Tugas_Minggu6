package org.acme.hibernate.orm.panache.controller;

import org.acme.hibernate.orm.panache.model.Pasien;
import org.acme.hibernate.orm.panache.service.PasienService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pasien")
public class PasienController {
    @Inject
    PasienService pasienService;

    @GET
    public Response list(@QueryParam("start") Integer start, @QueryParam("end") Integer end) {
        PanacheQuery<Pasien> listPasien = Pasien.findAll();
        listPasien.range(start, end);
        List<Pasien> firstRange = listPasien.list();
        return Response.ok().entity(firstRange).build();
    }

    @POST
    @Transactional
    public Pasien list(JsonObject payload) {
        Pasien pasiena = new Pasien();
        pasiena.nama = payload.getString("nama");
        pasiena.no_bpjs = payload.getString("no_bpjs");
//        pasien.nik = payload.getString("nik");
//        pasien.gender = payload.getString("gender");
//        pasien.status = payload.getString("status");
//        pasien.nama_ibu = payload.getString("nama_ibu");
//        pasien.nama_ayah = payload.getString("nama_ayah");
//        pasien.created_at = payload.getString("created_at");
        pasiena.persist();
        return pasiena;
    }

    @DELETE
    @Transactional
    public JsonObject delete(@QueryParam("nama") String name) {
        Pasien.delete("nama = ?1",name);
        return new JsonObject();
    }

    @PATCH
    @Transactional
    public JsonObject update(@QueryParam("nama") String nama,
                             @QueryParam("no_bpjs") String no_bpjs,
                             @QueryParam("nik") String nik,
                             @QueryParam("nama_ibu") String nama_ibu
    ) {
        Pasien.update("nama = ?1, nama_ibu = ?2 where nik = ?3 AND no_bpjs = ?4", nama, nama_ibu, nik, no_bpjs );
        return new JsonObject();
    }


}
