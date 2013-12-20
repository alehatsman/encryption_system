package by.grsu.esystem.scripts

import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator

import by.grsu.esystem.file.PEMFile


def KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA")
def KeyPair kp = kpg.generateKeyPair()
def Key publicKey = kp.getPublic()
def Key privateKey = kp.getPrivate()

def PUBLIC_KEY_FILE = "public_key.pem"
def PRIVATE_KEY_FILE = "private_key.pem"

new PEMFile(PUBLIC_KEY_FILE).withWriter { writer ->
	writer.writeObject(publicKey)
}

new PEMFile(PRIVATE_KEY_FILE).withWriter { writer ->
	writer.writeObject(privateKey)
}