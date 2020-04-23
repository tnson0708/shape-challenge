package tns.shapechallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    public Category() {
    }

    public Category(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    @EmbeddedId
    private CategoryId categoryId;
    @Transient
    private String shapeName;

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    @ManyToOne()
    @JoinColumn
    private Shape shape;
}
