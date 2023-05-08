package com.reto03.grupog1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog1.CrudRepository.MessageCrudRepository;
import com.reto03.grupog1.Entities.Message;
import com.reto03.grupog1.Entities.Car;
import com.reto03.grupog1.Entities.Client;

@Repository
public class MessageRepository {
    @Autowired 
    private MessageCrudRepository messageCrudRepository;

    public List<Message> getAll(){
        return (List<Message>) messageCrudRepository.findAll();
    }

    public Message addMessage(Message message){
        if(message.getIdMessage() == null || message.getIdMessage() == 0)
            return messageCrudRepository.save(message);
        else
            return message;
    }

    public Message existMessage(Integer Id){

        Optional <Message> objMessage = messageCrudRepository.findById(Id);
        Message objMessageRespuesta;

        if(objMessage.isEmpty() == true)
            objMessageRespuesta = null;

        else{
            objMessageRespuesta = objMessage.get();
        }
        
        return objMessageRespuesta;
    }

    public Message updMessage(Message message){
        Client client = new Client(); 
        Car car = new Car();

        Message objMessage = existMessage(message.getIdMessage());
        if(objMessage == null){
            return message;
        } 

        if(message.getMessageText() !=  null) 
            objMessage.setMessageText(message.getMessageText());

        if(message.getClient().getIdClient() != null){ 
            client.setIdClient(message.getClient().getIdClient());
            objMessage.setClient(client);
        }

        if(message.getCar().getIdCar() != null){ 
            car.setIdCar(message.getCar().getIdCar());
            objMessage.setCar(car);
        }
        return messageCrudRepository.save(objMessage);

    }

    public void delMessage(Integer id){
        Message objMessage = existMessage(id);
        if(objMessage != null){
            messageCrudRepository.deleteById(id);
        }
    }

    public Message getMessage(Integer id)
    {
        Message objMessage = existMessage(id);
        if(objMessage != null)
            return objMessage;
        
        else
            return null;
    }
}
