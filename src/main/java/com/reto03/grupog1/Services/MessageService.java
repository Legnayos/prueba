package com.reto03.grupog1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog1.Entities.Message;
import com.reto03.grupog1.Repository.MessageRepository;

//import net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy.Simple;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return (List<Message>) messageRepository.getAll();
    }

    public Message addMessage(Message message) {
        boolean bGrabar = true;

        if(message.getMessageText() == null)
            bGrabar = false;

        if(message.getClient().getIdClient() == null)
            bGrabar = false;

        if(message.getCar().getIdCar() == null)
            bGrabar = false;

        if(bGrabar ==true)
            return messageRepository.addMessage(message);
        else
            return message;
    }

    public Message updMessage(Message message){
        boolean bGrabar = true;

        if(message.getIdMessage() == null || message.getIdMessage() == 0)
            bGrabar = false;

        if(bGrabar == true)
            return messageRepository.updMessage(message);
        else
            return message;
    }

    public void delMessage(Integer id){
        messageRepository.delMessage(id);
    }

    public Message getMessage(Integer id){
        return messageRepository.getMessage(id);
    }

}

