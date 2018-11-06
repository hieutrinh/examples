package reflection;

import java.lang.reflect.Method;

public class AccessPrivateMethod {
    public static void main(String[] args) throws Exception {

//        Class c = Class.forName("PrivateMethodClass");
        Class c = PrivateMethodClass.class;
        Object o = c.newInstance();
        Method m = c.getDeclaredMethod("print", null);
        m.setAccessible(true);
        m.invoke(o, null);
    }
}
