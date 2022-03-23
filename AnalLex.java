

/** @author Ahmed Khoumsi */

import java.util.ArrayList;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
private int IndiceLecture;
private char[] ListeCaractere;

	
/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String s) {  // arguments possibles
    ListeCaractere = s.toCharArray();
    IndiceLecture = 0;
  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) {
    if(IndiceLecture < ListeCaractere.length)
      return true;
    else
      return false;
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal( ) {
    ArrayList<Character> Temp = new ArrayList<Character>();
    Terminal terminal = new Terminal();
    EtatLexical state = EtatLexical.A;
    while(resteTerminal()) {
      switch (state) {
        case A:
          if (ListeCaractere[IndiceLecture] == '1' ||
                  ListeCaractere[IndiceLecture] == '0') {
            Temp.add(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.NOMBRE;
            state = EtatLexical.B;
            IndiceLecture++;
          } else if (ListeCaractere[IndiceLecture] == '+') {
            terminal.chaine = String.valueOf(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.ADDITION;
            IndiceLecture++;
            return terminal;
          } else if (ListeCaractere[IndiceLecture] == '-') {
            terminal.chaine = String.valueOf(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.SOUSTRACTION;
            IndiceLecture++;
            return terminal;
          } else if (ListeCaractere[IndiceLecture] == '/') {
            terminal.chaine = String.valueOf(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.DIVISION;
            IndiceLecture++;
            return terminal;
          } else if (ListeCaractere[IndiceLecture] == '*') {
            terminal.chaine = String.valueOf(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.MULTIPLICATION;
            IndiceLecture++;
            return terminal;
          } else {
            Temp.add(ListeCaractere[IndiceLecture]);
            ErreurLex(Temp.toString());
            return terminal;
          }
          break;
        case B:
          if (ListeCaractere[IndiceLecture] == '1' ||
                  ListeCaractere[IndiceLecture] == '0') {
            Temp.add(ListeCaractere[IndiceLecture]);
            state = EtatLexical.B;
              IndiceLecture++;
          } else {
            terminal.chaine = Temp.toString().replaceAll("[,\\s\\[\\]]", "");
            state = EtatLexical.A;
            return terminal;
          }
      }
    }
    terminal.chaine = Temp.toString().replaceAll("[,\\s\\[\\]]", "");
    state = EtatLexical.A;
    return terminal;
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {	
     System.out.println("FUCK YOU CHARLES" + s);
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
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
