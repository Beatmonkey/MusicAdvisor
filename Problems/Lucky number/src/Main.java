import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("");

        int fistHalfSum = 0;
        int secondHalfSum = 0;

        for (int i = 0; i < input.length; i++) {
            if (i < input.length / 2) {
                fistHalfSum += Integer.parseInt(input[i]);
            } else {
                secondHalfSum += Integer.parseInt(input[i]);
            }


        }



        if (fistHalfSum == secondHalfSum) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}