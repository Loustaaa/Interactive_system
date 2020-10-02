public class Variables {

    public static void main(String[] args) {

        binaryToDecimal();
        binaryToDecimal();
        binaryToHex();

    }


    public static void binaryToDecimal() {

        String binary = "1010";
        System.out.println(Integer.parseInt(binary, 2));

    }

    public static void binaryToOctal() {
        String binary = "1010";
        System.out.println(Integer.toOctalString(Integer.parseInt(binary,
                2)));
    }

    public static void binaryToHex() {
        String binary = "1010";
        System.out.println(Integer.toHexString(Integer.parseInt(binary,
                2)));
    }
}