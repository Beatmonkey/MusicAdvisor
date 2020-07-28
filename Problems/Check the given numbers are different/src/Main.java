import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        System.out.println(isAllDifferent(a, b, c));

    }

    static boolean isAllDifferent(int a, int b, int c) {
        boolean isAllDifferent;
        if (a != b && b != c && c != a) {
            isAllDifferent = true;
        } else {
            isAllDifferent = false;
        }

        return isAllDifferent;
    }
}