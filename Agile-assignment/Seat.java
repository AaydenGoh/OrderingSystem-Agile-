package domain;

import java.io.Serializable;
import java.util.Objects;

public class Seat {
    
    
    private String name;
    private String date;
    private String time;
    private int noOfPeople;
    private String status;
    
    public Seat() {
    }

    public Seat(String name) {
        this.name=name;
    }

    public Seat(String name, String date, String time, int noOfPeople, String status) {
        
        this.name = name;
        this.date=date;
        this.time=time;
        this.noOfPeople=noOfPeople;
        this.status=status;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setType(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
*/
    
    public String toString() {
        return String.format("");
    }
    
}
