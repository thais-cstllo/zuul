/**
 * Cette classe permet de gérer et analyser les commandes entré par l'utilisateur.
 *
 * @author votre Thaïs Castillo
 * @version 16/03/2024
 */
public class Command
{
    // Attributs
    private String aCommandWord;
    private String aSecondWord;
    
    /**
    * Constructeur naturel
    * @param pC String pour initialiser aCommandWord
    * @param pS String pour initialiser aSecondWord
    */
    public Command(final String pC, final String pS)
    {
        this.aCommandWord=pC;
        this.aSecondWord=pS;
    }
    
    // ###Accesseur###
    /**
     * Fonction renvoyant le premier mot de la commande
     * @return String correspond au premier mot
     */
    public String getCommandWord(){ return this.aCommandWord;}
    
    /**
     * Fonction renvoyant le second mot de la commande
     * @return String correspond au second mot
     */
    public String getSecondWord(){ return this.aSecondWord;}
    
    /**
    * Fonction retournant true s'il existe un second mot 
    * ou false sinon.
    * @return Boolean qui vaut true ou false. 
    */
    public boolean hasSecondWord()
    {
        return this.getSecondWord()!=null;
    }
    
    /**
    * Fonction retournant true si le premier mot vaux 
    * null (ou une commande invalide) ou false sinon.
    * @return Boolean qui vaut true ou false.
    */
    public boolean isUnknown()
    {
        return this.getCommandWord()==null;
    }
} // Command
