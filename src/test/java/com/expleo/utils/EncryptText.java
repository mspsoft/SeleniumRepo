// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.utils;
/**
 * Holds method to generate encrypted passwords
 */
public class EncryptText {
	/**
	 * main method 
	 * @param args - arguments
	 *  
	 */
	public static void main(String[] args) {
        try {
        	 String secretKey = EncryptUtil.generateKey();
             String[] plainPasswords = {"Engineer_1", "Engineer_2", "engineer_3", "engineer_4", "Engineer_5","demo"};
             String[] encryptedPasswords = new String[plainPasswords.length];             
             for (int i = 0; i < plainPasswords.length; i++) {
                 encryptedPasswords[i] = EncryptUtil.encrypt(plainPasswords[i], secretKey);
                 System.out.println("Encrypted Password " + (i+1) + ": " + encryptedPasswords[i]);
             }
             
             System.out.println("secret key "+secretKey);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
