package cz.czechitas.java2webapps.lekce9.service;

import cz.czechitas.java2webapps.lekce9.entity.Osoba;
import cz.czechitas.java2webapps.lekce9.form.RokNarozeniForm;
import cz.czechitas.java2webapps.lekce9.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Služba pro práci s osobami a adresami.
 */
@Service
public class OsobaService {
    private final OsobaRepository osobaRepository;

    @Autowired
    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    /**
     * Vrací stránkovaný seznam všech osob v databázi seřazených podle příjmení a jména,  a datanarozeni.
     */
    public Page<Osoba> seznamOsob( Pageable pageable) {

        return osobaRepository.findByPrijmeniEqualsIgnoreCaseAndJmenoContainsOrderByPrijmeniAscJmenoAscDatumNarozeniDesc ("","", pageable) ;
    }

    /**
     * Vrací stránkovaný seznam všech osob v databázi seřazených podle příjmení .
     */
    public Page<Osoba> seznamOsobDlePrijmeni(String zacatekPrijmeni, Pageable pageable) {
        return osobaRepository.findByPrijmeniStartingWithIgnoreCaseOrderByPrijmeni(zacatekPrijmeni, pageable); // startingwithignorecase udela, ze neresi velikost pismen a najde to
    }


    /**
     * Vrací stránkovaný seznam všech osob v databázi, které se narodili mezi uvedenými roky.
     */
    public Page<Osoba> seznamDleRokuNarozeni(RokNarozeniForm form, Pageable pageable) {
      //  return osobaRepository.findByPrijmeniEqualsIgnoreCaseAndJmenoContainsOrderByPrijmeni(zacatekPrijmeni, "k", pagaeble);
         return osobaRepository.findByRok(form.getOd(), form.getDo(), pageable);
    }
}
