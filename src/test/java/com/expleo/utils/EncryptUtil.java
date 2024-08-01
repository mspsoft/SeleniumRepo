// @<COPYRIGHT>@
// ==================================================
// Copyright 2024.
// Expleo Group - Expleo India Pvt. Ltd.
// All Rights Reserved.
// ==================================================
// @<COPYRIGHT>@

package com.expleo.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 * Holds method to generate key, encrypt, decrypt
 */
public class EncryptUtil {
    private static final String ALGORITHM = "AES";
    /**
     * Generates key with AES-128 encryption
     * 
     * @return secretKey - secretKey
     */
    // Generate a new AES key
    public static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // AES-128
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    /**
     * Encrypts the given text with secret key
     * @param plainText -password to be encrypted
     * @param secret - secret key for encryption
     * @return encryptedText
     */
    // Encrypt a plain text using a secret key
    public static String encrypt(String plainText, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    /**
     * Decrypts the given text with secret key
     * @param encryptedText- encryptedText to be decrypted
     * @param - secret key - scret key
     * @return decryptedText
     */
    // Decrypt an encrypted text using a secret key
    public static String decrypt(String encryptedText, String secret) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }
}
