
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class ChiffrementAsymetrique
{private static javax.swing.JTextField jTextField7,jTextField8;
private static javax.swing.JProgressBar jProgressBar3;
private static javax.swing.JLabel jLabel40,jLabel41,jLabel42;
private long temps;
private int tempsEcoule = 0;

public ChiffrementAsymetrique(javax.swing.JTextField jTextField7,javax.swing.JTextField jTextField8,
                             javax.swing.JProgressBar jProgressBar3,javax.swing.JLabel jLabel40,
                             javax.swing.JLabel jLabel41,javax.swing.JLabel jLabel42)

{this.jTextField7=jTextField7;
this.jTextField8=jTextField8;
this.jProgressBar3=jProgressBar3;
this.jLabel40=jLabel40;
this.jLabel41=jLabel41;
this.jLabel42=jLabel42;
}

ChiffrementAsymetrique(javax.swing.JTextField jTextField7,javax.swing.JTextField jTextField8)
{this.jTextField7=jTextField7;
this.jTextField8=jTextField8;
}
public void chiffrerFichier(int algo,String urlCertificat,String proprietaireCertificat,String urlFichier)
{BufferedInputStream in2;
BufferedOutputStream out = null;
RSAPublicKey clePublique = null;
byte[]blocClair = null,blocChiffre = null,extensionChiffree = null, dernierBlocClair = null;
int nread = 0,progression = 0, nombreIterations = 0,tailleDernierBlocClair = 0;
RSA rsa = null;
String tailleCle = null;
InputStream in1;
CertificateFactory certFact = null;
X509Certificate certificat = null;

// Récupération du certificat numérique.
  try
  {in1 = new FileInputStream(urlCertificat);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return;
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
  jTextField7.setText(null);
  return;
  }
  try
  {certificat.checkValidity(new Date());
  }
  catch (CertificateExpiredException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est expiré.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return;
  }
  catch (CertificateNotYetValidException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  if(!(proprietaireCertificat.equals(certificat.getSubjectDN().toString())))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat n'appartient pas au propriétaire saisi.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return;
  }
// Récupération de la clé publique du certificat numérique.
clePublique =  (RSAPublicKey) certificat.getPublicKey();
//Vérification de la taille de la clé publique.
Pattern modeleTailleCle=Pattern.compile("^Sun RSA public key, (\\d+) bits");
Matcher resultat=modeleTailleCle.matcher(clePublique.toString());
  if(resultat.find())
  {tailleCle=resultat.group(1);
  }
  if((tailleCle.equals("1024") && algo==1)||(tailleCle.equals("2048")&& algo==0))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return;
  }
//Vérifier l'existence du fichier clair.
  try
  {in2 = new BufferedInputStream(new FileInputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le fichier de données est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField8.setText(null);
  return;
  }
//Chiffrement de l'éxtension du fichier clair.
String extension = urlFichier.substring(urlFichier.lastIndexOf("."), urlFichier.length());
  try
  {rsa = new RSA();
  }
  catch (Exception ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {extensionChiffree = rsa.chiffrer(extension.getBytes(),clePublique);
  }
  catch (Exception ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return;
  }
File fichier=new File(urlFichier);
jLabel40.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
//Création de l'URL du fichier chiffré.
urlFichier = urlFichier.substring(0, urlFichier.lastIndexOf("."));
  switch(algo)
  {  case 0:
     {urlFichier+="-RSA1024.crp";
      break;
     }
     case 1:
     {urlFichier+="-RSA2048.crp";
      break;
     }
  }
//Ecriture de l'éxtension chiffrée dans le fichier chiffré.
  try
  {out = new BufferedOutputStream(new FileOutputStream(urlFichier));
  }
  catch (FileNotFoundException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {out.write(extensionChiffree);
  }
  catch (IOException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
//Initialisation des variables.
  switch(algo)
  {  case 0 :
     {blocClair=new byte[117];
     progression=117;
     nombreIterations = (int)(fichier.length()/117);
     tailleDernierBlocClair = (int) (fichier.length()%117);
     dernierBlocClair = new byte [tailleDernierBlocClair];
     break;
     }
     case 1 :
     {blocClair=new byte[245];
     progression=245;
     nombreIterations = (int)(fichier.length()/245);
     tailleDernierBlocClair = (int) (fichier.length()%245);
     dernierBlocClair = new byte [tailleDernierBlocClair];
     break;
     }
  }
jProgressBar3.setMaximum((int)fichier.length());
jProgressBar3.setMinimum(0);
jLabel41.setText(fichier.length() + " octet(s)");
jLabel42.setText("0 s");
temps = System.currentTimeMillis();
//Chiffrement de fichier clair.
  for (int i = 0;i<nombreIterations;i++)
  {  try
     {nread = in2.read(blocClair);
     }
     catch (IOException ex)
     {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
     }
     try
     {blocChiffre = rsa.chiffrer(blocClair, clePublique);
     }
     catch (Exception ex)
     {JOptionPane jop=new JOptionPane();
     jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
     jTextField7.setText(null);
     return;
     }
     try
     {out.write(blocChiffre);
     }
     catch (IOException ex)
     {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
     }
   verifierTemps();
   jProgressBar3.setValue(jProgressBar3.getValue()+progression);
  }
    if(tailleDernierBlocClair != 0)
    {  try
       {nread = in2.read(dernierBlocClair);
       }
       catch (IOException ex)
       {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
       }
       try
       {blocChiffre = rsa.chiffrer(dernierBlocClair, clePublique);
       }
       catch (Exception ex)
       {JOptionPane jop=new JOptionPane();
       jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
       jTextField7.setText(null);
       return;
       }
       try
       {out.write(blocChiffre);
       }
       catch (IOException ex)
       {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
       }
    verifierTemps();
    jProgressBar3.setValue(jProgressBar3.getValue()+tailleDernierBlocClair);
    }
  try
  {in2.close();
  out.close();
  }
  catch (IOException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de chiffrement asymétrique.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel42.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
public byte[] chiffrerCleSession(String urlCertificat,String proprietaireCertificat,String cleSession)
{InputStream in;
RSAPublicKey clePublique = null;
RSA rsa = null;
byte[] cleSessionChiffree;
CertificateFactory certFact = null;
X509Certificate certificat = null;

//Vérification de la clé de session.
  if(cleSession==null || cleSession.equals(""))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Générer une clé de session.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField7.setText(null);
  return null;
  }
// Récupération du certificat numérique.
  try
  {in = new FileInputStream(urlCertificat);
  }
  catch (IOException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est introuvable.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField8.setText(null);
  return null;
  }
  try
  {certFact = CertificateFactory.getInstance("X.509");
  }
  catch (CertificateException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {certificat = (X509Certificate) certFact.generateCertificate(in);
  }
  catch (CertificateException ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {in.close();
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
  jTextField8.setText(null);
  return null;
  }
  try
  {certificat.checkValidity(new Date());
  }
  catch (CertificateExpiredException ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat est expiré.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField8.setText(null);
  return null;
  }
  catch (CertificateNotYetValidException ex)
  {Logger.getLogger(GenerationSignature.class.getName()).log(Level.SEVERE, null, ex);
  }
  if(!(proprietaireCertificat.equals(certificat.getSubjectDN().toString())))
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"Le certificat n'appartient pas au propriétaire saisi.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField8.setText(null);
  return null;
  }

// Récupération de la clé publique du certificat numérique.
clePublique =  (RSAPublicKey) certificat.getPublicKey();
//Chiffrement de la clé du session.
  try
  {rsa = new RSA();
  }
  catch (Exception ex)
  {Logger.getLogger(ChiffrementAsymetrique.class.getName()).log(Level.SEVERE, null, ex);
  }
  try
  {cleSessionChiffree = rsa.chiffrer(cleSession.getBytes(),clePublique);
  }
  catch (Exception ex)
  {JOptionPane jop=new JOptionPane();
  jop.showMessageDialog(null,"La clé publique est invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
  jTextField8.setText(null);
  return null;
  }
return cleSessionChiffree ;
}
}