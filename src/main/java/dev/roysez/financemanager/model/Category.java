package dev.roysez.financemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Category implements Comparable<Category> {

    @Override
    public int compareTo(Category o) {
        return this.categoryName.hashCode() - o.categoryName.hashCode();
    }

    Integer id;

    String categoryName;

    Integer tax;

    public Category() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + categoryName.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return  categoryName + " [" +tax + "%]";

    }
}
