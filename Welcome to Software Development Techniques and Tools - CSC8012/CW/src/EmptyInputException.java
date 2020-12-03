/*
 * Exception class to be used when a user inputs nothing.
 * Code is influenced by examples in lecture materials.
 * - https://ncl.instructure.com/courses/24648/pages/lecture-notes?module_item_id=1212359
 * Original Author: Marta Koutny
 * Modifying Author: Louie Franchino
 * */

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super("No input entered.");
    }

    public EmptyInputException(String location) {
        super("No input entered for " + location + ".");
    }
}
