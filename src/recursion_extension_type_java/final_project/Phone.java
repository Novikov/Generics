package recursion_extension_type_java.final_project;

public class Phone extends Product<Phone> {
    int model;

    @Override
    boolean subCompare(Phone p) {
        if (p instanceof Phone) {

            if (this.model > ((Phone) p).model) {
                return true;
            } else return false;
        }
        else return false;
    }
}
