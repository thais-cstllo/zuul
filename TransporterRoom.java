

/**
 * La classe TransporterRoom est une sous classe de la classe Room et va 
 * servir notamment à redéfinir la méthode getExit().
 *
 * @author Thaïs Castillo
 * @version 12/05/2024
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRandom;
    
    /**
     * Constructeur naturel 
     * @param pD String pour initialiser aDescription dans la classe Room.
     * @param pImage String pour ajouter une image dans la classe Room.
     */
    public TransporterRoom(final String pD, final String pImage){
        super(pD,pImage);
        this.aRandom= new RoomRandomizer();
    }
    
    /**
    * Fonction qui retourne une pièce aléatoire si on se déplace dans n'importe quelle direction pD.
    * @param pD String correspondant à la direction 
    * @return Room qui existe dans cette direction (peut valoir null si elle n'existe pas).
    */
    @Override
    public Room getExit(String pD){
        return this.aRandom.findRandomRoom();
    }
}
