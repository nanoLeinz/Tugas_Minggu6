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
        Pasien pasien = new Pasien();
        pasien.nama = payload.getString("nama");
        pasien.no_bpjs = payload.getString("no_bpjs");
        pasien.nik = payload.getString("nik");
        pasien.gender = payload.getString("gender");
        pasien.status = payload.getString("status");
        pasien.nama_ibu = payload.getString("nama_ibu");
        pasien.nama_ayah = payload.getString("nama_ayah");
        pasien.persist();
        return pasien;
    }

    @DELETE
    @Transactional
    public JsonObject delete(@QueryParam("nama") String name) {
        Pasien.delete("nama = ?1",name);
        return new JsonObject();
    }

    @PUT
    @Transactional
    public JsonObject update(@QueryParam("nama") String nama,
                             @QueryParam("no_bpjs") String no_bpjs,
                             @QueryParam("nik") String nik,
                             @QueryParam("nama_ibu") String nama_ibu,
                             @QueryParam("gender") String gender,
                             @QueryParam("status") String status,
                             @QueryParam("nama_ayah") String nama_ayah,
                             @QueryParam("id") String isid
    ) {
        Pasien.update("nama = ?1, nama_ibu = ?2, nik = ?3, no_bpjs = ?4, gender = ?5, status = ?6, nama_ayah = ?7 where id = ?8", nama, nama_ibu, nik, no_bpjs,gender,status,nama_ayah,isid );
        return new JsonObject();
    }

    @PATCH
    public JsonObject update(@QueryParam("namalama") String namaLama, @QueryParam("namabaru") String namaBaru) {
        pasienService.updateNama(namaLama,namaBaru);
        return new JsonObject();
    }

}
