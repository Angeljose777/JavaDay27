public class RaceConditionDemo {

    static class Counter {
        private int count = 0;

        // Uncomment this to fix the race condition
        // public synchronized void increment() {
        public void increment() {
            // synchronized (this) {
                count++;
            // }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Create two threads that increment the counter 1000 times each
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Without synchronization, the result is likely less than 2000
        System.out.println("Final count: " + counter.getCount());
    }
}
