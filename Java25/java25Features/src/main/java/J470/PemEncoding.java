package J470;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemEncoding {

    static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair keyPair = keyGen.generateKeyPair();
            String pem = encodeToPem("PUBLIC KEY", keyPair.getPublic().getEncoded());
            System.out.println("pem: "+pem);

            String base64 = pem.replaceAll("-----.*-----", "").replaceAll("\\s", "");
            byte[] keyBytes = Base64.getDecoder().decode(base64);

            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey key = factory.generatePublic(spec);

            System.out.println("Key algo: " + key.getAlgorithm());
            System.out.println("Key spec: " + key.getFormat());
            System.out.println("Key loaded: " + encodeToPem("PUBLIC KEY", key.getEncoded()));
            System.out.println("Keys match: " + keyPair.getPublic().equals(key));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static String encodeToPem(String type, byte[] data) {
        String base64 = Base64.getMimeEncoder(64, System.lineSeparator().getBytes())
                .encodeToString(data);
        return "-----BEGIN " + type + "-----" + System.lineSeparator() +
                base64 + System.lineSeparator() +
                "-----END " + type + "-----";
    }
}

