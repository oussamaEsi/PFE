
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class DechiffrementAsymetrique
{private static javax.swing.JTextField jTextField14,jTextField10;
private static javax.swing.JProgressBar jProgressBar4;
private static javax.swing.JLabel jLabel40,jLabel41,jLabel42;
private long temps;
private int tempsEcoule = 0;

public DechiffrementAsymetrique(javax.swing.JTextField jTextField14,javax.swing.JTextField jTextField10,
                                javax.swing.JProgressBar jProgressBar4,javax.swing.JLabel jLabel40,
                                javax.swing.JLabel jLabel41,javax.swing.JLabel jLabel42)
                                
{this.jTextField14=jTextField14;
this.jTextField10=jTextField10;
this.jProgressBar4=jProgressBar4;
this.jLabel40=jLabel40;
this.jLabel41=jLabel41;
this.jLabel42=jLabel42;
}
DechiffrementAsymetrique(javax.swing.JTextField jTextField14,javax.swing.JTextField jTextField10)
{this.jTextField14=jTextField14;
this.jTextField10=jTextField10;
}
public void dechiffrerFichier(int algo,String urlClePrivee,String urlFichier)
{RSAPrivateKey clePrivee = null;
BufferedInputStream in2;
BufferedOutputStream out = null;
int nread = 0,progression = 0,tailleExtensionChiffree = 0,nombreIterations=0,tailleDernierBlocChiffre=0;
byte[] extensionChiffre = null,extensionClair,blocChiffre = null,blocClair = null,dernierBlocChiffre=null;
RSA rsa = null ;
String tailleCle = null;;
ObjectInputStream in1;

// Récupération de la clé privee.
  try
  {in1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(urlClePrivee))));
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est introuvable ou invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField14.setText(null);
  return;
  }
  try
  {clePrivee = (RSAPrivateKey) in1.readObject();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (ClassNotFoundException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {in1.close();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
//Vérification de la taille de la clé privée.
Pattern modeleTailleCle=Pattern.compile("^Sun RSA private CRT key, (\\d+) bits");
Matcher resultat=modeleTailleCle.matcher(clePrivee.toString());
  if(resultat.find())
  {tailleCle=resultat.group(1);
  }
  if((tailleCle.equals("1024") && algo==1)||(tailleCle.equals("2048")&& algo==0))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField14.setText(null);
  return;
  }
//Vérificatiion de l'éxistence du fichier chiffré.
  try
  {in2 = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField10.setText(null);
  return;
  }
//Lecture de l'éxtension chiffrée.
  switch(algo)
  {  case 0 :
     {extensionChiffre=new byte[128];
     break;
     }
     case 1 :
     {extensionChiffre=new byte[256];
     break;
     }
  }
  try
  {nread = in2.read(extensionChiffre);
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
File fichier=new File(urlFichier);
jLabel40.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
//Création de l'URL du fichier clair.
  try
  {rsa = new RSA();
  }
  catch (Exception ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {extensionClair = rsa.dechiffrer(extensionChiffre, clePrivee);
  }
  catch (Exception ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField14.setText(null);
  return;
  }
String extension=new String(extensionClair);
urlFichier = urlFichier.substring(0, urlFichier.lastIndexOf(".")); 
urlFichier+=extension;
//Initialisation des variables et des objets.
  switch(algo)
  {  case 0 :
     {blocChiffre=new byte[128];
     progression=128;
     tailleExtensionChiffree=128;
     nombreIterations = (int)((fichier.length()-128)/128);
     tailleDernierBlocChiffre = (int) ((fichier.length()-128)%128);
     dernierBlocChiffre = new byte [tailleDernierBlocChiffre];
     break;
     }
     case 1:
     {blocChiffre=new byte[256];
     progression=256;
     tailleExtensionChiffree=256;
     nombreIterations = (int)((fichier.length()-256)/256);
     tailleDernierBlocChiffre = (int) ((fichier.length()-256)%256);
     dernierBlocChiffre = new byte [tailleDernierBlocChiffre];
     break;
     }
  }
jProgressBar4.setMaximum((int)fichier.length()-tailleExtensionChiffree);
jProgressBar4.setMinimum(0);
jLabel41.setText(fichier.length() + " octet(s)");
jLabel42.setText("0 s");
//Déchiffrement du fichier chiffré.
  try
  {out = new BufferedOutputStream(new FileOutputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
temps = System.currentTimeMillis();
  for (int i=0;i<nombreIterations;i++)
  {  try
     {nread = in2.read(blocChiffre);
     }
     catch (IOException ex)
     {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
     }
     try
     {blocClair = rsa.dechiffrer(blocChiffre, clePrivee);
     }
     catch (Exception ex)
     {JOptionPane jop=new JOptionPane();
     jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
     jTextField14.setText(null);
     return;
     }
     try
     {out.write(blocClair);
     }
     catch (IOException ex)
     {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
     }
  verifierTemps();
  jProgressBar4.setValue(jProgressBar4.getValue()+progression);
  }
    if(tailleDernierBlocChiffre!=0)
    {  try
       {nread = in2.read(dernierBlocChiffre);
       }
       catch (IOException ex)
       {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
       }
       try
       {blocClair = rsa.dechiffrer(dernierBlocChiffre, clePrivee);
       }
       catch (Exception ex)
       {JOptionPane jop=new JOptionPane();
       jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
       jTextField14.setText(null);
       return;
       }
       try
       {out.write(blocClair);
       }
       catch (IOException ex)
       {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
       }
    verifierTemps();
    jProgressBar4.setValue(jProgressBar4.getValue()+tailleDernierBlocChiffre);
    }
  try
  {in2.close();
  out.close();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de déchiffrement asymétrique.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel42.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
public byte[] dechiffrerCleSession(String urlClePrivee,String urlCleSession)
{String cleSessionChiffree;
ObjectInputStream in1;
BufferedReader in2;
RSAPrivateKey clePrivee = null;
RSA rsa = null;
byte[] cleSessionClaire;

// Récupération de la clé privée.
  try
  {in1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(urlClePrivee))));
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est introuvable ou invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField14.setText(null);
  return null;
  }
  try
  {clePrivee = (RSAPrivateKey) in1.readObject();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  catch (ClassNotFoundException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {in1.close();
  }
  catch (IOException ex)
  {Logger.getLogger(DechiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
// Récupération de la clé de session chiffrée.
  try 
  {in2 = new BufferedReader(new FileReader(urlCleSession));
  cleSessionChiffree = in2.readLine();
  in2.close();
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé de session est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField10.setText(null);
  return null;
  }
//Déchiffrement de la clé du session.
  try
  {rsa = new RSA();
  }
  catch (Exception ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {cleSessionClaire =rsa.dechiffrer(Utils.toBytes(cleSessionChiffree),clePrivee);
  }
  catch (Exception ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé privée est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField14.setText(null);
  return null;
  }
return cleSessionClaire;
}
}