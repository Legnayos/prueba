package com.reto03.grupog1.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="Cars") 

@JsonPropertyOrder({"idCar", "name", "brand", "year", "description", "gama", "messages", "reservations"})
public class Car implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idCar;
    private String name;
    private String brand;
    @JsonProperty("year")
    private Integer model;
    private String description;

    @ManyToOne
    @JoinColumn(name="idGama")
    @JsonIgnoreProperties("cars") 
    private Gama gama;

    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "car" )
    @JsonIgnoreProperties({"car", "client"})
    private List <Message> messages;

    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "car" )
    @JsonIgnoreProperties("car")
    private List <Reservation> reservations;

    public Car() {
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    
}
