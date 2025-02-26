import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Thaïs Castillo 
 * @version 16/03/24
 */
public class Room
{
    //Attributs
   private String       aDescription;
   private HashMap<String,Room> aExits;
   private String       aImageName;
   private Item         aItem;
   private ItemList     aItems;
   
   /**
    * Constructeur naturel. Créer une pièce décrite par la chaîne 
    * pD. Au départ il n'existe aucune sortie.
    * @param pD String pour initialiser aDescription.
    * @param pImage String pour ajouter une image
    */
   public Room(final String pD, final String pImage)
   {
       this.aDescription = pD;
       this.aExits=new HashMap<String, Room>();
       this.aImageName = pImage;
       this.aItems=new ItemList();
   }
   
   // ###Accesseur###
   /**
    * Retourne la description de la pièce définie dans le constructeur 
    * @return String
    */
   public String getDescription() { return this.aDescription; }
   
   public HashMap<String, Room> getExit(){ return this.aExits;}
   
   /**
    * Procédure qui attribue, à la pièce courante, une sortie. 
    * On utilise cette procédure pour attribuer une seule sortie à une seule pièce.
    * @param pD String qui permet d'avoir la direction.
    * @param pS Room qui permet d'avoir la prochaine pièce à laquelle on peut accéder grâce à la direction pD.
    */
   public void setExit(final String pD ,final Room pS)
   {
       this.aExits.put(pD, pS);
   }
   
   /**
    * Fonction qui renvoie true s'il existe une clée pour une certaine valeur de la HashMap aExits
    * @param pR Room dont on veut connaître l'existence d'une porte
    * @return boolean
    */
   public boolean isExit(final Room pR){
       if(this.aExits.containsValue(pR)){
           return true;
       }
       return false;
   }
   
   /**
    * Fonction qui retourne la pièce atteinte si on se déplace dans la direction pD.
    * @param pD String correspondant à la direction 
    * @return Room qui existe dans cette direction (peut valoir null si elle n'existe pas).
    */
   public Room getExit(final String pD)
   {
       return this.aExits.get(pD);
   }
   
   /**
    * Fonction qui renvoie les sorties d'une pièce.
    * @return String correspondant aux directions possibles 
    * où il existe une pièce.
    */
   public String getExitsString(){
       String vSorties="";
       Set<String> vKeys=aExits.keySet();
       for(String vExit: vKeys){
           vSorties+=" "+vExit+ " "+ this.aExits.get(vExit).getDescription();
       }
       return "Exits: "+vSorties;
       
   }
   
   /**
    * Renvoie une description détaillée de cette pièce
    * @return String description de la pièce avec les sorties
    */
   public String getLongDescription(){
       return " Vous êtes "+this.aDescription+".\n"+this.getExitsString()+"\n"+this.aItems.getItemStringDescription()+this.aItems.getItemStringNom();
   }
   
   /**
     * Fonction renvoyant une description de l'image
     * @return String décrit l'image
     */
    public String getImageName()
    {
    return this.aImageName;
    }
   
   
   /**
    * Retourne l'Item de la pièce
    * @param pN String correspondant au nom de l'Item
    * @return Item
    */
   public Item getItem(final String pN) {
        return this.aItems.getItem(pN);
    }
    
   /**
    * Procédure permettant d'ajouter un Item
    * @param pNom String correspondant au nom de l'item
    * @param pD String correspondant à la description de la pièce
    * @param pW int correspondant au poids de l'item
    */
   public void ajouteItem(final String pNom, final String pD, final int pW) {
        Item vItem = new Item(pNom, pD, pW);
        this.aItems.addItem(vItem);
    }
    
   public void ajouteBeamer(final Beamer pB){
       this.aItems.addItem(pB);
   }
   
    /**
     * Procédure permettant de supprimer des Items
     * @param pI Item
     */
   public void removeItem(final Item pI){
       this.aItems.removeItem(pI);
   }
   
   /**
     * Procédure permettant d'ajouter des Items
     * @param pI Item
     */
   public void addItem(final Item pI) {
        this.aItems.addItem(pI);
    }
   
} // Room
