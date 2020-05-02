package recursion_extension_type_java.final_project;

//<T extends Product<T>> это и есть рекурсивное расширение типа
public abstract class Product <T extends Product<T>> implements Comparable<T>{
    String name;
    double price;

    @Override
    public int compareTo(T o) {
        if(this.price>o.price){
            return 1;
        }
        else if(this.price==o.price) {
            return 0;
        }

        else return -1;
    }

    abstract boolean subCompare(T p);
}
