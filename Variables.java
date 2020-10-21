import java.sql.SQLOutput;
import java.util.Scanner;

public class Variables {

    public static void main(String[] args) {

        String name = "Louie";
        int cards = 52;
        boolean happy = false;
        float change = 5.1f;

        System.out.println(name);
        System.out.println(cards);
        System.out.println(happy);
        System.out.println(change);

        Scanner obj = new Scanner(System.in);
        Scanner obj2 = new Scanner(System.in);
        System.out.println("Type something");

        String userInput = obj.nextLine();
        System.out.println("Type something else");
        String userInput2 = obj2.nextLine();
        System.out.println("You said " + userInput + " and " + userInput2);

        binaryToDecimal(); //ignore
        binaryToOctal(); //ignore
        binaryToHex(); //ignore

    }


    public static void binaryToDecimal() {
    String binary = "1001";
    int output = Integer.parseInt(binary, 2);
    System.out.println(output);


    }

    public static void binaryToOctal() {
        String binary = "1001";
        String output = Integer.toOctalString(Integer.parseInt(binary, 2));
        System.out.println(output);

    }

    public static void binaryToHex() {
        String binary = "10011101";
        String output = Integer.toHexString(Integer.parseInt(binary, 2));
        System.out.println(output);
    }
}