package by.grsu.esystem.parser

class LKMParamParcer {

	def parce(String keys) {
		
		def params = keys.tokenize()

		params.each { e ->
			e = Integer.parseInt(e)
		}

		['x':params[0], 'm':params[1], 'a':params[2], 'c':params[3]]
	}
}
