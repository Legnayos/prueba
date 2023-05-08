package com.reto03.grupog1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.AdminCrudRepository;
import com.reto03.grupog1.Entities.Admin;

@Repository
public class AdminRepository {
    @Autowired 
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }

    public Admin addAdmin(Admin admin){
        if(admin.getIdAdmin() == null || admin.getIdAdmin() == 0)
            return adminCrudRepository.save(admin);
        else
            return admin;
    }

    public Admin existAdmin(Integer Id){

        Optional <Admin> objAdmin = adminCrudRepository.findById(Id);
        Admin objAdminRespuesta;

        if(objAdmin.isEmpty() == true)
            objAdminRespuesta = null;

        else{
            objAdminRespuesta = objAdmin.get();
        }
        
        return objAdminRespuesta;
    }

    public Admin updAdmin(Admin admin){

        Admin objAdmin = existAdmin(admin.getIdAdmin());
        if(objAdmin == null){
            return admin;
        } 

        if(admin.getName() !=  null) 
            objAdmin.setName(admin.getName());
            
        if(admin.getEmail() != null);
            objAdmin.setEmail(admin.getEmail());

        if(admin.getPassword() != null)
            objAdmin.setPassword(admin.getPassword());

        return adminCrudRepository.save(objAdmin);

    }

    public void delAdmin(Integer id){
        Admin objAdmin = existAdmin(id);
        if(objAdmin != null){
            adminCrudRepository.deleteById(id);
        }
    }

    public Admin getAdmin(Integer id)
    {
        Admin objAdmin = existAdmin(id);
        if(objAdmin != null)
            return objAdmin;
        
        else
            return null;
    }
}
