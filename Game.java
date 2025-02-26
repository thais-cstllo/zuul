/**
 *  Cette classe intialise le moteur de jeu GameEngine et lance le jeu
 * @author  Thaïs Castillo
 * @version 16/03/2024
 */

public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map. Create the inerface and link to it.
     */
    public Game() 
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
}
