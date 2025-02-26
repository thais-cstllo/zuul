import java.util.Stack;
import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe permet d'ajouter plusieurs joueurs au jeu.
 *
 * @author Thaïs Castillo 
 * @version 29/03/2024
 */
public class Player
{
    private String        aNamePlayer;
    private Room          aCurrentRoom;
    private Stack<Room>   aStack;
    private Room          aPreviousRoom;
    private ItemList      aItems;
    private Integer       aWeightPlayer;
    private Integer       aWeightTotal;
    
    /**
     * Constructeur naturel
     * @param pN initialise aNamePlayer
     * @param pCurrentRoom initialise aCurrentRoom
     */
    public Player(final String pN, final Room pCurrentRoom){
        this.aNamePlayer=pN;
        this.aStack=new Stack<>();
        this.aCurrentRoom=pCurrentRoom;
        this.aItems=new ItemList();
        this.aWeightPlayer=100;
        this.aWeightTotal=0;
    }
    
    /**
     * @return String aNamePlayer
     */
    public String getNamePlayer(){ return this.aNamePlayer;}
    
    /**
     * Fonction permettant d'afficher les éléments de la hashmap
     * @return String 
     */
    public String getAllItemsString(){
    return this.aItems.getAllItemsString();
    }
    
    /**
     * Fonction qui retourne la pièce courrante
     * @return Room aCurrentRoom
     */
    public Room getCurrentRoom(){ return this.aCurrentRoom;}
    
    /**
     * Fonction qui retourne la dernière pièce
     * @return Une pile contenant des objets de type Room.
     */
    public Stack<Room> getLastRoom(){ return this.aStack;}
    
    /**
     * Procédure qui permet de changer de pièce
     * @param pD String indiquant la direction 
     */
    public void goRoom(final String pD){
        Room vNextRoom = this.aCurrentRoom.getExit( pD );
        this.aStack.push(this.aCurrentRoom);
        this.aCurrentRoom=vNextRoom;
    }
    
    /**
     * Fonction permettant de revenir sur ses pas
     * @return boolean
     */
    public boolean back(){
        this.aPreviousRoom=this.aStack.peek();
        if(!this.aCurrentRoom.isExit(this.aPreviousRoom)){
            this.aStack.clear();
            return false;
        }
        this.aCurrentRoom=this.aPreviousRoom;
        this.aStack.pop();
        return true;
    }
    
    /**
     * Fonction permettant de savoir si le téléportateur est bien présent dans l'inventaire de l'utilisateur
     * @return boolean 
     */
    public boolean teleporteurPresent(){//on veut savoir si le teleporteur est bien dans l'inventaire
        if(this.aItems.teleporteur()){
                return true;
        }
        return false;
    }
    
    /**
     * Procédure permettant de changer de pièce
     * @param pR Room nouvelle pièce qui sera la pièce courrante
     */
    public void changeRoom(final Room pR){
        this.aCurrentRoom=pR;
    }
    
    /**
     * Procédure qui permet à l'utilisateur de prendre un Item
     * @param pI String désignant l'objet
     * @return boolean
     */
    public boolean take(final String pI){
        if(pI==null){
            return false;
        }
        Item vItem=this.aCurrentRoom.getItem(pI);
        int vW=vItem.getItemWeight();
        this.aWeightTotal+=vW;
        if(this.aWeightTotal>this.aWeightPlayer){
            this.aWeightTotal=this.aWeightTotal-vW;
            return false;
        }
        this.aItems.addItem(vItem);
        this.aCurrentRoom.removeItem(vItem);
        return true;
    }
    
    /**
     * Procédure qui permet à l'utilisateur de jeter un Item
     * @param pI String désignant l'objet
     * @return boolean
     */

    public boolean drop(final String pI){
        Item vItem=this.aItems.getItem(pI);
        if(vItem==null){
            return false;
        }
        int vW=vItem.getItemWeight();
        this.aWeightTotal=this.aWeightTotal-vW;
        
        this.aItems.removeItem(vItem);
        this.aCurrentRoom.addItem(vItem);
        return true;
    }
    
    /**
     * Fonction booléenne qui permet de double l'espace de stockage de l'utilisateur quand il tappe "eat coeur"
     * @param pD String qui doit être coeur pour retourner true et sinon false
     * @return boolean
     */
    public boolean eat(final String pD){
        if(pD.equals("coeur")){
            if(this.aItems.coeur()){
                this.aWeightPlayer=this.aWeightPlayer*2;
                Item vItem=this.aItems.getItem(pD);
                this.aItems.removeItem(vItem);
                return true;
            }
            return false;
        }
        return false;
    }
}
