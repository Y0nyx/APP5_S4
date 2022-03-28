
/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
    private ElemAST RightLeaf;
    private ElemAST LeftLeaf;
    private Terminal operator;
  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(ElemAST RightLeafL,ElemAST LeftLeaf,Terminal operator) { // avec arguments
    this.RightLeaf = RightLeafL;
    this.LeftLeaf = LeftLeaf;
    this.operator = operator;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
    switch(operator.type){
        case ADDITION:
        {
            return LeftLeaf.EvalAST()+RightLeaf.EvalAST();
        }
        case MULTIPLICATION:
        {
            return LeftLeaf.EvalAST()*RightLeaf.EvalAST();
        }
        case SOUSTRACTION:
        {
            return LeftLeaf.EvalAST()-RightLeaf.EvalAST();
        }
        case DIVISION:
        {
            return LeftLeaf.EvalAST()/RightLeaf.EvalAST();
        }
        default:
        {
            return 0;
        }
    }
  }

  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
    return ("("+ LeftLeaf.LectAST()+operator.LectTerminal()+RightLeaf.LectAST()+")");
  }

  public String LectASTPostFix () { return ("("+ LeftLeaf.LectAST()+RightLeaf.LectAST()+operator.LectTerminal()+")");}

  public void ErreurEvalAST(String s) {
      System.out.println("YOU BITCH" + s);
  }
}


