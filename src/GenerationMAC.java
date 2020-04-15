
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class GenerationMAC
{private static javax.swing.JTextField jTextField23,jTextField24;
private static javax.swing.JProgressBar jProgressBar11;
private static javax.swing.JLabel jLabel82,jLabel83,jLabel84;
private long temps;
private int tempsEcoule = 0;

public GenerationMAC(javax.swing.JTextField jTextField23,javax.swing.JTextField jTextField24,
                    javax.swing.JProgressBar jProgressBar11,javax.swing.JLabel jLabel82,
                    javax.swing.JLabel jLabel83,javax.swing.JLabel jLabel84)
{this.jTextField23=jTextField23;
this.jTextField24=jTextField24;
this.jProgressBar11=jProgressBar11;
this.jLabel82=jLabel82;
this.jLabel83=jLabel83;
this.jLabel84=jLabel84;
}
public String generer(int algoSym,String cleSecrete,int algoMAC,String urlFichier)
{Mac mac=null;
BufferedInputStream in;
int nread = 0;
byte[] bloc=new byte[1024];
String code;
 
//Création d'une clé secrète.
byte[] raw = null;
  if(cleSecrete!=null && !cleSecrete.equals(""))
  {raw=Utils.toBytes(cleSecrete);
  }
SecretKeySpec key=null;
  switch(algoSym)
  {  case 0 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "IDEA");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 1 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "DES");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 2 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "TripleDES");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 3 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "AES");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 4 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "AES");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 5 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "AES");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
     case 6 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "RC4");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField23.setText(null);
        return null;
        }
     break;
     }
  }
//Vérification de la taille de la clé secrète.
  if((algoSym==0 && cleSecrete.length()!=32)||(algoSym==1 && cleSecrete.length()!=16)||(algoSym==2 && cleSecrete.length()!=48)||
    (algoSym==3 && cleSecrete.length()!=32)||(algoSym==4 && cleSecrete.length()!=48)||(algoSym==5 && cleSecrete.length()!=64)||
    (algoSym==6 && cleSecrete.length()!=32))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField23.setText(null);
  return null;
  }
// Création d'un objet de génération d'un MAC.
  switch(algoMAC)
  {  case 0:
     {  try
        { mac = Mac.getInstance("HMacMD5");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 1:
     {  try
        { mac = Mac.getInstance("HMacSHA1");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 2:
     {  try
        { mac = Mac.getInstance("HMacSHA224");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 3:
     {  try
        { mac = Mac.getInstance("HMacSHA256");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 4:
     {  try
        { mac = Mac.getInstance("HMacSHA384");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 5:
     {  try
        { mac = Mac.getInstance("HMacSHA512");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 6:
     {  try
        { mac = Mac.getInstance("HMacRIPEMD160");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
  }
// Vérification de l’existence de fichier de données.
  try
  {in = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField24.setText(null);
  return null;
  }
// Initialisation des variables et des objets.
  try 
  {mac.init(key);
  }
  catch (InvalidKeyException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField23.setText(null);
  return null;
  }
File fichier=new File(urlFichier);
jProgressBar11.setMaximum((int)fichier.length());
jProgressBar11.setMinimum(0);
jLabel82.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel83.setText(fichier.length() + " octet(s)");
jLabel84.setText("0 s");
// Génération d'un MAC.
  try
  {nread = in.read(bloc);
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
  }
temps = System.currentTimeMillis();
  while ((nread!=-1))
  {mac.update(bloc, 0, nread);
    try
    {nread = in.read(bloc);
    }
    catch (IOException ex)
    {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
    }
  verifierTemps();
  jProgressBar11.setValue(jProgressBar11.getValue()+1024);
  }
byte[] macbytes = mac.doFinal();
return code=Utils.toHex(macbytes);
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de calcul de MAC.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel84.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}
