package by.grsu.esystem.file

import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator

import org.junit.Assert;
import org.junit.Before
import org.junit.Test

class CryptoFileTest {
	
	def TEST_DIRECTORY = "tests/CryptoFile/"
	def PUBLIC_KEY_FILE = TEST_DIRECTORY + "public_key.pem"
	def PRIVATE_KEY_FILE = TEST_DIRECTORY + "private_key.pem"
	def KEYS_FILE = TEST_DIRECTORY + "lkm_params"
	
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
	void test() {
		def String message = "HELLO CRYPTO"
		
		new CryptoFile(KEYS_FILE).write(message, Integer.MAX_VALUE)
		
		String messageFromFIle = new CryptoFile(KEYS_FILE).read(Integer.MAX_VALUE)
		
		Assert.assertEquals(message, messageFromFIle)
	}
	
}
