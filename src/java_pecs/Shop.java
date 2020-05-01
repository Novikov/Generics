package java_pecs;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    public static void main(String[] args) {
        List<Product> productsSrc = new ArrayList<>();
        productsSrc.add(new Camera());
        productsSrc.add(new Phone());
        productsSrc.add(new Camera());

        List<Product> listDst = new ArrayList<>();

        Helper helper = new Helper();
        helper.copy(productsSrc,listDst);

        for(Object p: listDst){
            System.out.println(p.getClass());
        }
    }
}

class Helper{
    public void copy(List<? extends Product> src, List<? super Product> dest){
        for (Product p : src){
            dest.add(p);
        }
    }
}
