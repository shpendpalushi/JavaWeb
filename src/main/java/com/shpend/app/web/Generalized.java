package com.shpend.app.web;

import java.util.Arrays;
import java.util.List;

import com.shpend.app.domain.Thiesis;

public class Generalized {
	
	public static List<String> getQuestionsFromThiesis(Thiesis thiesis){
		String questions = thiesis.getQuestions();
		String[] arrayQuestions = questions.split("\n");
		 List<String> asList = Arrays.asList(arrayQuestions);
		for (String string : asList) {
			System.out.println(string);
			
		}
		return asList;
	}
	public static String randomStringGenerator(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
}
