package com.katherine.rabin;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Rabin {
    private static Random r = new SecureRandom();
    private static BigInteger TWO = BigInteger.valueOf(2);
    private static BigInteger THREE = BigInteger.valueOf(3);
    private static BigInteger FOUR = BigInteger.valueOf(4);

    /**
     * ������Կ
     * @param bitLength ��Կ�ı��س���
     * @return ���ع�Կn��˽Կp��q
     */
    public static BigInteger[] genKey(int bitLength) {
    	BigInteger p = blumPrime(bitLength/2);
    	BigInteger q = blumPrime(bitLength/2);
    	BigInteger n = p.multiply(q);
    	return new BigInteger[] {n,p,q};
    }

    /**
     * �ù�Կ����
     * @param m ����
     * @param n ��Կ
     * @return c ����
     */
    public static BigInteger encrypt(BigInteger m,BigInteger n) {
    	return m.modPow(TWO, n);
    }

    /**
     * ��˽Կ����
     * @param c ����
     * @param p ˽Կ
     * @param q ˽Կ
     * @return ���ؿ��ܵ��ĸ�����
     */
    public static BigInteger[] decrypt(BigInteger c,BigInteger p,BigInteger q) {
    	BigInteger n = p.multiply(q);
    	//x^2=c(mod n)���ĸ���
    	BigInteger m_p1 = c.modPow(p.add(BigInteger.ONE).divide(FOUR), p);
        BigInteger m_p2 = p.subtract(m_p1);
        BigInteger m_q1 = c.modPow(q.add(BigInteger.ONE).divide(FOUR), q);
        BigInteger m_q2 = q.subtract(m_q1);
        
        //�й�ʣ�ඨ������ĸ�����
        //��չŷ�������˷���Ԫ
        BigInteger[] ext = ext_gcd(p,q);
        BigInteger y_p = ext[1];
        BigInteger y_q = ext[2];

        BigInteger d1 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p1)).mod(n);
        BigInteger d2 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p1)).mod(n);
        BigInteger d3 = y_p.multiply(p).multiply(m_q1).add(y_q.multiply(q).multiply(m_p2)).mod(n);
        BigInteger d4 = y_p.multiply(p).multiply(m_q2).add(y_q.multiply(q).multiply(m_p2)).mod(n);

        return new BigInteger[]{d1,d2,d3,d4};
    }


    public static BigInteger[] ext_gcd(BigInteger a, BigInteger b) {
        BigInteger s = BigInteger.ZERO;
        BigInteger old_s = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        BigInteger old_t = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger old_r = a;
        //�ǵݹ���չŷ�����
        while(!r.equals(BigInteger.ZERO)) {
            BigInteger q = old_r.divide(r);
            BigInteger tr = r;
            r = old_r.subtract(q.multiply(r));
            old_r=tr;

            BigInteger ts = s;
            s = old_s.subtract(q.multiply(s));
            old_s=ts;

            BigInteger tt = t;
            t = old_t.subtract(q.multiply(t));
            old_t=tt;
        }

        return new BigInteger[]{old_r, old_s, old_t};
    }

    /**
     * ����һ������
     * @param bitLength �����ı��س���
     * @return ����һ������
     */
    public static BigInteger blumPrime(int bitLength) {
        BigInteger p;
        do {
            p=BigInteger.probablePrime(bitLength,r);
        }
        while(!p.mod(FOUR).equals(THREE));
        return p;
    }
}
