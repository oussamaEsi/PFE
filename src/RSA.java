import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

public class RSA
{private Cipher cipher;
public RSA() throws Exception
{cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
}
//chiffrement RSA
public byte[] chiffrer(byte[] data,RSAPublicKey pubKey) throws Exception
{cipher.init(Cipher.ENCRYPT_MODE, pubKey);
byte[] chiffre = cipher.doFinal(data);
return chiffre;
}
// déchiffrement RSA
public byte[] dechiffrer(byte[] data,RSAPrivateKey privKey)throws Exception
{cipher.init(Cipher.DECRYPT_MODE, privKey);
byte[] clair=cipher.doFinal(data);
return clair;
}
}
