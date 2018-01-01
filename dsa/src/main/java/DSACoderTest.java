import java.util.Map;

public class DSACoderTest {

    public static void main(String args[]) throws Exception{
        String inputStr = "hello kat";
        byte[] data = inputStr.getBytes();
        // ������Կ
        Map<String, Object> keyMap = DSACoder.initKey();
        // �����Կ
        String publicKey = DSACoder.getPublicKey(keyMap);
        String privateKey = DSACoder.getPrivateKey(keyMap);
        System.err.println("��Կ:\r" + publicKey);
        System.err.println("˽Կ:\r" + privateKey);
        // ����ǩ��
        String sign = DSACoder.sign(data, privateKey);
        System.err.println("ǩ��:\r" + sign);
        // ��֤ǩ��
        boolean status = DSACoder.verify(data, publicKey, sign);
        System.err.println("״̬:\r" + status);

    }
}