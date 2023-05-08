package com.reto03.grupog1.DTO;

import java.io.Serializable;

import com.reto03.grupog1.Entities.Client;

public class ClientReport implements Serializable{
    
    private Long total;
    private Client client;

    public ClientReport() {
    }

    public ClientReport(Long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
}
