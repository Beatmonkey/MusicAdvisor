class Create {

    public static void main(String[] args) {


        Runnable runnable = createRunnable("Hello", 3);
    }



    public static Runnable createRunnable(String text, int repeats) {
        for (int i = 0; i < repeats; i++) {
            System.out.println(text);
        }
        return new Runnable() {
            @Override
            public void run() {

            }
        };
        }
    }
