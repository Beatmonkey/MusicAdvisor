import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        System.out.println(result(n));


    }

    public static int result(int n) {
        return ((n + 1) * n + 2) * n + 3;
    }
}