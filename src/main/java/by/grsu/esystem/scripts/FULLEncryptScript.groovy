package by.grsu.esystem.scripts

import by.grsu.esystem.file.CryptoFile
import by.grsu.esystem.file.PEMFile
import by.grsu.esystem.file.RSAFile
import by.grsu.esystem.generators.impl.LKM
import by.grsu.esystem.parser.LKMParamParcer
import java.security.PrivateKey
import java.security.PublicKey

def MESSAGE_FILE = "message.txt"
def RSA_FILE = "lkm_params.txt"
def PUBLIC_KEY = "public_key.pem"
def LKM_PARAMS = "1 3234 16807 0"

PublicKey publicKey = new PEMFile(PUBLIC_KEY).withReader { reader ->
	reader.readObject()
}

new RSAFile(RSA_FILE).write(LKM_PARAMS, publicKey)

def parcer = new LKMParamParcer()

def map = parcer.parce(LKM_PARAMS)

LKM lkm = new LKM()

new CryptoFile(MESSAGE_FILE).write("HELLO WORLD MESSAGE XOR ENCRYPT", lkm.generate(map['x'], map['m'], map['a'], map['c']))
