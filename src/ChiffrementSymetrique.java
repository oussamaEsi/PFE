
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class ChiffrementSymetrique
{private static javax.swing.JTextField jTextField3,jTextField4;
private static javax.swing.JProgressBar jProgressBar1;
private static javax.swing.JLabel jLabel34,jLabel35,jLabel36;
private long temps;
private int tempsEcoule = 0;

public ChiffrementSymetrique(javax.swing.JTextField jTextField3,
                             javax.swing.JTextField jTextField4,javax.swing.JProgressBar jProgressBar1,
                             javax.swing.JLabel jLabel34,javax.swing.JLabel jLabel35,javax.swing.JLabel jLabel36)
{this.jTextField3=jTextField3;
this.jTextField4=jTextField4;
this.jProgressBar1=jProgressBar1;
this.jLabel34=jLabel34;
this.jLabel35=jLabel35;
this.jLabel36=jLabel36;
}

public void chiffrerFichier(int algo,String cleSecrete,String urlFichier)
{BufferedInputStream in = null;
AlgorithmeChiffrementSymetrique algorithme = null;
byte[] blocClair = null;
BufferedOutputStream out = null;
int nread = 0,progression = 0;

//Création d'une clé secrète.
byte[] raw = null;
  if(cleSecrete!=null && !cleSecrete.equals(""))
  {raw=Utils.toBytes(cleSecrete);
  }
SecretKeySpec key=null;
  switch(algo)
  {  case 0 :
     {  if(raw!=null)
        {key = new SecretKeySpec(raw, "IDEA");
        }
        else
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
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
        jTextField3.setText(null);
        return;
        }
     break;
     }
  }
//Vérification de la taille de la clé secrète.
  if((algo==0 && cleSecrete.length()!=32)||(algo==1 && cleSecrete.length()!=16)||(algo==2 && cleSecrete.length()!=48)||
    (algo==3 && cleSecrete.length()!=32)||(algo==4 && cleSecrete.length()!=48)||(algo==5 && cleSecrete.length()!=64)||
    (algo==6 && cleSecrete.length()!=32))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField3.setText(null);
  return;
  }
//Vérification de l'éxistence du fichier clair.
  try
  {in = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField4.setText(null);
  return;
  }
 //Initialisation des variables et des objets.
String extension = urlFichier.substring(urlFichier.lastIndexOf("."), urlFichier.length());
byte tailleExtension=(byte) extension.length();
File fichier=new File(urlFichier);
jProgressBar1.setMaximum((int)fichier.length());
jProgressBar1.setMinimum(0);
jLabel34.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel35.setText(fichier.length() + " octet(s)");
jLabel36.setText("0 s");
  switch(algo)
  {  case 0:
     {blocClair = new byte[8];
     progression=8;
     break;
     }
     case 1:
     {blocClair = new byte[8];
     progression=8;
     break;
     }
     case 2:
     {blocClair = new byte[8];
     progression=8;
     break;
     }
     case 3:
     {blocClair = new byte[16];
     progression=16;
     break;
     }
     case 4:
     {blocClair = new byte[16];
     progression=16;
     break;
     }
     case 5:
     {blocClair = new byte[16];
     progression=16;
     break;
     }
     case 6:
     {blocClair = new byte[8];
     progression=8;
     break;
     }
  }
//Chiffrement de la taille de l'éxtension du fichier clair.
  try
  {algorithme = new AlgorithmeChiffrementSymetrique(algo);
  }
  catch (Exception ex)
  {Logger.getLogger(ChiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
Cipher cipher=algorithme.getCipher();
  try
  {cipher.init(Cipher.ENCRYPT_MODE, key, algorithme.getIvSpec());
  }
  catch (InvalidKeyException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField3.setText(null);
  return;
  }
  catch (InvalidAlgorithmParameterException ex) 
  {Logger.getLogger(ChiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
urlFichier = urlFichier.substring(0, urlFichier.lastIndexOf("."));
  switch(algo)
  {  case 0:
     {urlFichier+="-IDEA.crp";
     break;
     }
     case 1:
     {urlFichier+="-DES.crp";
     break;
     }
     case 2:
     {urlFichier+="-TripleDES.crp";
     break;
     }
     case 3:
     {urlFichier+="-AES128.crp";
     break;
     }
     case 4:
     {urlFichier+="-AES192.crp";
     break;
     }
     case 5:
     {urlFichier+="-AES256.crp";
     break;
     }
     case 6:
     {urlFichier+="-RC4.crp";
     break;
     }
  }
  try
  {out =new BufferedOutputStream(new FileOutputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {Logger.getLogger(ChiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
CipherOutputStream cos = new CipherOutputStream(out, cipher);
  try
  {cos.write(tailleExtension);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField3.setText(null);
  return;
  }
// Chiffrement de l'extension du fichier clair.
  try
  {cos.write(extension.getBytes());
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField3.setText(null);
  return;
  }
temps = System.currentTimeMillis();
//Chiffrement de fichier clair
  try
  {  while ((nread = in.read(blocClair)) != -1 )
     {  try
        {cos.write(blocClair, 0, nread);
        }
        catch (IOException ex)
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField3.setText(null);
        return;
        }
     verifierTemps();
     jProgressBar1.setValue(jProgressBar1.getValue()+progression);
     }
  }
  catch (IOException ex)
  {Logger.getLogger(ChiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {cos.close();
   in.close();
   out.close();
  }
  catch (IOException ex)
  {Logger.getLogger(ChiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de chiffrement symétrique.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel36.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}
