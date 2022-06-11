package pt.migrantmatcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Random;

import pt.migrantmatcher.plugins.SenderType;

/*USAR SINGLETON*/

public class MigrantConfiguration {

	private Properties p;

	protected MigrantConfiguration(String fileName) {

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

	private static MigrantConfiguration INSTANCE = null; // Lazy loading colocar a null

	public static MigrantConfiguration getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MigrantConfiguration("senders.properties");
		}

		return MigrantConfiguration.INSTANCE;
	}

	public String enviaCod(int num) {

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
		return new Random().ints(6,33,127)
						   .map( x -> (char) x)
						   .collect(StringBuilder::new, 
								    StringBuilder::appendCodePoint,
								    StringBuilder::append)
						   .toString();
	}
	
	/*ESTAMOS A REPETIR CODIGO VAI TER DE SER ALTERADO*/

	public String enviaSMS(int tel, String string) {
		
		try {
			String className = p.getProperty("SENDERTYPE");

			Class<?> klass = Class.forName(className);
			SenderType sender = (SenderType) klass.getConstructor().newInstance();
			
			sender.enviaSMS(tel, string);
			
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
}
