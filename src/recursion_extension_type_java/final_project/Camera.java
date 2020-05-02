package recursion_extension_type_java.final_project;

public class Camera extends Product<Camera> {
    int pixel;

    @Override
    boolean subCompare(Camera p) {
            if (this.pixel > ((Camera) p).pixel) {
                return true;
            } else {
                return false;
            }
    }
}
