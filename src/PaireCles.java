import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import java.math.BigInteger;
import org.bouncycastle.jce.X509Principal;
import java.sql.Date;

public class PaireCles
{private PrivateKey clePrivee;
private X509Certificate certificat;
public PaireCles(int algo, String client) throws Exception
{KeyPair paire = null;
   switch(algo)
   {  case 0 :
      {KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(1024);
      paire=generator.generateKeyPair();
      break;
      }
      case 1 :
      {KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      paire=generator.generateKeyPair();
      break;
      }
      case 2 :
      {KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
      generator.initialize(512);
      paire=generator.generateKeyPair();
      break;
      }
      case 3 :
      {KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
      generator.initialize(1024);
      paire=generator.generateKeyPair();
      break;
      }
   }
clePrivee=paire.getPrivate();

X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
certGen.setIssuerDN(new X509Principal("CN=Plateforme"));
certGen.setNotBefore(new Date(System.currentTimeMillis()));
certGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365*10)));
certGen.setSubjectDN(new X509Principal("CN="+client));
certGen.setPublicKey(paire.getPublic());
  if((algo==0) || (algo==1))
  {certGen.setSignatureAlgorithm("SHA512WithRSAEncryption");
  }
  else
  {certGen.setSignatureAlgorithm("SHA512WithDSA");
  }
certificat = certGen.generateX509Certificate(clePrivee);
}
public PrivateKey getClePrivee()
{return clePrivee;
}
public X509Certificate getCertificat()
{return certificat;
}
}
