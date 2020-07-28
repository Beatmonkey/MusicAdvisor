import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] date = scanner.nextLine().split("-");

        String formatDate = "";
        for (int i = date.length - 1; i >= 0; i--) {
            if (i == date.length - 1) {
                formatDate += date[i - 1] + "/";
                formatDate += date[i] + "/";
            } else if (i == 0) {
                formatDate += date[i];
            }
        }

        System.out.println(formatDate);

    }
}
