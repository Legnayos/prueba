package com.reto03.grupog1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.GamaCrudRepository;
import com.reto03.grupog1.Entities.Gama;

@Repository
public class GamaRepository {
    @Autowired 
    private GamaCrudRepository gamaCrudRepository;

    public List<Gama> getAll(){
        return (List<Gama>) gamaCrudRepository.findAll();
    }

    public Gama addGama(Gama gama){
        System.out.println("Yendo a Repository addGama");
        if(gama.getIdGama() == null || gama.getIdGama() == 0)
            return gamaCrudRepository.save(gama);
        else
            return gama;
    }

    public Gama existGama(Integer Id){

        Optional <Gama> objGama = gamaCrudRepository.findById(Id);
        Gama objGamaRespuesta;

        if(objGama.isEmpty() == true)
            objGamaRespuesta = null;

        else{
            objGamaRespuesta = objGama.get();
        }
        
        return objGamaRespuesta;
    }

    public Gama updGama(Gama gama){
        //boolean bGrabar = true;

        Gama objGama = existGama(gama.getIdGama());
        if(objGama == null){
            return gama;
        } 
    
        if(gama.getName() != null)
            objGama.setName(gama.getName());

        if(gama.getDescription() !=  null)
            objGama.setDescription(gama.getDescription());

        return gamaCrudRepository.save(objGama);

    }

    public void delGama(Integer id){
        Gama objGama = existGama(id);
        if(objGama != null){
            gamaCrudRepository.deleteById(id);
        }
    }

    public Gama getGama(Integer id)
    {
        Gama objGama = existGama(id);
        if(objGama != null)
            return objGama;
        
        else
            return null;
    }
}
