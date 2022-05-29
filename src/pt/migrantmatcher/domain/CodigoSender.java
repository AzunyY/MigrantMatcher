package pt.migrantmatcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.Properties;

import pt.migrantmatcher.plugins.SenderType;

/*USAR SINGLETON*/

public class CodigoSender {

	private Properties p;

	protected CodigoSender(String fileName) {

		p = new Properties();

		try {
			p.load(new FileInputStream(new File(fileName)));

		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static CodigoSender INSTANCE = null; // Lazy loading colocar a null

	public static CodigoSender getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CodigoSender("senders.properties");
		}

		return CodigoSender.INSTANCE;
	}

	public String enviaSMS(int num) {

		String cod = generateCod();

		try {
			String className = p.getProperty("SENDERTYPE");

			Class<?> klass = Class.forName(className);
			SenderType sender = (SenderType) klass.getConstructor().newInstance();
			
			sender.enviaSMS(num, cod);
			return cod;
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error";

	}

	public String generateCod() {
		return new SecureRandom().ints(6,33,127)
				.map( x -> (char) x)
				.collect(StringBuilder::new, 
						StringBuilder::appendCodePoint,
						StringBuilder::append)
				.toString();
	}
}
