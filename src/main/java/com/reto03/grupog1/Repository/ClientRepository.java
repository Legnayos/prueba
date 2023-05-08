package com.reto03.grupog1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.ClientCrudRepository;
import com.reto03.grupog1.Entities.Client;

@Repository
public class ClientRepository {
    @Autowired 
    private ClientCrudRepository clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Client addClient(Client client){
        if(client.getIdClient() == null || client.getIdClient() == 0)
            return clientCrudRepository.save(client);
        else
            return client;
    }

    public Client existClient(Integer Id){

        Optional <Client> objClient = clientCrudRepository.findById(Id);
        Client objClientRespuesta;

        if(objClient.isEmpty() == true)
            objClientRespuesta = null;

        else{
            objClientRespuesta = objClient.get();
        }
        
        return objClientRespuesta;
    }

    public Client updClient(Client client){
        //boolean bGrabar = true;

        Client objClient = existClient(client.getIdClient());
        if(objClient == null){
            return client;
        } 

        if(client.getAge() !=  null)
            objClient.setAge(client.getAge());
            
        if(client.getPassword() != null);
            objClient.setPassword(client.getPassword());

        if(client.getName() != null)
            objClient.setName(client.getName());

        if(client.getEmail() != null)
            objClient.setEmail(client.getEmail());

        return clientCrudRepository.save(objClient);

    }

    public void delClient(Integer id){
        Client objClient = existClient(id);
        if(objClient != null){
            clientCrudRepository.deleteById(id);
        }
    }

    public Client getClient(Integer id)
    {
        Client objClient = existClient(id);
        if(objClient != null)
            return objClient;
        
        else
            return null;
    }
}
