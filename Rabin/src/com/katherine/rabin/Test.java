package com.katherine.rabin;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		BigInteger[] key = Rabin.genKey(512);
		BigInteger n = key[0];
		BigInteger p = key[1];
		BigInteger q = key[2];

		String strm = "Hello world!";
		String s = strm + "AAAA";
		BigInteger m = new BigInteger(s.getBytes(Charset.forName("ascii")));
		BigInteger c = Rabin.encrypt(m, n);

		BigInteger[] m2 = Rabin.decrypt(c, p, q);
		for (BigInteger b : m2) {
			String dec = new String(b.toByteArray(), Charset.forName("ascii"));
			if (dec.endsWith("AAAA"))
				System.out.println("decode sucessfully!");
		}

	}
}
