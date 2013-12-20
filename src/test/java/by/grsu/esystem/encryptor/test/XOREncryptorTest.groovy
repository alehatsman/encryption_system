package by.grsu.esystem.encryptor.test

import by.grsu.esystem.encryptors.XOREncryptor
import org.junit.Assert
import org.junit.Test

class XOREncryptorTest {
	
	@Test
	void test() {
		
		XOREncryptor encryptor = new XOREncryptor()
		
		def message = "HELLO WORLD ENCRYPTOR"
		
		def encryptedMessage = encryptor.encrypt(message, Integer.MAX_VALUE)
		
		def decryptedMessage = encryptor.decrypt(encryptedMessage, Integer.MAX_VALUE)
		
		Assert.assertEquals(message, decryptedMessage)
	}
	
}
