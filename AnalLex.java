

/** @author Ahmed Khoumsi */

import java.util.ArrayList;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
private int IndiceLecture;
private char[] ListeCaractere;

private char[] Majuscule = {'A','B','C','D','E','F','G','H','I','J','K','L','N','M','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
private char[] Minuscule = {'a','b','c','d','e','f','g','h','i','j','k','l','n','m','o','p','q','r','s','t','u','v','w','x','y','z'};
private char[] Chiffre = {'0','1','2','3','4','5','6','7','8','9'};
private char UnderScore = '_';
	
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
      return IndiceLecture < ListeCaractere.length;
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
          if (ChiffreTerminal()) {
            Temp.add(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.NOMBRE;
            state = EtatLexical.B;
            IndiceLecture++;
          } else if (MajusculeTerminal()) {
            Temp.add(ListeCaractere[IndiceLecture]);
            terminal.type = ULType.ID;
            state = EtatLexical.C;
            IndiceLecture++;
          } else if (ListeCaractere[IndiceLecture] == '+') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.ADDITION);
          } else if (ListeCaractere[IndiceLecture] == '-') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.SOUSTRACTION);
          } else if (ListeCaractere[IndiceLecture] == '/') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.DIVISION);
          } else if (ListeCaractere[IndiceLecture] == '*') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.MULTIPLICATION);
          }else if (ListeCaractere[IndiceLecture] == '(') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.LEFTPARENTHESIS);
          }else if (ListeCaractere[IndiceLecture] == ')') {
            IndiceLecture++;
            return new Terminal(String.valueOf(ListeCaractere[IndiceLecture-1]),ULType.RIGHTPARENTHESIS);
          } else {
            // ERREUR
            if(ListeCaractere[IndiceLecture] != ' '){

              ErreurLex(ULType.NOTSUPPORTED);
              return terminal;
            } else {
              IndiceLecture++;
            }
          }
          break;
        case B:
          if (ChiffreTerminal()) {
            Temp.add(ListeCaractere[IndiceLecture]);
            state = EtatLexical.B;
            IndiceLecture++;
          } else {
            terminal.chaine = Temp.toString().replaceAll("[,\\s\\[\\]]", "");
            state = EtatLexical.A;
            return terminal;
          }
          break;
        case C:
          if(MajusculeTerminal() || MinusculeTerminal()){
            Temp.add(ListeCaractere[IndiceLecture]);
            state = EtatLexical.C;
            IndiceLecture++;
          } else if(ListeCaractere[IndiceLecture] == UnderScore){
            Temp.add(ListeCaractere[IndiceLecture]);
            state = EtatLexical.D;
            IndiceLecture++;
          } else {
            terminal.chaine = Temp.toString().replaceAll("[,\\s\\[\\]]", "");
            state = EtatLexical.A;
            return terminal;
          }
          break;
        case D:
          if(MajusculeTerminal() || MinusculeTerminal()){
            Temp.add(ListeCaractere[IndiceLecture]);
            state = EtatLexical.C;
            IndiceLecture++;
          } else {
            // ERREUR
            if(ListeCaractere[IndiceLecture] != ' '){
              ErreurLex(terminal.type);
              return terminal;
            } else {
              IndiceLecture++;
            }
          }
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + state);
      }
    }
    if(ListeCaractere[IndiceLecture-1] == UnderScore)
    {
      // ERREUR
      IndiceLecture--;
      if(ListeCaractere[IndiceLecture] != ' '){
        ErreurLex(terminal.type);
        return terminal;
      }
    } else terminal.chaine = Temp.toString().replaceAll("[,\\s\\[\\]]", "");
    state = EtatLexical.A;
    return terminal;
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(ULType type) {
     String ToWrite = "";
     System.out.println("////// ERREUR //////" );
     System.out.println(String.valueOf(ListeCaractere));
     for(int i = 0; i < IndiceLecture; i++){
       ToWrite += " ";
     }
     ToWrite += "^";
     System.out.println(ToWrite);
     System.out.println("Ce caractère ne respecte pas les régler du type : " + type);
    System.out.println("Expression régulière non comforme donc, passe à la prochaine expression");

  }

  /**
   * MajusculeTerminal() vérifie sur le caractère à l'indice de lecture est une majuscule
   * @return boolean
   */
  public boolean MajusculeTerminal() {
    for(int i = 0; i < Majuscule.length; i++){
      if(ListeCaractere[IndiceLecture] == Majuscule[i])
        return true;
    }
    return false;
  }

  /**
   * ChiffreTerminal() vérifie sur le caractère à l'indice de lecture est une Chiffre
   * @return boolean
   */
  public boolean ChiffreTerminal() {
    for(int i = 0; i < Chiffre.length; i++){
      if(ListeCaractere[IndiceLecture] == Chiffre[i])
        return true;
    }
    return false;
  }
  /**
   * MinusculeTerminal() vérifie sur le caractère à l'indice de lecture est une Chiffre
   * @return boolean
   */
  public boolean MinusculeTerminal() {
    for(int i = 0; i < Minuscule.length; i++){
      if(ListeCaractere[IndiceLecture] == Minuscule[i])
        return true;
    }
    return false;
  }
  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    System.out.println("Debut d'analyse lexicale");
    StringBuilder ToWriteFile = new StringBuilder();
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);
    String[] Str = (r.toString()).split("\r\n");
    // Execution de l'analyseur lexical
    for(int i = 0; i < Str.length; i++){
      AnalLex lexical = new AnalLex(Str[i]);
      String toWrite = "";
      Terminal t = null;
      System.out.println("////// TEST UNITAIRE " + (i+1) + " //////");
      while(lexical.resteTerminal()){
        t = lexical.prochainTerminal();
        if(t.chaine == null)
          break;
        toWrite += t.chaine;  // toWrite contient le resultat
        for(int j = 0; j < (30 -t.chaine.length()) ; j++)
          toWrite += " ";
        toWrite += t.type + "\n" ;  // toWrite contient le resultat
      }
      System.out.println(toWrite);
      ToWriteFile.append(toWrite);
    }
    Writer w = new Writer(args[1], ToWriteFile.toString()); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
