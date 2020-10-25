package sk.jaro.fiktivnyprogram;

import sk.jaro.fiktivnyprogram.entity.Film;
import sk.jaro.fiktivnyprogram.entity.Osoba;
import sk.jaro.simpleormframework.dbaccess.SormManager;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        SormManager manager = new SormManager();
        Osoba osoba = manager.getEntityByid(1L, Osoba.class);
//        Film f = manager.getEntityByid(0L, Film.class);
        System.out.println(osoba.getId());
        System.out.println(osoba.getMeno());
        System.out.println(osoba.getPriezvsiko());
        System.out.println(osoba.getVek());
        System.out.println(osoba.getAdresa());

        Film film = new Film();
        film.setNazov("Fireproof");
        film.setReziser("Alex Kendrick");
        film.setvHlavnejUlohe("Kirk Cameron, Erin Bethea");
        manager.insertEntity(film);
    }
}
