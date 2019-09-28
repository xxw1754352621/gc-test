public class ThreadLimit {
    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) { }
            }).start();
        }
    }
}
