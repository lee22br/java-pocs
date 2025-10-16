package org.example;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemEncoding {
    public static PrivateKey decodePemPrivateKey(String pemContent) throws Exception {
        // Remove header and footer
        String base64Encoded = pemContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", ""); // Remove whitespace

        // Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);

        // Convert to PrivateKey object
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Or "EC", etc.
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    public static void main (String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.generateKeyPair();
        String pem = encodeToPem("PUBLIC KEY", keyPair.getPublic().getEncoded());
        System.out.println("pem: "+pem);
        decodePemPrivateKey(pem);
    }

    private static String encodeToPem(String type, byte[] data) {
        String base64 = Base64.getMimeEncoder(64, System.lineSeparator().getBytes())
                .encodeToString(data);
        return "-----BEGIN " + type + "-----" + System.lineSeparator() +
                base64 + System.lineSeparator() +
                "-----END " + type + "-----";
    }

}
