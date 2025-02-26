 /**
 * CommandsWords contiendra tous les mots de commandes acceptés
 *
 * @author Thaïs Castillo
 * @version 16/03/24
 */
public class CommandWords
{
    //Attribut 
    private final String[] aValidCommands={"quit", "help", "go","look", "eat", "back", "test", "take", "drop", "inventaire", "charge", "use", "alea"};
    
    /**
     * Fonction qui permet de valider si la commande entrée
     * par l'utilisateur est valide ou non en l'a comparant aux String de son tableau.
     * @param pMot String à vérifier/comparer aux valeurs du tableau. 
     * @return Boolean qui vaut true ou false.
     */
    public boolean isCommand(final String pMot){
        for(int i=0; i<aValidCommands.length; i++){
            if(pMot.equals(aValidCommands[i])){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Affiche toutes les commandes valides du System.out.
     * @return String 
     */
    public String getCommandList(){
        String vCommands="";
        for(String command : aValidCommands){
            vCommands+=command+" ";
        }
        return vCommands;
    }
}//CommandWords
