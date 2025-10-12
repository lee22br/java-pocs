import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class Jep510 {

    void main (){
        // Create a KDF object for the specified algorithm
        try {
        KDF hkdf = KDF.getInstance("HKDF-SHA256");
        byte[] initialKeyMaterial = "secret key material".getBytes();
        byte[] salt = "random salt".getBytes();
        byte[] info = "application info".getBytes();

// Create an ExtractExpand parameter specification
        AlgorithmParameterSpec params =
                HKDFParameterSpec.ofExtract()
                        .addIKM(initialKeyMaterial)
                        .addSalt(salt).thenExpand(info, 32);
// Derive a 32-byte AES key
            SecretKey key = hkdf.deriveKey("AES", params);
            System.out.println("Key Alg: "+key.getAlgorithm());
            System.out.println("Key Format: "+key.getFormat());
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
