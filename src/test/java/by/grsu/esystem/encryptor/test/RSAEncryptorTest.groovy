package by.grsu.esystem.encryptor.test

import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import by.grsu.esystem.encryptors.RSAEncryptor
import by.grsu.esystem.file.PEMFile

class RSAEncryptorTest {
	
	def RSAEncryptor encryptor = new RSAEncryptor()
	def KeyFactory keyFactory = KeyFactory.getInstance("RSA")
	
	@Test
	void test() {
		def KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA")
		def KeyPair kp = kpg.generateKeyPair()
		def Key publicKey = kp.getPublic()
		def Key privateKey = kp.getPrivate()
		
		def String message = "HELLO WORLD RSA!!!"
		
		println "Message: " + message
		println "Message byte view: \n" + message.getBytes()
		
		def ciphertext = encryptor.encrypt(message, publicKey)
		println "Cipher text: " + new String(ciphertext)
		println "Cipher text byte view: \n" + ciphertext
		
		def decryptMessage = encryptor.decrypt(ciphertext, privateKey)
		println "Decrypt message: " + decryptMessage
		
		Assert.assertEquals(message, decryptMessage)
	}
}
