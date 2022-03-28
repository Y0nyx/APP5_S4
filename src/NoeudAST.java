
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
                return RightLeaf.EvalAST()+LeftLeaf.EvalAST();
            }
            case MULTIPLICATION:
            {
                return RightLeaf.EvalAST()*LeftLeaf.EvalAST();
            }
            case SOUSTRACTION:
            {
                return RightLeaf.EvalAST()-LeftLeaf.EvalAST();
            }
            case DIVISION:
            {
                return RightLeaf.EvalAST()/LeftLeaf.EvalAST();
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
        return ("("+ LeftLeaf.LectAST()+" "+operator.LectTerminal()+" "+RightLeaf.LectAST()+")");
    }

    public String LectASTPostFix () { return ("("+ LeftLeaf.LectASTPostFix()+" "+RightLeaf.LectASTPostFix()+" "+operator.LectTerminal()+")");}
}