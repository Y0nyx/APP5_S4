

/** @author Ahmed Khoumsi */

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {


  /**
   * Evaluation d'AST
   */
  public abstract int EvalAST();


  /**
   * Lecture d'AST
   */
  public abstract String LectAST();

  /**
   * PostFix
   */
  public abstract String LectASTPostFix();


  /**
   * ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
   */
  public void ErreurEvalAST(String s) {
    System.out.println("Ce terme ("+s+") ne peux pas être Evaluer car Il n'est pas un nombre");
  }
}

