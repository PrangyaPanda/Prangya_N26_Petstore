package com.N26.PetStore.utilities;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;

public class SimpleAnnotationTransformer implements IAnnotationTransformer {
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		if (testMethod == null || testMethod.getAnnotation(CanRunMultipleTimes.class) == null) {
			return;
		}
		Random rand = new Random();
	    String[] disc = {"10","20","30"}; 
		int counter = Integer.parseInt(System.getProperty("iteration.count", disc[rand.nextInt(disc.length)]));
		annotation.setInvocationCount(counter);
	}
}

//Prangya Paramita Panda