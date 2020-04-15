
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GenerationSignature
{private static javax.swing.JTextField jTextField2,jTextField11;
private static javax.swing.JProgressBar jProgressBar5;
private static javax.swing.JLabel jLabel52,jLabel53,jLabel54;
private long temps;
private int tempsEcoule = 0;

public GenerationSignature(javax.swing.JTextField jTextField2,javax.swing.JTextField jTextField11,
                          javax.swing.JProgressBar jProgressBar5,javax.swing.JLabel jLabel52,
                          javax.swing.JLabel jLabel53,javax.swing.JLabel jLabel54)
{this.jTextField2=jTextField2;
this.jTextField11=jTextField11;
this.jProgressBar5=jProgressBar5;
this.jLabel52=jLabel52;
this.jLabel53=jLabel53;
this.jLabel54=jLabel54;
}
public String signerFicher(int algo,String urlClePrivee,String urlFichier)
{PrivateKey clePrivee = null;
BufferedInputStream in2;
Signature sig = null;
int nread = 0;
ObjectInputStream in1;
String signatureNumerique = null;

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
// Récupération de la clé privée.
  try
  {in1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(urlClePrivee))));
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est introuvable ou invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField2.setText(null);
  return null;
  }
  try
  {clePrivee = (PrivateKey) in1.readObject();
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (ClassNotFoundException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  try 
  {in1.close();
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
// Vérification de l'existence de fichier de données.
  try
  {in2 = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField11.setText(null);
  return null;
  }
// Initialisation des variables et des objets.
byte[] dataBytes = new byte[1024];
File fichier=new File(urlFichier);
jProgressBar5.setMaximum((int)fichier.length());
jProgressBar5.setMinimum(0);
jLabel52.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel53.setText(fichier.length() + " octet(s)");
jLabel54.setText("0 s");
// Génération d'une signature numérique.
  try
  {sig.initSign(clePrivee);
  }
  catch (InvalidKeyException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField2.setText(null);
  return null;
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
     {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
     }
     try
     {nread = in2.read(dataBytes);
     }
     catch (IOException ex)
     {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
     }
  verifierTemps();
  jProgressBar5.setValue(jProgressBar5.getValue()+1024);
  }
  try
  {in2.close();
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  try 
  {signatureNumerique = Utils.toHex(sig.sign());
  }
  catch (SignatureException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
return signatureNumerique;
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de signature.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel54.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}
