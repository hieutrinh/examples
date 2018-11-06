package shut_down_hook;

/*
Java Shutdown Hook

The shutdown hook can be used to perform cleanup resource or save the state when JVM 
shuts down normally or abruptly. Performing clean resource means closing log file, sending 
some alerts or something else. So if you want to execute some code before 
JVM shuts down, use shutdown hook.

When does the JVM shut down?

The JVM shuts down when:
user presses ctrl+c on the command prompt
System.exit(int) method is invoked
user logoff
user shutdown etc.

*/

public class ShutDownHook extends Thread {
    @Override
    public void run() {
        System.out.println("shut down hook task completed.."); 
    }
}
