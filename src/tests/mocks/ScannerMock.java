package tests.mocks;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerMock {
	
	private final Scanner scanner;

	public ScannerMock(InputStream in) {
		scanner = new Scanner(in);
	}

	public String ask() {
		System.out.println("Confirm registration with the code received: ");
		return scanner.nextLine();
	}
	
	public void close() {
		scanner.close();
	}
}
