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

import pt.migrantmatcher.exceptions.PropertiesLoadingException;
 
/**
 * Le dos ficheiros e devolve configuraçoes 
 * Padrao polimorfismo - pois estamos a fazer pois estamos a usar esta classe
 * para devolver varias implementacoes 
 **/
public class MigrantConfiguration {

	private Properties props = new Properties();
	//Usa-se Singleton porque so se quer uma instancia do MigrandConfiguration em vez de se estar sempre a criar um novo
	// Lazy loading 
	private static MigrantConfiguration INSTANCE = null; 


	protected MigrantConfiguration(String fileName) throws PropertiesLoadingException {

		try {
			props.load(new FileInputStream(new File(fileName)));
		}
		catch (FileNotFoundException e) {
			throw new PropertiesLoadingException();
		} catch (IOException e) {
			throw new PropertiesLoadingException();
		}
	}


	public static MigrantConfiguration getInstance(String filename) throws PropertiesLoadingException {
		if (INSTANCE == null) {
			INSTANCE = new MigrantConfiguration(filename);
		}

		return MigrantConfiguration.INSTANCE;
	}
	
	/**
	 * Devolve uma lista de Strings que esteja no ficheiro 
	 * @key - a chave que vai ler
	 * @return lista de strings
	**/
	public List<String> getProperty(String key) throws PropertiesLoadingException {

		List<String> listString = new ArrayList <>();

		try {
			String s = props.getProperty(key);

			String [] listReg = s.split("\\s+|,\\s*|\\.\\s*");
			for(String regS : listReg)
				listString.add(regS);
			
		} catch(NullPointerException e) {
			throw new PropertiesLoadingException();
		}

		return listString;
	}
	
	/**
	 * Devolve uma instancia de uma classe que esteja no ficheiro 
	 * @key - chave onde se vai buscar o valor
	 * @return instancia de uma classe
	 **/
	public <T> T getClass(String key) throws PropertiesLoadingException {

		String klassName = (String) props.get(key);
		if (klassName == null) {
			throw new PropertiesLoadingException();
		}

		try {
			@SuppressWarnings("unchecked")
			Class<T> klass = (Class<T>) Class.forName(klassName);
			Constructor<T> c = klass.getConstructor();
			return c.newInstance();

		} catch (ClassNotFoundException e) {
			throw new PropertiesLoadingException();
		} catch (NoSuchMethodException e) {
			throw new PropertiesLoadingException();
		} catch (SecurityException e) {
			throw new PropertiesLoadingException();
		} catch (InstantiationException e) {
			throw new PropertiesLoadingException();
		} catch (IllegalAccessException e) {
			throw new PropertiesLoadingException();
		} catch (IllegalArgumentException e) {
			throw new PropertiesLoadingException();
		} catch (InvocationTargetException e) {
			throw new PropertiesLoadingException();
		}
	}
}