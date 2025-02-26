                                                                                                import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
/**
 * La classe GameEngine correspond au moteur du jeu. Elle est celle qui
 * coordonne toutes les actions à effectuer dans une logique spécifique 
 * du jeu. Elle gère également les interractions avec l'utilisateur.
 *
 * @author Thaïs Castillo
 * @version 16/03/2024
 */
public class GameEngine
{
    private Parser        aParser;
    private UserInterface aGui;
    private Player        aPlayer;
    private Beamer        aBeamer;
    private static List<Room>   aTabRooms= new ArrayList<Room>();
    private static boolean       aTestMode;
    private static String          aRoomTestMode;
    
    /**
    * Constructeur par défaut
    */
    public GameEngine()
    {
        this.createRooms();
        //On initialise aParser
        this.aParser=new Parser();
        this.aTestMode=false;

    }
    
    /**
     * Définie l'interface utilisateur à utiliser par l'objet courant.
     * Affiche également un message de bienvenue dans l'inteface utilisateur.
     * @param pUserInterface UserInterface correspond à l'utilisateur
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
    /**
     * Procédure qui sera appellée au début du jeu pour afficher un message d'entrée.
     */
    private void printWelcome(){
        this.aGui.print( "\n" );
        this.aGui.println( "Playmobil présente : The Bedroom exploration !" );
        this.aGui.println( "Un monde où être un jouet n'est pas chose facile !" );
        this.aGui.println( "Tappe 'help' si tu veux de l'aide." );
        this.aGui.print( "\n" );
        this.printLocationInfo();        
        
    }
    
