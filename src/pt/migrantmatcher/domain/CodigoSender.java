package pt.migrantmatcher.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import pt.migrantmatcher.plugins.SenderType;

public class CodigoSender {

	public String enviaSMS(int num) {

		Properties p = new Properties();
		SenderType type = null;

		try {

			p.load(new FileInputStream(new File("sender.properties")));

			String className = p.getProperty("SENDERTYPE");

			@SuppressWarnings("unchecked")
			Class<SenderType> klass = (Class<SenderType>) Class.forName(className);
			Constructor<SenderType> cons = klass.getConstructor();
			type = cons.newInstance();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return type.enviaSMS(num);

	}
}
