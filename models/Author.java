package m6_streams.models;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Author {

    private String name, secondName;
    private LocalDate dateOfBird;
    private Locale nationallity;

    public Author(String name, String secondName, LocalDate dateOfBird, Locale nationallity) {
        this.name = name;
        this.secondName = secondName;
        this.dateOfBird = dateOfBird;
        this.nationallity = nationallity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateOfBird() {
        return dateOfBird;
    }

    public void setDateOfBird(LocalDate dateOfBird) {
        this.dateOfBird = dateOfBird;
    }

    public String getNationallity() {
        return nationallity.getCountry();
    }

    public void setNationallity(Locale nationallity) {
        this.nationallity = nationallity;
    }
    
    public String getCompleteName(){
        return name + " " + secondName;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", secondName=" + secondName + ", dateOfBird=" + dateOfBird + 
                ", nationallity=" + getNationallity() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.secondName);
        hash = 83 * hash + Objects.hashCode(this.dateOfBird);
        hash = 83 * hash + Objects.hashCode(this.nationallity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBird, other.dateOfBird)) {
            return false;
        }
        return Objects.equals(this.nationallity, other.nationallity);
    }
    
    
    
    
    
    
            
            
            
            
            
            
            
            
            
}
