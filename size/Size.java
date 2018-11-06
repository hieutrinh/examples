package size;

import java.util.ArrayList;
import java.util.List;

public class Size {
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		
		System.out.println(s1.length());
		System.out.println("s1==s2 is " + s1==s2);
		System.out.println("s1==s3 is " + s1==s3);
		
		int[] ar = new int[5];
		System.out.println(ar.length);
		
		List<String> list = new ArrayList<>();
		System.out.println(list.size());

	}
}
