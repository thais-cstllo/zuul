import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe permet de gérer des collections d'Items.
 *
 * @author Thaïs Castillo
 * @version 29/03/2024
 */
public class ItemList
{
    private HashMap<String, Item> aItems;
    
    /**
     * Constructeur par défaut
     */
    public ItemList(){
        this.aItems = new HashMap<String, Item>();
    }
    
    /**
     * Procédure permettant d'ajouter des Items
     * @param pI Item
     */
   public void addItem(final Item pI) {
        this.aItems.put(pI.getNomItem(), pI);
    }
    
     /**
     * Procédure permettant de supprimer des Items
     * @param pI Item
     */
   public void removeItem(final Item pI){
       this.aItems.remove(pI.getNomItem());
   }
   
   /**
    * Fonction qui renvoie la description de l'Item présent dans la pièce
    * @return String décrivant chaque item
    */
   public String getItemStringDescription() {
        String vD = "";
        if (this.aItems.isEmpty()) {
            return vD + "Il n'y a pas d'Item ici";
        }
        
        Set<String> vKey = this.aItems.keySet();
        for ( String pI : vKey ){
            Item vI=this.aItems.get(pI);
            vD += vI.getItemDescription();
        }
        return "Il y a :"+"\n"+ vD+"\n";

    }
    
    /**
     * Fonction permettant d'afficher les nom des items
     * @return String
     */
    public String getItemStringNom() {
        String vD = "";
        if (this.aItems.isEmpty()) {
            return vD;
        }
        
        Set<String> vKey = this.aItems.keySet();
        for ( String pI : vKey ){
            Item vI=this.aItems.get(pI);
             vD += vI.getNomItem() + " ";
        }
        return "Tu peux prendre : " + vD;
    }
    
    /**
    * Retourne l'Item de la pièce
    * @param pN String correspondant au nom de l'Item
    * @return Item
    */
   public Item getItem(final String pN) {
        return this.aItems.get(pN);
    }
    
    /**
     * Fonction permettant d'afficher les items que possède l'utilisateur
     * @return String 
     */
    public String getAllItemsString(){
    if(this.aItems.isEmpty()){
        return "Tu ne possède pas d'Item";
    }
    Set<String> vKey = this.aItems.keySet();
    String vD="";
    for ( String pI : vKey ){
        Item vI=this.aItems.get(pI);
        int vW=vI.getItemWeight();
        vD=vD+pI+" "+":"+vW+"\n";
    }
    return "Tu possède :"+"\n"+vD;
    }
    
    /**
     * Fonction permettant de savoir si l'utilisateur possède l'objet coeur dans son inventaire
     * @return boolean
     */
    public boolean coeur(){
        if(this.aItems.get("coeur")==null){ //on vérifie si l'inventaire contient bien le coeur
            return false;
        }
        return true;
    }
    
    /**
     * Fonction permettant de savoir si l'utilisateur possède l'objet téléportateur dans son inventaire
     * @return boolean
     */
    public boolean teleporteur(){
        if(this.aItems.get("teleporteur")==null){
            return false;
        }
        return true;
    }
}
