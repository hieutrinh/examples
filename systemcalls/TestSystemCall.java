package systemcalls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestSystemCall {

	public static void main(String[] args) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("find /Users");
			InputStream in = p.getInputStream();
			InputStream err = p.getErrorStream();
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(in));
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(err));
			String line;
			
			System.out.println("STANDARD OUT");
			while ((line = stdIn.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("STANDARD ERROR");
			while ((line = stdErr.readLine()) != null) {
				System.err.println(line);
			}

			try {
				p.waitFor();
				System.out.println(p.exitValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				stdIn.close();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
