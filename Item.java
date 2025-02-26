/**
 * Cette classe permet d'ajouter des Items dans chaque pièce
 *
 * @author Thaïs Castillo
 * @version 16/03/24
 */
public class Item
{
    private String aItemDescription;
    private int aItemWeight;
    private String aNomItem;

    /**
     * Constructeur naturel. Créer un Item décrit par la chaîne.
     * @param pD String pour intialiser aItemDescription
     * @param pW Entier pour initialiser aItemWeight
     * @param pN String
     */
    public Item(final String pN,final String pD, final int pW){
        this.aNomItem=pN;
        this.aItemDescription=pD;
        this.aItemWeight=pW;
    }
    
    /**
     * Retourne le nom de l'Item
     * @return String 
     */
    public String getNomItem(){ return this.aNomItem;}
    
    /**
     * Procédure qui attribue à un Item son nom 
     * @param pN String pour intialiser aNomItem
     */
    public void setNomItem(final String pN){ this.aNomItem=pN;}
    
    /**
     * Retourne la description de l'Item
     * @return String 
     */
    public String getItemDescription(){ return this.aItemDescription;}
    
    /**
     * Retourne le poids de l'Item
     * @return int
     */
    public int getItemWeight(){ return this.aItemWeight;}
    
    /**
     * Procédure qui attribue à un Item sa descrition 
     * @param pD String pour intialiser aItemDescription
     */
    public void setItemDescription(final String pD){
        this.aItemDescription=pD;
    }
    
    /**
     * Procédure qui attribue à un Item son poids 
     * @param pW Entier pour initialiser aItemWeight 
     */
    public void setItemWeight(final int pW){
        this.aItemWeight=pW;
    }
    
}