    /**
     * Procédure qui affiche les sorties possibles et la description quand on est dans une pièce. 
     */
    private void printLocationInfo(){
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription()+"\n");
        
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }
    
    /**
    * Procédure qui déclare et initialise les 
    * 12 lieux du jeu, les sorties , les items, et le player
    */
    private void createRooms()
    {
        //On donne la description de chaque pièce créées
        Room vMaison = new Room("dans la maison des Playmobils", "images/maison.jpg");
        Room vAutoroute = new Room("sur l'autoroute des voitures en bois", "images/autoroute.jpg");
        Room vBoutique = new Room("dans la boutique Playmobil", "images/boutique.jpg");
        Room vLit = new Room("sur le lit", "images/lit.gif");
        Room vAvion = new Room("dans un avion LEGO", "images/avion.gif");
        Room vFerme = new Room("dans la ferme Playmobil", "images/ferme.gif");
        Room vCoffre = new Room("dans la boite à jouets", "images/boite.gif");
        Room vCuisine = new Room("dans la cuisine Playmobil", "images/cuisine.gif");
        Room vTapisDeFoot = new Room("sur le tapis de foot", "images/tapi.gif");
        Room vBureau = new Room("sur le bureau", "images/bureau.gif");
        Room vTempleLego = new Room("dans le temple LEGO", "images/temple.gif");
        Room vSousLeLit = new Room("en-dessous du lit", "images/lit.gif");
        TransporterRoom vMachine =new TransporterRoom("dans la machine à voyager dans l'espace", "images/machine.jpg");
        
        //on initialise les sorties de chaque lieux
        vMaison.setExit("north", vAutoroute);
        vMaison.setExit("south",vBoutique); 
        vAutoroute.setExit("east", vAvion);
        vAutoroute.setExit("south",vMaison);
        vBoutique.setExit("north", vMaison); 
        vBoutique.setExit("south", vLit);
        //vLit.setExit("north", vBoutique);
        vLit.setExit("east", vBureau);
        //On ajoute la direction "down"
        vLit.setExit("down", vSousLeLit);
        vAvion.setExit("east", vFerme); 
        vAvion.setExit("south", vTempleLego);
        //vAvion.setExit("west", vAutoroute);
        vFerme.setExit("west", vAvion);
        vCoffre.setExit("west", vCuisine);
        vCuisine.setExit("north", vTempleLego);
        vCuisine.setExit("east", vCoffre);
        vCuisine.setExit("south", vBureau); 
        vTapisDeFoot.setExit("west", vSousLeLit); 
        vBureau.setExit("north", vCuisine);
        vBureau.setExit("west", vLit);
        vBureau.setExit("east",vMachine);
        vTempleLego.setExit("north", vAvion); 
        vTempleLego.setExit("south", vCuisine);
        //On ajoute la direction "up"
        vSousLeLit.setExit("up", vLit);
        vSousLeLit.setExit("east", vTapisDeFoot);
        vMachine.setExit("west", vBureau);
        
        
        //Ajout des Items 
        vMaison.ajouteItem("epee","Une belle épée te permettant d'affronter le danger"+"\n ",50);
        vBoutique.ajouteItem("coeur","Un coeur qui signifit une nouvelle vie"+"\n ", 0);
        vBoutique.ajouteItem("epee","Une autre belle épée te permettant d'affronter le danger"+"\n ",50); 
        vLit.ajouteItem("echelle","Une échelle te permettant d'escalader des peluches"+"\n ", 10);
        vFerme.ajouteItem("fourche","Une fourche appartenant au fermier"+"\n ", 15);
        vFerme.ajouteItem("pelle", "Une pelle appartenant au fermier"+"\n ", 15);
        vFerme.ajouteItem("seau","Un seau appartenant au fermier"+"\n ", 15);
        vFerme.ajouteItem("rateau", "Un râteau appartenant au fermier"+"\n ", 15);
        vCuisine.ajouteItem("clee","Une clée te permettant de rentrer chez toi"+"\n ", 30);
        this.aBeamer=new Beamer("teleporteur","Un téléporteur qui te permet de te ramener dans une pièce que tu peux charger"+"\n ",50);
        vTapisDeFoot.ajouteBeamer(this.aBeamer);
        
        //Lieux courant
        this.aPlayer=new Player("Joueur", vMaison);
        
        //Ajoute les pièces dans une hashmap
        this.aTabRooms.add(0,vMaison);
        this.aTabRooms.add(1,vAutoroute);
        this.aTabRooms.add(2,vBoutique);
        this.aTabRooms.add(3,vLit);
        this.aTabRooms.add(4,vAvion);
        this.aTabRooms.add(5,vFerme);
        this.aTabRooms.add(6,vCoffre);
        this.aTabRooms.add(7,vCuisine);
        this.aTabRooms.add(8,vTapisDeFoot);
        this.aTabRooms.add(9,vBureau);
        this.aTabRooms.add(10,vTempleLego);
        this.aTabRooms.add(11,vSousLeLit);
        
        
    }
    
    /**
     * Accesseur de la Liste de Rooms.
     * @return List
     */
    public static List<Room> getTabRooms(){
        return aTabRooms;
    }
    
    /**
     * Accesseur de la String aRoomTestMode
     * @return String
     */
    public static String getRoomTestMode(){
        return aRoomTestMode;
    }
    
    /**
     * Accesseur du booléen aTestMode
     * @return boolean
     */
    public static boolean getTestMode(){
        return aTestMode;
    }
    
    /**
     * Procédure qui interprète les commandes entrées par l'utilisateur et y répond 
     * @param pCommandLine String qui correspond à la commande 
     */
    public void interpretCommand( final String pCommandLine) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );
        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals("test") )
            this.test(vCommand);
        else if ( vCommandWord.equals("inventaire"))
            this.inventaire();
        else if (vCommandWord.equals("take") )
            this.take(vCommand);
        else if (vCommandWord.equals("alea") )
            if (!this.aTestMode){
                this.aGui.println( "Commande seulement utilisable dans un fichier test." );
                return;
            }
            else
                this.alea(vCommand);
        else if (vCommandWord.equals("charge"))
            this.charge(vCommand);
        else if (vCommandWord.equals("use"))
            this.use(vCommand);
        else if (vCommandWord.equals("drop") )
            this.drop(vCommand);
        else if ( vCommandWord.equals("back") )
            this.back(vCommand);
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand);
        else if(vCommandWord.equals("eat"))
            this.eat(vCommand);
        else if(vCommandWord.equals("look"))
            this.look();
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
    }
    
    /**
     * Procédure qui affiche tous les items que possède l'utilisateur et leur poids
     */
    private void inventaire(){
        this.aGui.println(this.aPlayer.getAllItemsString());
    }
    
    /**
     * Procédure qui affiche les sorties possibles et la description quand on est dans une pièce
     * si l'utilisateur le demande à nouveau 
     */
    private void look(){
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.println(this.aPlayer.getAllItemsString());
    }
    
    /**
     * Procédure qui permet à l'aventurier de se nourrir 
     * @param pC Command correspondant au magic cookies
     */
    private void eat(final Command pC){
        if ( ! pC.hasSecondWord() ) {
            this.aGui.println( "Eat what ?!" );
            return;
        }
        String vE= pC.getSecondWord();
        if(this.aPlayer.eat(vE)){
            this.aGui.println( "Tu viens de manger un coeur, ton inventaire a été doublé.");
            return;
        }
        this.aGui.println( "Tu n'as rien pu manger !");
    }
    
    /**
     * Procédure qui sera exécutée lorsque l'on tapera "help".
     */
    private void printHelp(){
        this.aGui.println( "Tu es perdus. Tu es seul.");
        this.aGui.println( "Tes commandes sont:" );
        this.aGui.println(this.aParser.getCommandString() );
    }
    
    /**
     * Procédure qui va permettre à l'utilisateur de se déplacer d'une pièce à l'autre.
     * @param pCommand Command venant de l'utilisateur.
     */
    public void goRoom( final Command pCommand ) 
    {
        if ( ! pCommand.hasSecondWord() ) {
            this.aGui.println( "Go where?" );
            return;
        }
        String vDirection = pCommand.getSecondWord();
        
        if(this.aPlayer.getCurrentRoom().getExit(vDirection)==null){
            this.aGui.println( "There is no door!" );
            return;
        }
        this.aPlayer.goRoom(vDirection);
        printLocationInfo(); 
    }
    
    /**
     * Procédure permettant à l'utilisateur de revenir dans les salles précédentes
     * @param pD Command venant de l'utilisateur
     */
    private void back(final Command pD){
        if(pD.hasSecondWord()){
            this.aGui.println("Tu ne peux pas retourner en arrière dans une direction précise !");
            return;
        }
        
        if(this.aPlayer.getLastRoom().empty()){
            this.aGui.println("Il n'existe pas de pièce précédente !");
            return;
        }
        
        if(this.aPlayer.back()){
            this.printLocationInfo();
            return;
        }
        this.aGui.println("Tu ne peux pas retourner en arrière");
        
    }
    
    private void charge(final Command pD){
        if(pD.hasSecondWord()){
            this.aGui.println("Tu ne peux pas charger une pièce en particulier !");
            return;
        }
        
        if(this.aBeamer.getUsed()){
            this.aGui.println("Tu ne peux pas utiliser ton téléporteur plusieurs fois !!");
            return;
        }
        
        if(!this.aPlayer.teleporteurPresent()){
            this.aGui.println("Ton téléporteur n'est pas dans ton inventaire");
            return;
        }
        
        if(this.aBeamer.getCharger()){
            this.aGui.println("Ton téléporteur est déjà chargé !!");
            return;
        }
        
        if(this.aPlayer.teleporteurPresent()){
            this.aBeamer.charge(this.aPlayer.getCurrentRoom());
            this.aGui.println("Ton téléporteur a pu être recharger");
            return;
        }
        this.aGui.println("Tu n'as pas pu charger ton téléporteur");
    }
    
    private void use(final Command pD){
        if(pD.hasSecondWord()){
            this.aGui.println("Tu ne peux pas retourner dans une pièce en particulier !");
            return;
        }
        
        if(!this.aPlayer.teleporteurPresent()){
            this.aGui.println("Ton téléporteur n'est pas dans ton inventaire");
            return;
        }
        
        if(this.aBeamer.getUsed()){
            this.aGui.println("Tu ne peux pas utiliser ton téléporteur plusieurs fois !!");
            return;
        }
        
        if(!this.aBeamer.getCharger()){
            this.aGui.println("Ton téléporteur n'as pas été rechargé !!");
            return;
        }
        
        if(this.aBeamer.getCharger()){
            this.aPlayer.changeRoom(this.aBeamer.getChargeRoom());
            this.aBeamer.use();
            this.aGui.println("Tu t'es téléporté vers "+this.aBeamer.getChargeRoom());
            this.printLocationInfo(); 
            return;
        }
        this.aGui.println("Tu n'as pas pu utiliser ton téléporteur !!");
        
    }
    
    /**
     * Procédure qui permet à l'utilisateur de prendre un Item
     * @param pD Command désignant l'objet
     */
    private void take(final Command pD){
        if(pD.hasSecondWord()==false){
            this.aGui.println("take what ?");
            return;
        }
        String vItem = pD.getSecondWord();
        if(this.aPlayer.take(vItem)){
            this.aGui.println("Tu possède maintenant "+vItem);
            return;
        }
        this.aGui.println("Tu n'as rien pu prendre car tu as peut-être déjà trop d'Item !!");
    }
    
    /**
     * Procédure qui permet à l'utilisateur de jeter un Item
     * @param pD Command désignant l'objet
     */

    private void drop(final Command pD){
        if(pD.hasSecondWord()==false){
            this.aGui.println("drop what ?");
            return;
        }
        String vItem = pD.getSecondWord();
        if(this.aPlayer.drop(vItem)){
            this.aGui.println("Tu ne possède plus "+vItem);
            return;
        }
        this.aGui.println("Tu n'as rien pu jeter !!");
    }
    
    /**
     * Procédure permettant de savoir à l'avance où sera téléporter l'utilisateur ou pas
     *  @param pD Command
     */
    private void alea(final Command pD){
        if(!pD.hasSecondWord()){
             this.aRoomTestMode=null;
            return;
        }
        this.aRoomTestMode=pD.getSecondWord();
    }
    
    /**
     * Procédure permettant de tester le jeu
     * @param pD Command 
     */
    public void test(final Command pD){
        this.aTestMode=true;
        if(!pD.hasSecondWord()){
            this.aGui.println("Test what ?");
            return;
        }
        String vFichier=pD.getSecondWord();
        this.lecture(vFichier);
    }
    
    /**
     * Procédure qui lit un fichier
     * @param pNomFichier String du nom de fichier
     */
    private void lecture( final String pNomFichier )
    {
        Scanner vSc;
        try { // pour "essayer" les instructions suivantes :
            String vNomFichier=pNomFichier+".txt";
            vSc = new Scanner( new File( vNomFichier ) );
            while ( vSc.hasNextLine() ) {
                String vLigne = vSc.nextLine();
                interpretCommand(vLigne);
                // traitement de la ligne lue
            } // while
            vSc.close(); //fermeture du fichier
        } // try
        catch ( final FileNotFoundException pFNFE ) {
            this.aGui.println("Fichier non trouvé");
        } // catch
    } // lecture
    
    public void perdu(){
        this.aGui.println( "Tout ton temps s'est écoulé. Tu as perdu, ton playmobil n'as pas pu retourner dans sa boîte." );
        this.aGui.enable( false );
    }
    
    /**
     * Procédure permettant de mettre fin au jeu
     */
    private void endGame()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
}
