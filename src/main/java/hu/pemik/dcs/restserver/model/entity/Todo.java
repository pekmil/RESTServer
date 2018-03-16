package hu.pemik.dcs.restserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.pemik.dcs.restserver.database.Model;

import java.util.Objects;

/**
 * @author pekmil
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo extends Model {

    private static final long serialVersionUID = 10;

    public int id;
    public String name;
    public String description;
    public String userName;

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Todo other = (Todo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
