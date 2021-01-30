package de.viadee.spring.boot.security.example.baseauth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeystoreUtil {
	public static KeyStore load(File keystore, String password) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyStoreException {
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(keystore), password.toCharArray());
		return ks;
	}
	
}
