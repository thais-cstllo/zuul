
import java.util.Random;
import java.util.List;

/**
 * La classe RoomRandomizer choisi va générer une pièce aléatoire 
 * où l'utilisateur sera téléporté après être sortie de la machine 
 * à voyager dans l'espace
 *
 * @author Thaïs Castillo
 * @version 12/05/2024
 */
public class RoomRandomizer
{
    private int aRandomNumber;
    
    /**
     * Constructeur naturel. 
     * Il va initialiser l'attribut aRandomNumber en générant un chiffre au hasard entre et 0 et 11 inclus.
     */
    public RoomRandomizer(){
        Random random=new Random();
        this.aRandomNumber=random.nextInt(11);
    }
    
    /**
     * Fonction servant à générer une Room au hasard.
     * @return Room correspondant à une pièce aléatoire
     */
    public Room findRandomRoom(){
        List<Room> vR=GameEngine.getTabRooms();
        if (GameEngine.getRoomTestMode()==null){
            return vR.get(aRandomNumber);
        }
        int vNbre=Integer.parseInt(GameEngine.getRoomTestMode());
        return vR.get(vNbre);
            
    }
}
