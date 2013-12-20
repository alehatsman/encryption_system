package by.grsu.esystem.scripts

import java.security.PrivateKey

import by.grsu.esystem.file.CryptoFile
import by.grsu.esystem.file.PEMFile
import by.grsu.esystem.file.RSAFile
import by.grsu.esystem.generators.impl.LKM
import by.grsu.esystem.parser.LKMParamParcer

def MESSAGE_FILE = "message.txt"
def RSA_FILE = "lkm_params.txt"
def PRIVATE_KEY = "private_key.pem"

PrivateKey privateKey = new PEMFile(PRIVATE_KEY).withReader { reader ->
	reader.readObject().getPrivate()
}

String keys = new RSAFile(RSA_FILE).read(privateKey)

def parcer = new LKMParamParcer()

def map = parcer.parce(keys)

LKM lkm = new LKM()

def message = new CryptoFile(MESSAGE_FILE).read(lkm.generate(map['x'], map['m'], map['a'], map['c']))

print message