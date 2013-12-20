package by.grsu.esystem.file

import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import by.grsu.esystem.encryptors.RSAEncryptor;

class PEMFileTest extends AbstractFileTest {
	
	def TEST_DIRECTORY = "tests/PEMFileTest/"
	def PUBLIC_KEY_FILE = TEST_DIRECTORY + "public_key.pem"
	def PRIVATE_KEY_FILE = TEST_DIRECTORY + "private_key.pem"
	def KEYS_FILE = TEST_DIRECTORY + "lkm_params"
	
	def RSAEncryptor encryptor = new RSAEncryptor()
	
	@Before
	void before() {
		File f = new File(TEST_DIRECTORY);
		f.mkdirs();
		
		def KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA")
		def KeyPair kp = kpg.generateKeyPair()
		def Key publicKey = kp.getPublic()
		def Key privateKey = kp.getPrivate()
		
		new PEMFile(PUBLIC_KEY_FILE).withWriter { writer ->
			writer.writeObject(publicKey)
		}
		
		new PEMFile(PRIVATE_KEY_FILE).withWriter { writer ->
			writer.writeObject(privateKey)
		}
	}
	
	@Test
	void testFileKeys() {	
		def message = "HELLO WORLD RSA!!!"
		
		def publicKey = new PEMFile(PUBLIC_KEY_FILE).withReader { r ->
			r.readObject()
		}
		
		def Key privateKey = new PEMFile(PRIVATE_KEY_FILE).withReader { r ->
			r.readObject().getPrivate()
		}
		
		def ciphertext = getEncryptor().encrypt(message, publicKey)
		def decryptMessage = getEncryptor().decrypt(ciphertext, privateKey)
		
		Assert.assertEquals(message, decryptMessage)
	}
	
}
