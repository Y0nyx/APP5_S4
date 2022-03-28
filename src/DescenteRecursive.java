

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private Terminal terminals[] = new Terminal[20];
  private ElemAST AST;
  private int IndiceLecture;
/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in) {
  Reader r = new Reader(in);
  String[] Attributes = (r.toString()).split("\r\n");
  for(int i = 0; i < Attributes.length; i++){
    String[] Element = Attributes[i].split("\\s+");
    if(Element[1].compareTo("ADDITION") == 0)
      terminals[i] = new Terminal(Element[0], ULType.ADDITION);
    else if(Element[1].compareTo("MULTIPLICATION") == 0)
      terminals[i] = new Terminal(Element[0], ULType.MULTIPLICATION);
    else if(Element[1].compareTo("DIVISION") == 0)
      terminals[i] = new Terminal(Element[0], ULType.DIVISION);
    else if(Element[1].compareTo("SOUSTRACTION") == 0)
      terminals[i] = new Terminal(Element[0], ULType.SOUSTRACTION);
    else if(Element[1].compareTo("ID") == 0)
      terminals[i] = new Terminal(Element[0], ULType.ID);
    else if(Element[1].compareTo("NOMBRE") == 0)
      terminals[i] = new Terminal(Element[0], ULType.NOMBRE);
    else if(Element[1].compareTo("LEFTPARENTHESIS") == 0)
      terminals[i] = new Terminal(Element[0], ULType.LEFTPARENTHESIS);
    else if(Element[1].compareTo("RIGHTPARENTHESIS") == 0)
      terminals[i] = new Terminal(Element[0], ULType.RIGHTPARENTHESIS);
  }
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
    IndiceLecture = 0;
    return X();
}

public ElemAST X(){
  ElemAST n1,n2;
  NoeudAST noeudAST = null;
  n1 = Y();
  if(terminals[IndiceLecture].type == ULType.ADDITION){
    IndiceLecture++;
    n2 = X();
    noeudAST = new NoeudAST(n1,n2,new Terminal("+", ULType.ADDITION));
  }else if(terminals[IndiceLecture].type == ULType.SOUSTRACTION){
    IndiceLecture++;
    n2 = Y();
    noeudAST = new NoeudAST(n1,n2,new Terminal("+", ULType.SOUSTRACTION));
  }
  return noeudAST;
}

public ElemAST Y(){
  ElemAST n1,n2;
  NoeudAST noeudAST = null;
  n1 = Z();
  if(terminals[IndiceLecture].type == ULType.MULTIPLICATION){
    IndiceLecture++;
    n2 = Y();
    noeudAST = new NoeudAST(n1,n2,new Terminal("+", ULType.MULTIPLICATION));
  }else if(terminals[IndiceLecture].type == ULType.DIVISION){
    IndiceLecture++;
    n2 = Y();
    noeudAST = new NoeudAST(n1,n2,new Terminal("+", ULType.DIVISION));
  }
  return noeudAST;
}

public ElemAST Z(){
  ElemAST elemAST = null;
  if(terminals[IndiceLecture].type == ULType.NOMBRE){
    elemAST = new FeuilleAST(terminals[IndiceLecture]);
    IndiceLecture++;
  }else if(terminals[IndiceLecture].type == ULType.LEFTPARENTHESIS){
    elemAST = X();
    if(terminals[IndiceLecture].type == ULType.RIGHTPARENTHESIS){
      IndiceLecture++;
    } else {
      ErreurSynt(" ");
    }
  }else{
    ErreurSynt(" ");
  }
  return elemAST;
}



/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    //
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArithSynth.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

