package by.grsu.esystem.file

import by.grsu.esystem.encryptors.XOREncryptor

class CryptoFile {
	
	def XOREncryptor xorEncryptor = new XOREncryptor()
	
	def String filename
	
	CryptoFile(String filename) {
		this.filename = filename
	}
	
	def void write (String message, Integer randomSequence) {
		new File(filename).withOutputStream { os -> 
			os.write(xorEncryptor.encrypt(message, randomSequence))
		}
	}
	
	def String read (Integer randomSequence) {
		byte[] ciphertext = new File(filename).readBytes()
		xorEncryptor.decrypt(ciphertext, randomSequence)
	}
	
}
