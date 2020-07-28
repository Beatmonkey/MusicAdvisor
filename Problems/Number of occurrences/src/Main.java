import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        calcOccurrences();
    }

    public static void calcOccurrences() {

        Scanner scanner = new Scanner(System.in);


        String inputString = scanner.nextLine();
        String subString = scanner.nextLine();

//        String[] strings = inputString.split(" ");

        int counter = 0;

        if (subString.length() == 1) {
            String[] stringChars = inputString.split("");
            for (int i = 0; i < stringChars.length; i++) {
                if (subString.equals(stringChars[i])) {
                    counter++;
                }
            }

        } else {
            String[] strings = inputString.split(" ");
            for (String string : strings) {
                while (string.contains(subString)) {
                    counter++;

                    string = string.replaceAll(subString, "");

                }
            }

        }


        System.out.println(counter);
    }
}