import java.util.ArrayList;
import java.util.List;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
private List<String> terminaux = new ArrayList<String>();


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal( ) {   // arguments possibles
     //
  }

  public void setTerminaux(String termeTerminal) {
    this.terminaux.add(termeTerminal);
  }

  public List<String> getTerminaux() {
    return terminaux;
  }

  public String getTerminaux(int i) {
    return terminaux.get(i);
  }

  public String toString(){
    return terminaux.toString();
  }
}
