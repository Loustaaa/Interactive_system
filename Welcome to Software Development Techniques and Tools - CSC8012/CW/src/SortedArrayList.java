import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    public void insert(E item) {
        if (this.size() == 0) {
            this.add(item);
            return;
        }
        for (int i = 0; i < this.size(); i++) {
            int comparison = item.compareTo((E) this.get(i));
            if (comparison < 0) {
                this.add(i, item);
                return;
            }
            if (comparison == 0) {
                return;
            }
        }
        this.add(item);
    }
}
