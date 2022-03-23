

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
//  ...

	
/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String s) {  // arguments possibles
    Terminal temp = new Terminal();
    char[] temp2 = s.toCharArray();
    char[] temp3 = new char[temp2.length];
    EtatLexical state = EtatLexical.A;
    for(int i = 0; i < temp2.length; i++){
      switch (state){
        case A :
          if(temp2[i] == '1' || temp2[i] == '0'){
            temp3[i] = temp2[i];
            state = EtatLexical.B;
          }
          else if(temp2[i] == '+'){
            temp3[i] = temp2[i];
            temp.setTerminaux(String.copyValueOf(temp3));
            temp3 = new char[temp2.length];
          }
          else{
            ErreurLex(String.copyValueOf(temp3));
          }
          break;
        case B :
          if(temp2[i] == '1' || temp2[i] == '0'){
            temp3[i] = temp2[i];
            state = EtatLexical.B;
          }
          else{
            i--;
            temp.setTerminaux(String.copyValueOf(temp3));
            state = EtatLexical.A;
            temp3 = new char[temp2.length];
          }
          break;
      }
        System.out.println(temp.toString());
    }
  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) {
    return true;
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  //public Terminal prochainTerminal( ) {
     //
 // }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {	
     //
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);
    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    //while(lexical.resteTerminal()){
      //t = lexical.prochainTerminal();
      //toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
    //}				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
