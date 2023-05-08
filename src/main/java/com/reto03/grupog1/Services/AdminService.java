package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Admin;
import com.reto03.grupog1.Repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll() {
        return (List<Admin>) adminRepository.getAll();
    }

    public Admin addAdmin(Admin admin) {
        boolean bGrabar = true;

        if(admin.getName() == null)
            bGrabar = false;

        if(admin.getEmail() == null)
            bGrabar = false;

        if(admin.getPassword() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return adminRepository.addAdmin(admin);
        else
            return admin;
    }

    public Admin updAdmin(Admin admin){
        boolean bGrabar = true;

        if(admin.getIdAdmin() == null || admin.getIdAdmin() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return adminRepository.updAdmin(admin);
        else
            return admin;
    }

    public void delAdmin(Integer id){
        adminRepository.delAdmin(id);
    }

    public Admin getAdmin(Integer id){
        return adminRepository.getAdmin(id);
    }
}

