import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        String maxLengthWord = input[0];
        for (int i = 0; i < input.length; i++) {
            if (input[i].length() > maxLengthWord.length()){
                maxLengthWord = input[i];
            }
        }

        System.out.println(maxLengthWord);
    }
}
