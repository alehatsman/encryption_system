package by.grsu.esystem.file

import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator

import org.junit.Before

class AbstractFileTest {
	
	def TEST_DIRECTORY = "tests/Abstract/"
	def PUBLIC_KEY_FILE = TEST_DIRECTORY + "public_key.pem"
	def PRIVATE_KEY_FILE = TEST_DIRECTORY + "private_key.pem"
	
	void generateRSAKeys() {
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
	
}
