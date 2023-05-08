package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Client;
import com.reto03.grupog1.Repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return (List<Client>) clientRepository.getAll();
    }

    public Client addClient(Client client) {
        boolean bGrabar = true;

        if(client.getAge() == null)
            bGrabar = false;

        if(client.getName() == null)
            bGrabar = false;

        if(client.getEmail() == null)
            bGrabar = false;

        if(client.getPassword() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return clientRepository.addClient(client);
        else
            return client;
    }

    public Client updClient(Client client){
        boolean bGrabar = true;

        if(client.getIdClient() == null || client.getIdClient() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return clientRepository.updClient(client);
        else
            return client;
    }

    public void delClient(Integer id){
        clientRepository.delClient(id);
    }

    public Client getClient(Integer id){
        return clientRepository.getClient(id);
    }
}

