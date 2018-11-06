package vmware;

public class MyString {

	public static void main(String[] args) {
		String s = "abc";
		String s0 = "abc";
		String s2 = new String("abc");
		System.out.println(s==s2);
		System.out.println(s==s0);
	}
}
