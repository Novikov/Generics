package recursion_extension_type_java.starter;

public class Camera extends Product {
    int pixel;

    @Override
    boolean subCompare(Product p) {
        if(p instanceof Camera) {
            if (this.pixel > ((Camera) p).pixel) {
                return true;
            } else {
                return false;
            }
        }
        else return false;
    }
}
