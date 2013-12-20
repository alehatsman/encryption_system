package by.grsu.esystem.encryptors

import by.grsu.esystem.generators.impl.LKM
import java.nio.ByteBuffer

class XOREncryptor {
	
	def byte[] encrypt(String message, Integer randomSequence) {
		def messageBytes = message.getBytes()
		xor(messageBytes, intToByteArray(randomSequence))
	}
	
	def String decrypt(byte[] ciphertext, Integer randomSequence) {		
		def encryptBytes = xor(ciphertext, intToByteArray(randomSequence))
		new String(encryptBytes as byte[])
	}
	
	def byte[] xor (byte[] message, byte[] gamma) {
		def xoredElements = []
		def gammaNumber = 0
		message.each { e -> 
			def encryptedByte = e^gamma[gammaNumber]
			xoredElements.add(encryptedByte)
			if(gammaNumber == 3) {gammaNumber = 0} else {gammaNumber += 1}
		}
		xoredElements.toArray() as byte[]
	}
	
	def byte[] intToByteArray(x) {
		ByteBuffer b = ByteBuffer.allocate(4)
		b.putInt(x)
		b.array()
	}
	
}
