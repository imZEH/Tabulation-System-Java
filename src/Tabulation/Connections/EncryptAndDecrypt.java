package Tabulation.Connections;

import javax.crypto.*;  
import javax.crypto.spec.*;  
import java.security.spec.*;  
import java.security.InvalidAlgorithmParameterException;  
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import java.io.*;  
  
public class EncryptAndDecrypt {  
  
 public static Cipher dcipher,ecipher;  
   
 //Responsible for setting, initializing this object's encrypter and decrypter Chipher instances      
 public EncryptAndDecrypt(String passPhrase) {  
          // 8-bytes Salt  
          byte[] salt = {  
             (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,  
             (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03  
          };  
  
          // Iteration count  
          int iterationCount = 19;  
  
          try  
             {    
  //Generate a temporary key. In practice, you would save this key  
            //Encrypting with DES Using a Pass Phrase  
              KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);  
             SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);  
  
             ecipher = Cipher.getInstance(key.getAlgorithm());  
             dcipher = Cipher.getInstance(key.getAlgorithm());  
  
              // Prepare the parameters to the cipthers  
              AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);  
  
             ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);  
             dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);  
  
             } catch (InvalidAlgorithmParameterException e) {  
               System.out.println("EXCEPTION: InvalidAlgorithmParameterException");  
              } catch (InvalidKeySpecException e) {  
              System.out.println("EXCEPTION: InvalidKeySpecException");  
             } catch (NoSuchPaddingException e) {  
              System.out.println("EXCEPTION: NoSuchPaddingException");  
              } catch (NoSuchAlgorithmException e) {  
              System.out.println("EXCEPTION: NoSuchAlgorithmException");  
             } catch (InvalidKeyException e) {  
               System.out.println("EXCEPTION: InvalidKeyException");  
             }  
    }  
 
 
  
     //Encrpt Password      
     @SuppressWarnings("unused")  
 public String encrypt(String str)   
     {  
            try {  
             // Encode the string into bytes using utf-8  
              byte[] utf8 = str.getBytes("UTF8");  
              // Encrypt  
             byte[] enc = ecipher.doFinal(utf8);  
              // Encode bytes to base64 to get a string  
             return new sun.misc.BASE64Encoder().encode(enc);  
  
          } catch (BadPaddingException e) {  
        } catch (IllegalBlockSizeException e) {  
          } catch (UnsupportedEncodingException e) {  
               }  
          return null;  
     }      
  
     // Decrpt password     
     //To decrypt the encryted password  
     public String decrypt(String str)   
     {  
      Cipher dcipher = null;  
  try  
  {  
       byte[] salt = {(byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,(byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03};  
   int iterationCount = 19;  
   try   
   {   String passPhrase="";  
        KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);  
       SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);            
       dcipher = Cipher.getInstance(key.getAlgorithm());  
        // Prepare the parameters to the cipthers  
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);  
    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);  
           }   
   catch (InvalidAlgorithmParameterException e)  
           {       System.out.println("EXCEPTION: InvalidAlgorithmParameterException");  
    }  
   catch (InvalidKeySpecException e)  
   {       System.out.println("EXCEPTION: InvalidKeySpecException");  
   }  
   catch (NoSuchPaddingException e)  
   {       System.out.println("EXCEPTION: NoSuchPaddingException");  
   }   
   catch (NoSuchAlgorithmException e)  
   {       System.out.println("EXCEPTION: NoSuchAlgorithmException");  
   }   
   catch (InvalidKeyException e)  
   {       System.out.println("EXCEPTION: InvalidKeyException");  
   }  
           // Decode base64 to get bytes  
   byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);  
   // Decrypt  
   byte[] utf8 = dcipher.doFinal(dec);  
   // Decode using utf-8  
   return new String(utf8, "UTF8");  
  }  
  catch (BadPaddingException e) {  
  } catch (IllegalBlockSizeException e) {  
  } catch (UnsupportedEncodingException e) {  
  } catch (IOException e){  
  }  
  return null;  
 }
     public static void main(String [] args){
         EncryptAndDecrypt encrypter = new EncryptAndDecrypt("");
 String a = "0n0Uw3Gb0hQ=";
 System.out.print(encrypter.decrypt(a));
 }
      
 /** 
  * @param args 
  */  
/* public static void main(String[] args) {  
  try {  
        
      // Create encrypter/decrypter class  
   System.out.println("Inside Helper");  
      EncryptAndDecrypt encrypter = new EncryptAndDecrypt("");  
  
      // Pass the word to be Encrypted to Encrypt()  
      System.out.println( "encrypt the String: SimplePassword");  
      String encrypted = encrypter.encrypt("IT Bacth 2010");  
      System.out.println( "encrypted String:"+ encrypted);  
        
      // Pass the encrypted word to be Decrypted to Decrypt()  
      String decrypted = encrypter.decrypt(encrypted);  
      System.out.println( "decrypted String:"+ decrypted);  
  } catch (Exception e) {  
  }  
 } */   
} 