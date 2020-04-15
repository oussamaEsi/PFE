import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Main 
{public static void main(String[] args)
{Security.addProvider (new BouncyCastleProvider ());

  try {
    UIManager.setLookAndFeel(new BernsteinLookAndFeel() );
  }
  catch (UnsupportedLookAndFeelException ex) {
   Logger.getLogger(InterfacePrincipale.class.getName()).log(Level.SEVERE, null, ex);
  }

  try
  {InterfacePrincipale interfacePrincipale = new InterfacePrincipale();
  }
  catch (UnsupportedLookAndFeelException ex)
  {Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
  }

}
}
