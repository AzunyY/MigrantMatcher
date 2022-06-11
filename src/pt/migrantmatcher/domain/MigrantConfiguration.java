package pt.migrantmatcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

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
			INSTANCE = new MigrantConfiguration("defaults.properties");
		}

		return MigrantConfiguration.INSTANCE;
	}
	
	public String getProperty(String chave) {		
		return p.getProperty(chave);
	}
	
	public <T> T getClass(String klassName, T def) {
		try {
			// java -cp plugin_da_empresa_y.jar -jar polimorfismo.jar 
			Class<?> klass = Class.forName(klassName);
			
			@SuppressWarnings("unchecked")
			T s = (T) klass.getConstructor().newInstance();
			return s;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nao existe a class correspondente");
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
		return def;
	}
}
