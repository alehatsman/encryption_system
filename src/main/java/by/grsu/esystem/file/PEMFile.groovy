package by.grsu.esystem.file

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.PEMReader
import org.bouncycastle.openssl.PEMWriter
import java.security.Security

class PEMFile {
	
	static {
		// adds the Bouncy castle provider to java security
		Security.addProvider(new BouncyCastleProvider());
	}
	
	def String filename;
	
	PEMFile(String filename) {
		this.filename = filename
	}
	
	def withWriter(Closure closure) {
		new File(filename).withWriter { writer -> 
			PEMWriter pemWriter = new PEMWriter(writer)
			def result = closure.call(pemWriter)
			pemWriter.close()
			result
		}
	}
	
	def withReader(Closure closure) {
		new File(filename).withReader { reader ->
			PEMReader pemReader = new PEMReader(reader)
			def result = closure.call(pemReader)
			pemReader.close()
			result
		}
	}
	
}
