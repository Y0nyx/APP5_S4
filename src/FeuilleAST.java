

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    private Terminal terminal;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal terminal) {  // avec arguments
    this.terminal = terminal;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
        if(this.terminal.type == ULType.NOMBRE)
            return Integer.parseInt(this.terminal.chaine);
        else
            return -1;
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return this.terminal.LectTerminal();
  }

  public void ErreurEvalAST(char op){
        System.out.println("YOU BITCH FEUILLE" + op);
    }

}
