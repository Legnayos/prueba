package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Gama;
import com.reto03.grupog1.Repository.GamaRepository;

@Service
public class GamaService {
    @Autowired
    private GamaRepository gamaRepository;

    public List<Gama> getAll() {
        return (List<Gama>) gamaRepository.getAll();
    }

    public Gama addGama(Gama gama) {
        boolean bGrabar = true;

        System.out.println("Entra a Services addGama");

        if(gama.getDescription() == null)
            bGrabar = false;

        if(gama.getName() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return gamaRepository.addGama(gama);
        else
            return gama;
    }

    public Gama updGama(Gama gama){
        boolean bGrabar = true;

        if(gama.getIdGama() == null || gama.getIdGama() == 0)
            bGrabar = false;

        if(bGrabar == true){

            System.out.println("Yendo a Repository");
            return gamaRepository.updGama(gama);
        }
        else
            return gama;
    }

    public void delGama(Integer id){
        gamaRepository.delGama(id);
    }

    public Gama getGama(Integer id){
        return gamaRepository.getGama(id);
    }
}

