import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class AlgorithmeChiffrementSymetrique
{private Cipher cipher;
private IvParameterSpec ivSpec=null;
 
public  AlgorithmeChiffrementSymetrique(int algo) throws Exception
{//CHoix de initial value (iv).
byte[] iv1=new byte[] {0x07,0x06,0x05,0x04,0x03,0x02,0x01,0x00};
byte[] iv2=new byte[] {0x0f,0x0e,0x0d,0x0c,0x0b,0x0a,0x09,0x08,0x07,0x06,0x05,0x04,0x03,0x02,0x01,0x00};
  switch(algo)
  {  case 0 :
     {cipher =Cipher.getInstance("IDEA/CBC/PKCS7Padding", "BC");
      ivSpec=new IvParameterSpec(iv1);
      break;
     }
     case 1 :
     {cipher =Cipher.getInstance("DES/CBC/PKCS7Padding", "BC");
      ivSpec=new IvParameterSpec(iv1);
      break;
     }
     case 2 :
     {cipher = Cipher.getInstance("TripleDES");
      break;
     }
     case 3 :
     {cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
      ivSpec=new IvParameterSpec(iv2);
      break;
     }
     case 4 :
     {cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
      ivSpec=new IvParameterSpec(iv2);
      break;
     }
     case 5 :
     {cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
      ivSpec=new IvParameterSpec(iv2);
      break;
     }
     case 6 :
     {cipher = Cipher.getInstance("RC4", "BC");
      break;
     }
  }
}
// Récupération de cipher.
public Cipher getCipher()
{return cipher;
}
// Récupération de ivSpec.
public IvParameterSpec getIvSpec()
{return ivSpec;
}
}