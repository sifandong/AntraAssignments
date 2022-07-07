package assignments0706;

// Question 5
// create a class + function
// create two threads and print even + odd number

public class Solution5 {
    private static int num = 0;
    private static final Object lock = new Object();


    public static void main(String[] args) {
        Runnable printOdd = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock){
                        if (num % 2 != 0){
                            System.out.println(num++ + ":" + Thread.currentThread().getName());
                        }
                    }
                }
            }
        };
        Runnable printEven = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock){
                        if (num % 2 == 0){
                            System.out.println(num++ + ":" + Thread.currentThread().getName());
                        }
                    }
                }
            }
        };
        Thread t0 = new Thread(printEven);
        Thread t1 = new Thread(printOdd);
        t0.start();
        t1.start();
    }
}


