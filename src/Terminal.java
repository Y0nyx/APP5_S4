import java.util.ArrayList;
import java.util.List;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
public ULType type;
public String chaine;


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal() { }

  public Terminal(String c, ULType type){
    this.chaine = c;
    this.type = type;
  }

  public String LectTerminal() { return this.chaine; }
}
