package shut_down_hook;

public class ShutDownHookTest {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new ShutDownHook());
        System.out.println("Now main sleeping... press ctrl+c to exit");  
        try {
            Thread.sleep(500000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
