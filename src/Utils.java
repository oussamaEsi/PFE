public class Utils
{private static String digits = "0123456789abcdef";

/* Convertir un tableau d'octets en chaine hexadécimale. */
public static String toHex(byte[] data)
{StringBuffer    buf = new StringBuffer();
  if(data!=null)
  {  for(int i = 0; i<data.length; i++)
     {int v = data[i] & 0xff;
     buf.append(digits.charAt(v >> 4));
     buf.append(digits.charAt(v & 0xf));
     }
  }
return buf.toString();
}
public static byte[] toBytes(String chaine)
{byte octet1=0,octet2;
byte[] data=new byte[chaine.length()/2] ;
char c;
int a,j=0;
  for(int i=0;i<chaine.length();i++)
  {  if(i%2==0)
     {c=chaine.charAt(i);
	   if(('0'<=c)&&(c<='9'))
	   {a=c-48;
	   octet1=(byte) a;
	   octet1=(byte) (octet1 << 4);
	   }
	   else
	   {  if(('a'<=c)&&(c<='f'))
	      {a=c-87;
	      octet1=(byte) a;
	      octet1=(byte) (octet1 << 4);
	      }
	      else
	      {return null;
	      }
	   }
     }
     else
     {c=chaine.charAt(i);
       if(('0'<=c)&&(c<='9'))
       {a=c-48;
       octet2=(byte) a;
       octet1=(byte) (octet1 | octet2);
       data[j]=octet1;
       j++;
       }
       else
       {  if(('a'<=c)&&(c<='f'))
          {a=c-87;
          octet2=(byte) a;
          octet1=(byte) (octet1 | octet2);
          data[j]=octet1;
          j++;
          }
          else
          {return null;
          }
       }
     }
  }
return data;
}
/* Modifier une chaine de caractéres pour ajuster son affichage sur JTextArea. */
public static String alignerChaine(String chaine,int longueur)
{int i=0;
String sousChaine;
String resultat ="";
  if(chaine!=null)
  {  while(i+longueur<=chaine.length())
     {sousChaine=chaine.substring(i, i+longueur)+"\n";
     resultat+=sousChaine;
     i+=longueur;
     }
  sousChaine=chaine.substring(i, chaine.length());
  resultat+=sousChaine;
  }
return resultat;
}
/* Ecrire une chaine de caractéres sur une seule ligne en éliminant les éspaces
et les retours à la ligne. */
public static String etalerChaine(String chaine)
{int i=0;
String resultat ="";
  if(chaine !=null)
  {  while(i<chaine.length())
     {  if((chaine.charAt(i)!=' ')&&(chaine.charAt(i)!='\n'))
	{resultat+=chaine.charAt(i);
	}
     i++;
     }
  }
return resultat;
}
}	