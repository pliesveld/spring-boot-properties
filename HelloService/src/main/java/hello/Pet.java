package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PET")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    @Column
    private String type;

    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && Objects.equals(type, pet.type) && Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, owner);
    }
}
