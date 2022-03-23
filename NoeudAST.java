
/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
    private ElemAST RightLeaf;
    private ElemAST LeftLeaf;
    private Terminal operateur;
  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal RightLeafUL,Terminal LeftLeafUL,Terminal operateur) { // avec arguments
    this.RightLeaf = new FeuilleAST(RightLeafUL);
    this.LeftLeaf = new FeuilleAST(LeftLeafUL);
    this.operateur = operateur;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
    switch(operateur.chaine){
        case '+':
        {
            return LeftLeaf.EvalAST()+RightLeaf.EvalAST();
        }
        case '*':
        {
            return LeftLeaf.EvalAST()*RightLeaf.EvalAST();
        }
        case '-':
        {
            return LeftLeaf.EvalAST()-RightLeaf.EvalAST();
        }
        case '/':
        {
            return LeftLeaf.EvalAST()/RightLeaf.EvalAST();
        }
        default:
        {
            ErreurNoeud(operateur.chaine);
            return 0;
        }
    }
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
    return ("("+ LeftLeaf.LectAST()+operateur.toString()+RightLeaf.LectAST()+")");
  }

  private void ErreurNoeud(char op){
      System.out.println("YOU BITCH" + op);
  }

}


