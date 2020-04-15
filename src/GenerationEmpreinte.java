
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GenerationEmpreinte
{private static javax.swing.JTextField jTextField12,jTextField14;
private static javax.swing.JProgressBar jProgressBar7;
private static javax.swing.JLabel jLabel46,jLabel47,jLabel48;
private long temps;
private int tempsEcoule = 0;

public GenerationEmpreinte(javax.swing.JTextField jTextField12,javax.swing.JProgressBar jProgressBar7,
                         javax.swing.JLabel jLabel46,javax.swing.JLabel jLabel47,
                         javax.swing.JLabel jLabel48)
{this.jTextField12=jTextField12;
this.jProgressBar7=jProgressBar7;
this.jTextField14=jTextField14;
this.jLabel46=jLabel46;
this.jLabel47=jLabel47;
this.jLabel48=jLabel48;
}
public String generer(int fonction,String urlFichier)
{BufferedInputStream in;
int nread = 0;
byte[] bloc=new byte[1024];
MessageDigest md = null;
String empreinteNumerique;

// Création d'un objet de génération d'une empreinte numérique.
  switch(fonction)
  {  case 0:
     {  try
        { md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 1:
     {  try
        { md = MessageDigest.getInstance("SHA1");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 2:
     {  try
        { md = MessageDigest.getInstance("SHA224");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 3:
     {  try
        { md = MessageDigest.getInstance("SHA256");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 4:
     {  try
        { md = MessageDigest.getInstance("SHA384");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 5:
     {  try
        { md = MessageDigest.getInstance("SHA512");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 6:
     {  try
        { md = MessageDigest.getInstance("RIPEMD160");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 7:
     {  try
        { md = MessageDigest.getInstance("RIPEMD256");
        }
        catch (NoSuchAlgorithmException ex)
        {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
        }
     break;
     }
     case 8:
     {  try
        { md = MessageDigest.getInstance("RIPEMD320");
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
  jTextField12.setText(null);
  return null;
  }
// Initialisation des variables et des objets.
File fichier=new File(urlFichier);
jProgressBar7.setMaximum((int)fichier.length());
jProgressBar7.setMinimum(0);
jLabel46.setText(urlFichier.substring(urlFichier.lastIndexOf(File.separator)+1, urlFichier.length()) );
jLabel47.setText(fichier.length() + " octet(s)");
jLabel48.setText("0 s");
// Génération d'une empreinte numérique.
  try
  {nread = in.read(bloc);
  }
  catch (IOException ex)
  {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
  }
temps = System.currentTimeMillis();
  while ((nread!=-1))
  {md.update(bloc, 0, nread);
    try
    {nread = in.read(bloc);
    }
    catch (IOException ex)
    {Logger.getLogger(GenerationEmpreinte.class.getName()).log(Level.SEVERE, null, ex);
    }
  verifierTemps();
  jProgressBar7.setValue(jProgressBar7.getValue()+1024);
  }
byte[] mdbytes = md.digest();
return empreinteNumerique=Utils.toHex(mdbytes);
}
//Méthode qui nous permet de déterminer le temps écoulé durant l'opération de calcul de l'empreinte.
private void verifierTemps()
{  if(System.currentTimeMillis()-temps >= 1000)
   {jLabel48.setText((++tempsEcoule) + " s");
   temps = System.currentTimeMillis();
   }
}
}