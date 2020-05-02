package recursion_extension_type_java.starter;

public class Container <T extends Product> {
    T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
