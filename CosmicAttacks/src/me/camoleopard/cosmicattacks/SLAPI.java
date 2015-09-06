package me.camoleopard.cosmicattacks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class SLAPI
{
	public static void save(Object obj,String path) throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	public static Object load(String path) throws Exception
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return result;
	}
	
	public static void saveHash(HashMap<String, String> hash, String path)throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(hash);
		oos.flush();
		oos.close();
	}
	
	public static HashMap<String,String> loadHash(String path) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return (HashMap<String,String>)result;
	}
	public static void saveHash1(HashMap<String, List<String>> hash, String path)throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(hash);
		oos.flush();
		oos.close();
	}
	
	public static HashMap<String,List<String>> loadHash1(String path) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return (HashMap<String,List<String>>)result;
	}
	
	
}
