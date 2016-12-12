package domain;

import java.io.Serializable;
import java.util.Objects;

public class Food {
    
    private String ID;
    private String name;
    private String type;
    private String description;
    private double price;
    
    public Food() {
    }

    public Food(String ID) {
        this.ID = ID;
    }

    public Food(String ID, String name, String type, String description, double price) {
        this.ID = ID;
        this.name = name;
        this.type=type;
        this.description=description;
        this.price=price;
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String faculty) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
