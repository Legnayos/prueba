package com.reto03.grupog1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog1.Entities.Admin;
import com.reto03.grupog1.Services.AdminService;

@RestController
@RequestMapping("/api/Admins")
public class AdminController {
    @Autowired
    private AdminService carService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAll(){
        return (List<Admin>) carService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin car) {
        return (Admin) carService.addAdmin(car);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin updAdmin(@RequestBody Admin car){
        return (Admin) carService.updAdmin(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delAdmin(@PathVariable Integer id){
        carService.delAdmin(id);
    }
    
    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin getAdmin(@PathVariable Integer id){
        return carService.getAdmin(id);
    }

    
}
