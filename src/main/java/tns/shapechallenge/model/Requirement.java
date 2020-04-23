package tns.shapechallenge.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requirement")
public class Requirement implements Serializable {

    @Id
    private Integer id;
    private Boolean width;
    private Boolean height;
    private Boolean high;
    @Transient
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getWidth() {
        return width;
    }

    public void setWidth(Boolean width) {
        this.width = width;
    }

    public Boolean getHeight() {
        return height;
    }

    public void setHeight(Boolean height) {
        this.height = height;
    }

    public Boolean getHigh() {
        return high;
    }

    public void setHigh(Boolean high) {
        this.high = high;
    }

    @OneToOne
    @JoinColumn(name = "id")
    private Shape shape;

}
