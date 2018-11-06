package hash_map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapExample {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        
        Map<Integer,Set<Integer>> map = new HashMap<>();
        map.put(10, set);
        map.put(20, set);
        
        Set<Integer> s1 = null;
        Set<Integer> s2 = null;
        for (Set<Integer> s : map.values()) {
            s2 = s1;
            s1 = s;
            System.out.println("size of s " + s.size());
            if (s2 != null) {
                if (s1 == s2) {
                    System.out.println("Identical");
                } else {
                    System.out.println("Not identical");
                }
            }
        }
    }
    
}
