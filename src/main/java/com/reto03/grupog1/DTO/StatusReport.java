package com.reto03.grupog1.DTO;

import java.io.Serializable;

public class StatusReport implements Serializable{
    
    private Integer completed;
    private Integer cancelled;

    public StatusReport() {
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }

    
}
