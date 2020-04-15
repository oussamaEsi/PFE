import java.security.Key;
import javax.crypto.KeyGenerator;

public class CleSecrete {
    private String cleSecrete;
public CleSecrete(int algo) throws Exception {
    switch(algo){  
      case 0: //algorithme IDEA.
      {KeyGenerator keyGen = KeyGenerator.getInstance("IDEA");
      keyGen.init(128);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 1: //algorithme DES.
      {KeyGenerator keyGen = KeyGenerator.getInstance("DES");
      keyGen.init(56);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 2: //algorithme Triple DES.
      {KeyGenerator keyGen = KeyGenerator.getInstance("TripleDES");
      keyGen.init(168);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 3: //algorithme AES 128 bits.
      {KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(128);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 4: //algorithme AES 192 bits.
      {KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(192);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 5: //algorithme AES 256 bits.
      {KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
      case 6: //algorithme RC4 128 bits.
      {KeyGenerator keyGen = KeyGenerator.getInstance("RC4");
      keyGen.init(128);
      Key key = keyGen.generateKey();
      byte[] raw=key.getEncoded();
      cleSecrete=Utils.toHex(raw);
      break;
      }
   }
}
public String getCleSecrete()
{return cleSecrete;
}
}
