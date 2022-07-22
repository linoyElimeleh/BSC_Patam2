package tests.test6_2020_b_a;


import java.util.HashMap;
import java.util.Map;

public class Singleton {
    public static Singleton singleton;
    public static Object lock = new Object();
    public static Map<String, Object> mapSingelton = new HashMap<>();

    private Singleton() {
    }

    public static <V> V getInstance(Class<V> c) throws Exception {

        if (!mapSingelton.containsKey(c.getName())) {
            // synchorinized block
            synchronized (lock) {
                if (!mapSingelton.containsKey(c.getName())) {
                    mapSingelton.put(c.getName(), c.newInstance());
                }
            }
        }
        // recall:
        // c.newInstance() creates a new instance of type VMID
        // c.getName() returns a String - the name of type V

        return (V) mapSingelton.get(c.getName());
    }
}
