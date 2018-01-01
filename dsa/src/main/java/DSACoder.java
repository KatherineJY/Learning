import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class DSACoder {
    public static final String ALGORITHM = "DSA";

    /** 
     * DSA��Կ���ȣ�RSA�㷨��Ĭ����Կ������1024 
     * ��Կ���ȱ�����64�ı�������512��1024λ֮�� 
     * */ 
    private static final int KEY_SIZE = 1024;
    private static final String DEFAULT_SEED = "0f22507a10bbddd07d8a3082122966e3";
    private static final String PUBLIC_KEY = "DSAPublicKey";
    private static final String PRIVATE_KEY = "DSAPrivateKey";

    /**
     * ��˽Կ����Ϣ��������ǩ��
     * @param data ���ܺ����Ϣ
     * @param privateKey
     * @return �������ɵ�����ǩ��
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // ������base64�����˽Կ
        byte[] keyBytes = decryptBASE64(privateKey);
        // ����PKCS8EncodedKeySpec����
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM ָ�������㷨
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        // ȡ˽Կ�׶���
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // ��˽Կ����Ϣ��������ǩ��
        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * ��֤����ǩ��
     * @param data ���ܺ����Ϣ
     * @param publicKey
     * @param sign
     * @return ��֤�ɹ�����true ʧ�ܷ���false
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // ������base64����Ĺ�Կ
        byte[] keyBytes = decryptBASE64(publicKey);
        // ����X509EncodedKeySpec����
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        // ȡ��Կ�׶���
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
        signature.initVerify(pubKey);
        signature.update(data);

        // ��֤ǩ���Ƿ�����
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * ������Կ
     * @param seed
     * @return ��Կ����
     * @throws Exception
     */
    public static Map<String, Object> initKey(String seed) throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
        // ��ʼ�����������
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed.getBytes());
        keygen.initialize(KEY_SIZE, secureRandom);
        // ������Կ��
        KeyPair keys = keygen.genKeyPair();

        DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);

        return map;
    }

    public static Map<String, Object> initKey() throws Exception {
        return initKey(DEFAULT_SEED);
    }

    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static byte[] decryptBASE64(String data) {
        return Base64.decodeBase64(data);
    }

    public static String encryptBASE64(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }
}