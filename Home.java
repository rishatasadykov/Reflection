
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import pet.Pet;

public class Home {
	private Pet firstPet;
	private Pet secondPet;
	public Pet getFirstPet() {
		return firstPet;
	}
	public void setFirstPet(Pet firstPet) {
		this.firstPet = firstPet;
	}
	public Pet getSecondPet() {
		return secondPet;
	}
	public void setSecondPet(Pet secondPet) {
		this.secondPet = secondPet;
	}
	public static void main(String... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Home home = new Home();
		String inputFilePath = "C:/Java/settings.txt";
        String line;
        BufferedReader reader;
        List<String> list = new ArrayList<>();
        try {
        	reader = new BufferedReader(new FileReader(inputFilePath));
			while ((line = reader.readLine()) != null) {
				String[] spline = line.split(" ");
				list.add(spline[0]);
				list.add(spline[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        ArrayList<Pet> pets = new ArrayList<>();
        for (int i = 1; i < list.size(); i+=2) {
        	try {
            	Class p = Class.forName(list.get(i));
    			Pet pet = (Pet) p.newInstance();
    			pets.add(pet);
    			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	
        }
        Class clazz = home.getClass();
		Method[] methods = clazz.getMethods();
        for (Method method : methods) {
			String name = method.getName();
			if (name == "setFirstPet") {
				method.invoke(home, pets.get(0));
				
			}
			if (name == "setSecondPet") {
				method.invoke(home, pets.get(1));
			}
		}
		System.out.println(home.getFirstPet().getName());
		System.out.println(home.getSecondPet().getName());
	}
}
