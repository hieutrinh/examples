package throttle;

import java.util.Random;

import com.google.common.util.concurrent.RateLimiter;

public class ThrottlingExample {
    public static void main(String[] args) {
        Random ran = new Random();
        int count = 0;
        int rate = 30;
        RateLimiter throttle = RateLimiter.create(rate);
        long start = System.currentTimeMillis();
        while (count < 10 * rate) {
            try {
                throttle.acquire();
                Thread.sleep(ran.nextInt(5));
                count++;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
//            System.out.println(count++);
        }
        System.out.println(count++);
        long elapseTime = System.currentTimeMillis() - start;
        System.out.println("Rate " + (count * 1000 / elapseTime));
        System.out.println("Elapse time " + elapseTime);
    }
}
