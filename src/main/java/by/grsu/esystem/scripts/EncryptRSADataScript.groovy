package by.grsu.esystem.scripts

import by.grsu.esystem.encryptors.RSAEncryptor
import by.grsu.esystem.file.PEMFile
import java.security.KeyPair

def PUBLIC_KEY_FILE = "public_key.pem"
def PRIVATE_KEY_FILE = "private_key.pem"

def publicKey = new PEMFile(PUBLIC_KEY_FILE).withReader { reader ->
	reader.readObject()
}

def KeyPair keyPair = new PEMFile(PRIVATE_KEY_FILE).withReader { reader ->
	reader.readObject()
}

def encryptor = new RSAEncryptor()

def byte[] encryptMessage = encryptor.encrypt("1 3234 16807 0", publicKey)

/*def decryptMessage = encryptor.decrypt(encryptMessage, keyPair.getPrivate())

print decryptMessage*/

new File("lkm_params").withOutputStream { os ->
	os.write(encryptMessage)
}
