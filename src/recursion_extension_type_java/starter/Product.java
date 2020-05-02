package recursion_extension_type_java.starter;

public abstract class Product implements Comparable<Product>{
    String name;
    double price;

    @Override
    public int compareTo(Product o) {
        if(this.price>o.price){
            return 1;
        }
        else if(this.price==o.price) {
            return 0;
        }

        else return -1;
    }

    abstract boolean subCompare(Product p);
}
