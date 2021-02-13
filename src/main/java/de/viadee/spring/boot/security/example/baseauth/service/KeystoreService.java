package de.viadee.spring.boot.security.example.baseauth.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import org.springframework.stereotype.Service;

@Service
public class KeystoreService {
	private final KeyStore keystore;
	private final String keystoreMasterPassword 	= "geheim"; 
	private final String keyAlias 					= "testKeystore";
	private final String keyAliasPass 				= "geheim2";
	private final String keystoreFile				= "C:\\Users\\r8744\\eclipse-workspace\\keystore\\testKeystore.keystore";
			
	
	public KeystoreService() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
		keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(new FileInputStream(keystoreFile), keystoreMasterPassword.toCharArray());	
	}
	
	public PrivateKey getPrivateKey() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		return (PrivateKey) keystore.getKey(keyAlias, keyAliasPass.toCharArray());
	}
	
	public PublicKey getPublicKey() throws KeyStoreException {
		  Certificate cert = keystore.getCertificate(keyAlias);
		  return (PublicKey) cert.getPublicKey();
	}
	
}
