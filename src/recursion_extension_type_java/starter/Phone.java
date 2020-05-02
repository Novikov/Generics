package recursion_extension_type_java.starter;

public class Phone extends Product {
    int model;

    @Override
    boolean subCompare(Product p) {
        if (p instanceof Phone) {

            if (this.model > ((Phone) p).model) {
                return true;
            } else return false;
        }
        else return false;
    }
}
