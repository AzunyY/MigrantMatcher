package pt.migrantmatcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*USAR SINGLETON*/

public class MigrantConfiguration {

	private Properties props = new Properties();
	private static MigrantConfiguration INSTANCE = null; // Lazy loading colocar a null


	protected MigrantConfiguration(String fileName) {

		try {
			props.load(new FileInputStream(new File(fileName)));
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static MigrantConfiguration getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MigrantConfiguration("defaults.properties");
		}

		return MigrantConfiguration.INSTANCE;
	}

	public List<String> getProperty(String chave, List<String> defaultValue) {
		
		List<String> listString = new ArrayList <>();
		
		try {
			for(String s : props.getProperty(chave).split("[ ,]+"))
				listString.add(s);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
		return listString;
	}

	public <T> T getClass(String key, T defaultValue) {
		
		String klassName = (String) props.get(key);
		if (klassName == null) {
			return defaultValue;
		}

		try {
			@SuppressWarnings("unchecked")
			Class<T> klass = (Class<T>) Class.forName(klassName);
			Constructor<T> c = klass.getConstructor();
			return c.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}
}