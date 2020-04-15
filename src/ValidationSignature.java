
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ValidationSignature
{private static javax.swing.JTextField jTextField9;
private static javax.swing.JTextField jTextField13;
private static javax.swing.JProgressBar jProgressBar6;
private static javax.swing.JLabel jLabel52,jLabel53,jLabel54;
private long temps;
private int tempsEcoule = 0;

public ValidationSignature(  javax.swing.JTextField jTextField9,javax.swing.JTextField jTextField13,
                             javax.swing.JProgressBar jProgressBar6,javax.swing.JLabel jLabel52,
                             javax.swing.JLabel jLabel53,javax.swing.JLabel jLabel54)
{this.jTextField9=jTextField9;
this.jTextField13=jTextField13;
this.jProgressBar6=jProgressBar6;
this.jLabel52=jLabel52;
this.jLabel53=jLabel53;
this.jLabel54=jLabel54;
}
public int valider(int algo,String urlCertificat,String proprietaireCertificat,String urlFichier,String signatureNumerique)
{PublicKey clePublique = null;
BufferedInputStream in2;
Signature sig = null;
int nread = 0,resultat = -1;
InputStream in1;
CertificateFactory certFact = null;
X509Certificate certificat = null;

// Algorithme de signature numérique.
  switch(algo)
  {  case 0 :
     {  try
        {sig = Signature.getInstance("MD5WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 1 :
     {  try
        {sig = Signature.getInstance("SHA1WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 2 :
     {  try
        {sig = Signature.getInstance("SHA224WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 3 :
     {  try
        {sig = Signature.getInstance("SHA256WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 4 :
     {  try
        {sig = Signature.getInstance("SHA384WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 5 :
     {  try
        {sig = Signature.getInstance("SHA512WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 6 :
     {  try
        {sig = Signature.getInstance("RIPEMD160WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 7 :
     {  try
        {sig = Signature.getInstance("RIPEMD256WithRSAEncryption");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 8 :
     {  try
        {sig = Signature.getInstance("SHA1WithDSA");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 9 :
     {  try
        {sig = Signature.getInstance("SHA224WithDSA");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 10 :
     {  try
        {sig = Signature.getInstance("SHA256WithDSA");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 11 :
     {  try
        {sig = Signature.getInstance("SHA384WithDSA");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 12 :
     {  try
        {sig = Signature.getInstance("SHA512WithDSA");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
  }
// Récupération du certificat numérique.
  try
  {in1 = new FileInputStream(urlCertificat);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField9.setText(null);
  return -1;
  }
  try
  {certFact = CertificateFactory.getInstance("X.509");
  }
  catch (CertificateException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {certificat = (X509Certificate) certFact.generateCertificate(in1);
  }
  catch (CertificateException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {in1.close();
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
// Validation de certificat numérique.
  try
  {certificat.verify(certificat.getPublicKey());
  }
  catch (CertificateException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (NoSuchAlgorithmException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (InvalidKeyException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (NoSuchProviderException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (SignatureException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est délivré par une autorité de certification non reconnue.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField9.setText(null);
  return -1;
  }
  try
  {certificat.checkValidity(new Date());
  }
  catch (CertificateExpiredException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est expiré.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField9.setText(null);
  return -1;
  }
  catch (CertificateNotYetValidException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  if(!(proprietaireCertificat.equals(certificat.getSubjectDN().toString())))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat n'appartient pas au propriétaire saisi.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField9.setText(null);
  return -1;
  }
// Récupération de la clé publique du certificat numérique.
clePublique = certificat.getPublicKey();
// Vérification de l'existence de fichier de données.
  try
  {in2 = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField13.setText(null);
  return -1;
  }
// Initialisation des variables et des objets.
byte[] dataBytes = new byte[1024];
File fichier=new File(urlFichier);
jProgressBar6.setMaximum((int)fichier.length());
jProgressBar6.setMinimum(0);
jLabel52.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel53.setText(fichier.length() + " octet(s)");
jLabel54.setText("0 s");
// Vérification d'une signature numérique.
  try
  {sig.initVerify(clePublique);
  }
  catch (InvalidKeyException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField9.setText(null);
  return -1;
  }
  try
  {nread = in2.read(dataBytes);
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
temps = System.currentTimeMillis();
  while (nread != -1)
  {  try
     {sig.update(dataBytes, 0, nread);
     }
     catch (SignatureException ex)
     {Logger.getLogger(ValidationSignature.class.getName()).log(Level.SEVERE, null, ex);
     }
     try
     {nread = in2.read(dataBytes);
     }
     catch (IOException ex)
     {Logger.getLogger(ValidationSignature.class.getName()).log(Level.SEVERE, null, ex);
     }
  verifierTemps();
  jProgressBar6.setValue(jProgressBar6.getValue()+1024);
  }
  try
  {in2.close();
  }
  catch (IOException ex)
  {Logger.getLogger(ValidationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {  if(sig.verify(Utils.toBytes(signatureNumerique)))
     {resultat=1;
     }
     else
     {resultat=0;
     }
  }
  catch (SignatureException ex)
  {Logger.getLogger(ValidationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
return resultat;
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de vérification signature.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel54.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}