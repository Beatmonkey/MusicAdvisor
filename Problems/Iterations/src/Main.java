class IteratorExecutor {

    static void performIterationsWithCallback(int numberOfIterations, LoopCallback callback) {
        for (int i = 0; i < numberOfIterations; i++) {
            callback.onNewIteration(i);
        }
    }

    static void startIterations(int numberOfIterations) {
        performIterationsWithCallback(numberOfIterations, new LoopCallback() {
            @Override
            public void onNewIteration(int iteration) {
                System.out.println("Iteration: " + iteration);
            }
        });

    }
}

// Don't change the code below
interface LoopCallback {

    void onNewIteration(int iteration);

}

interface Message {



    String getSender();
    String getDestination();
    String getText();
}

abstract class BaseMessage implements Message {
    protected String sender;
    protected String dest;

    public BaseMessage(String sn, String dst) {
        sender = sn;
        dest = dst;
    }

    @Override
    public String getSender() {
        return sender;
    }
}

