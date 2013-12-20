package by.grsu.esystem.generators.impl

import by.grsu.esystem.generators.Generator

class LKM implements Generator {
	def x = 1,
		m = Integer.MAX_VALUE,
		a = 16807,
		c = 0
	
	def LKM() {
		
	}
		
	def LKM(x, m, a, c) {
		this.x = x
		this.m = m
		this.a = a
		this.c = c
	}
		
	def generate() {
		x = (a*x + c) % m;
		return Math.abs(x);
	}
	
	def generate(x, m, a, c) {
		
		x = Integer.parseInt(x)
		m = Integer.parseInt(m)
		a = Integer.parseInt(a)
		c = Integer.parseInt(c)
		
		x = (a*x + c) % m;
		return Math.abs(x);
	}
}
