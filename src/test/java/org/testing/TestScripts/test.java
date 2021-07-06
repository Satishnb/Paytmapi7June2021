package org.testing.TestScripts;

import java.util.HashSet;

import org.testng.annotations.Test;

public class test {
	@Test
	public static void test() {
		
		{
			  
	        // Creating object of HashSet
	        HashSet<Integer>
	            arrset1 = new HashSet<Integer>();
	  
	        // Populating arrset1
	        arrset1.add(10);
	        arrset1.add(20);
	        arrset1.add(30);
	        arrset1.add(40);
	        arrset1.add(50);
	  
	        // print arrset1
	        System.out.println("First HashSet: "
	                           + arrset1);
	  
	        // Creating another object of HashSet
	        HashSet<Integer>
	            arrset2 = new HashSet<Integer>();
	  
	        // Populating arrset2
	        arrset2.add(40);
	        arrset2.add(50);
	        arrset2.add(10);
	        arrset2.add(20);
	        arrset2.add(30);
	  
	        // print arrset2
	        System.out.println("Second HashSet: "
	                           + arrset2);
	  
	        // comparing first HashSet to another
	        // using equals() method
	        boolean value = arrset1.equals(arrset2);
	  
	        // print the value
	        System.out.println("Are both set equal: "
	                           + value);
	    }
		
	}

}
