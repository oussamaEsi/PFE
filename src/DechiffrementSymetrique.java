
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
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class DechiffrementSymetrique 
{private static javax.swing.JTextField jTextField5,jTextField6;
private static javax.swing.JProgressBar jProgressBar2;
private static javax.swing.JLabel jLabel34,jLabel35,jLabel36;
private long temps;
private int tempsEcoule = 0;

public DechiffrementSymetrique(javax.swing.JTextField jTextField5,javax.swing.JTextField jTextField6,
                              javax.swing.JProgressBar jProgressBar2,javax.swing.JLabel jLabel34,
                              javax.swing.JLabel jLabel35,javax.swing.JLabel jLabel36)
{this.jTextField5=jTextField5;
this.jTextField6=jTextField6;
this.jProgressBar2=jProgressBar2;
this.jLabel34=jLabel34;
this.jLabel35=jLabel35;
this.jLabel36=jLabel36;
}

public void dechiffrerFichier(int algo,String cleSecrete,String urlFichier)
{BufferedInputStream in;
BufferedOutputStream out = null;
byte[] extensionChiffree =null,tailleExtensionChiffree=new byte[1],blocChiffre = null;
int nread = 0,progression = 0;
AlgorithmeChiffrementSymetrique algorithme = null ;

//Création de la clé secrète.
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
        jTextField5.setText(null);
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
  jTextField5.setText(null);
  return;
  }
//Vérification de l'éxistence du fichier chiffré.
  try
  {in = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField6.setText(null);
  return;
  }
//Initialisation des variables.
  switch(algo)
  {  case 0:
     {blocChiffre =new byte[8];
     progression=8;
     break;
     }
     case 1:
     {blocChiffre =new byte[8];
     progression=8;
     break;
     }
     case 2:
     {blocChiffre =new byte[8];
     progression=8;
     break;
     }
     case 3:
     {blocChiffre =new byte[16];
     progression=16;
     break;
     }
     case 4:
     {blocChiffre =new byte[16];
     progression=16;
     break;
     }
     case 5:
     {blocChiffre =new byte[16];
     progression=16;
     break;
     }
     case 6:
     {blocChiffre =new byte[8];
     progression=8;
     break;
     }
  }
File fichier=new File(urlFichier);
jLabel34.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel35.setText(fichier.length() + " octet(s)");
jLabel36.setText("0 s");
// Déchiffrement de l'éxtension chiffrée
  try
  {algorithme = new AlgorithmeChiffrementSymetrique(algo);
  }
  catch (Exception ex)
  {Logger.getLogger(DechiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
Cipher cipher=algorithme.getCipher();
  try
  {cipher.init(Cipher.DECRYPT_MODE, key,algorithme.getIvSpec());
  }
  catch (InvalidKeyException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField5.setText(null);
  return;
  }
  catch (InvalidAlgorithmParameterException ex)
  {Logger.getLogger(DechiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
CipherInputStream cis = new CipherInputStream(in, cipher);
  try
  {nread = cis.read(tailleExtensionChiffree);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField5.setText(null);
  return;
  }
jProgressBar2.setMaximum((int)fichier.length()-(int)tailleExtensionChiffree[0]-1);
jProgressBar2.setMinimum(0);
extensionChiffree=new byte[(int)tailleExtensionChiffree[0]];
  try
  {nread = cis.read(extensionChiffree);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField5.setText(null);
  return;
  }
String extension=new String(extensionChiffree);
//Création de l'URL de fichier clair.
urlFichier = urlFichier.substring(0, urlFichier.lastIndexOf("."));
urlFichier+=extension;
  try
  {out = new BufferedOutputStream(new FileOutputStream(urlFichier));
  }
  catch (FileNotFoundException ex) 
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField5.setText(null);
  return;
  }
temps = System.currentTimeMillis();
  try
  {  while ((nread = cis.read(blocChiffre)) != -1 )
     {  try
        {out.write(blocChiffre, 0, nread);
        }
        catch (IOException ex)
        {JOptionPane jop=new JOptionPane();
        jop.showMessageDialog(null,"La clé secrète est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
        jTextField5.setText(null);
        return;
        }
     verifierTemps();
     jProgressBar2.setValue(jProgressBar2.getValue()+progression);
     }
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {out.close();
   in.close();
   cis.close();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementSymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de déchiffrement symétrique.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel36.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}
