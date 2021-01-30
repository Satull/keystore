package de.viadee.spring.boot.security.example.baseauth.controller;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.viadee.spring.boot.security.example.baseauth.service.KeystoreService;

@RestController
@RequestMapping("/private")
public class Private {
	@Autowired
	private KeystoreService keystoreService;
	
	private Logger logger = LoggerFactory.getLogger(Private.class);
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String address() {
		return "Geheimstrasse 5";
	}
	
	@RequestMapping(value = "/privateKey", method = RequestMethod.GET)
	public String privateKey() {
		String privateKeyStr = "undefined";
		try {
			PrivateKey privateKey = keystoreService.getPrivateKey();
			privateKeyStr = privateKey.toString();
		} catch (Exception e) {
			logger.debug("Cannot get private key", e);
		}
		return privateKeyStr;
	}
	
	@RequestMapping(value = "/publicKey", method = RequestMethod.GET)
	public String publicKey() {
		String publicKeyStr = "undefined";
		try {
			PublicKey publicKey = keystoreService.getPublicKey();
			publicKeyStr = publicKey.toString();
		} catch (Exception e) {
			logger.debug("Cannot get public key", e);
		}
		return publicKeyStr;
	}
}
