
/**
 * La classe Beamer permet à l'utilisateur de se téléporter.
 *
 * @author Thaïs Castillo
 * @version 19/04/2024
 */
public class Beamer extends Item
{
    private Room    aChargeRoom;
    private boolean aCharger;
    private boolean aUsed;
    
    /**
     * Constructeur naturel
     * @param pN String nom du teleportateur
     * @param pD String description
     * @param pW int poids du téléportateur
     */
    public Beamer(final String pN,final String pD, final int pW){
        super(pN,pD,pW);
        this.aCharger=false;
        this.aUsed=false;
    }
    
    /**
     * Accesseur
     * @return boolean aCharger
     */
    public boolean getCharger(){ return this.aCharger;}
    
    /**
     * Accesseur
     * @return boolean aUsed
     */
    public boolean getUsed(){ return this.aUsed;}
    
    /**
     * Accesseur
     * @return boolean aChargeRoom
     */
    public Room getChargeRoom(){ return this.aChargeRoom;}
    
    /**
     * Procédure permettant de charger le téléportateur
     * @param pR Room
     */
    public void charge(final Room pR){
        this.aChargeRoom=pR;
        this.aCharger=true;      
    }
    
    /**
     * Procédure permettant d'utiliser le téléportateur
     */
    public void use(){
        this.aUsed=true;
    }
    
}
