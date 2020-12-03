/*
 * A generic class which extends superclass ArrayList. SortedArrayList will hold any object which implements the comparable interface.
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */
import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    /*
    A method used for adding new items to sortedArrayList class.
    Items are sorted in ascending order defined by their compareTo class.
    */
    public void insert(E item) {
        if (this.size() == 0) {
            this.add(item);
            return;
        }
        for (int i = 0; i < this.size(); i++) {
            int comparison = item.compareTo(this.get(i));
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
