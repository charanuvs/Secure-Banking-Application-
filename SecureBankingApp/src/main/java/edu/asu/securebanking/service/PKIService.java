package edu.asu.securebanking.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;

import edu.asu.securebanking.util.AppUtil;
import edu.asu.securebanking.service.EmailService;
import edu.asu.securebanking.util.AppUtil;
import javax.crypto.Cipher;
import javax.crypto.SealedObject;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.securebanking.constants.AppConstants;

public class PKIService {

	@Autowired
	private EmailService emailService;
	
	public void generateKeyAndSendToUser(String user, String email) throws Exception
	{
		String body = new String("Dear User,\nPlease save the following private key corresponding to your account. Save the attached key file.");
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		KeyPair myPair = kpg.generateKeyPair();
		byte[] privateKeyBytes = myPair.getPrivate().getEncoded();
		File pri = new File(AppConstants.KEY_PATH+user+"_key.txt");
		File pub = new File(AppConstants.KEY_PATH+user+"_pub.txt");
		FileOutputStream fos1 = new FileOutputStream(pri);
		byte[] publicKeyBytes = myPair.getPublic().getEncoded();
		FileOutputStream fos2 = new FileOutputStream(pub);
		
	    Encoder encoder = Base64.getEncoder();
	    byte[] encryptedpublicKeyBytes = encoder.encode(publicKeyBytes);
	    byte[] encryptedprivateKeyBytes = encoder.encode(privateKeyBytes);
	    fos1.write(encryptedprivateKeyBytes);
	    fos1.close();
	    fos2.write(encryptedpublicKeyBytes);
	    fos2.close();
	    emailService.sendEmail(email, "CSE545 Project: Private Key", body, pri.getAbsolutePath());
	}
	
	public void sendEnryptedString(String user, String email) throws Exception
	{
		String body = "Dear user,\n\nPlease find below the message that needs to be decrypted using the key provided to you at the time of registration."+
						"\n Copy the code enclosed in the dotted lines below:\n\n"+
						"--------------------------------------------------------------\n\n";
		File pub = new File(AppConstants.KEY_PATH+user+".pub");
	    FileInputStream pubfis = new FileInputStream(pub);
	    DataInputStream pubdis = new DataInputStream(pubfis);
	    byte[] pubBytes = new byte[(int)pub.length()];
	    pubdis.readFully(pubBytes);
	    pubdis.close();

	    X509EncodedKeySpec pubspec =
	      new X509EncodedKeySpec(pubBytes);
	    KeyFactory pubkf = KeyFactory.getInstance("RSA");
	    Key publickey = pubkf.generatePublic(pubspec);
	    
	    // Get an instance of the Cipher for RSA encryption/decryption
	    Cipher c = Cipher.getInstance("RSA");
	    // Initiate the Cipher, telling it that it is going to Encrypt, giving it the public key
	    c.init(Cipher.ENCRYPT_MODE, publickey); 
	    
	    // Create a secret message
	    String myMessage = new String(AppUtil.getRandomPwd(15));
	    // Encrypt that message using a new SealedObject and the Cipher we created before
	    
	    byte[] encryptedBytes = c.doFinal(myMessage.getBytes());
	    //System.out.println(new String(encryptedBytes));
	    Encoder encoder = Base64.getEncoder();
	    byte[] encodedencmessage = encoder.encode(encryptedBytes);
	    String encodedMessage = new String(encodedencmessage);
	    body = body + encodedMessage;
	    
	    body = body+"\n\n--------------------------------------------------------------";
	    emailService.sendEmail(email, "CSE545 Project: Challenge string [PKI]", body);
	}
}
