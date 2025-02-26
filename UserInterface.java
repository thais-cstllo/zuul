    import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.net.URL;

import java.awt.Color;
/**
 * Cette classe implémente une interface utilisateur graphique simple avec une zone de saisie de texte, une zone de sortie de texte et une image optionnelle. 
 * 
 * @author Thaïs Castillo
 * @version 16/03/2024
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aButtonLook;
    private JButton    aButtonNorth;
    private JButton    aButtonSouth;
    private JButton    aButtonWest;
    private JButton    aButtonEast;
    private JButton    aButtonUp;
    private JButton    aButtonDown;
    private JButton    aButtonQuit;
    private JButton    aButtonBack;
    private JPanel     aPanelButton;
    private int        aNbreTouch;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.aNbreTouch=0;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText String entré par l'utilisateur 
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText String affiche du texte
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName String nom de l'image.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the entry field.
     * @param pOnOff boolean active ou pas la barre d'entré du texte
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
            this.aButtonNorth.setEnabled(false);
            this.aButtonSouth.setEnabled(false);
            this.aButtonWest.setEnabled(false);
            this.aButtonEast.setEnabled(false);
            this.aButtonUp.setEnabled(false);
            this.aButtonDown.setEnabled(false);
            this.aButtonQuit.setEnabled(false);
            this.aButtonBack.setEnabled(false);
            aButtonLook.setEnabled(false);
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Playmobil présente : The Bedroom Exploration !" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        
        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() ); 
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        
        //Création de l'emplacement des bouttons
        this.aPanelButton = new JPanel();
        this.aPanelButton.setLayout(new GridLayout(3,4));
        //add()
        vPanel.add(this.aPanelButton, BorderLayout.EAST);
        
        //Boutons
        //look
        this.aButtonLook = new JButton("look");
        this.aButtonLook.setBackground(Color.WHITE);
        this.aButtonLook.addActionListener( this );
        this.aPanelButton.add(this.aButtonLook);
        
        //north
        this.aButtonNorth=new JButton("north");
        this.aButtonNorth.setBackground(Color.BLUE);
        this.aButtonNorth.addActionListener( this );
        this.aPanelButton.add(this.aButtonNorth);
        
        //south
        this.aButtonSouth=new JButton("south");
        this.aButtonSouth.setBackground(Color.YELLOW);
        this.aButtonSouth.addActionListener( this );
        this.aPanelButton.add(this.aButtonSouth);
        
        //east
        this.aButtonEast=new JButton("east");
        this.aButtonEast.setBackground(Color.ORANGE);
        this.aButtonEast.addActionListener( this );
        this.aPanelButton.add(this.aButtonEast);
        
        //west
        this.aButtonWest=new JButton("west");
        this.aButtonWest.setBackground(Color.PINK);
        this.aButtonWest.addActionListener( this );
        this.aPanelButton.add(this.aButtonWest);
        
        //up
        this.aButtonUp=new JButton("up");
        this.aButtonUp.setBackground(Color.MAGENTA);
        this.aButtonUp.addActionListener( this );
        this.aPanelButton.add(this.aButtonUp);
        
        //down
        this.aButtonDown=new JButton("down");
        this.aButtonDown.setBackground(Color.CYAN);
        this.aButtonDown.addActionListener( this );
        this.aPanelButton.add(this.aButtonDown);
        
        //back
        this.aButtonBack=new JButton("back");
        this.aButtonBack.setBackground(Color.GREEN);
        this.aButtonBack.addActionListener( this );
        this.aPanelButton.add(this.aButtonBack);
        
        //quit
        this.aButtonQuit=new JButton("quit");
        this.aButtonQuit.setBackground(Color.RED);
        this.aButtonQuit.addActionListener( this );
        this.aPanelButton.add(this.aButtonQuit);
        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // ActionListenner (clavier)
        this.aEntryField.addActionListener( this );
        
        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * @param pE ActionEvent
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        this.aNbreTouch++;
        if (pE.getSource() == this.aButtonNorth)
        {
            this.aEngine.interpretCommand("go north");
        }
        if (pE.getSource() == this.aButtonSouth)
        {
            this.aEngine.interpretCommand("go south");
        }
        if (pE.getSource() == this.aButtonEast)
        {
            this.aEngine.interpretCommand("go east");
        }
        if (pE.getSource() == this.aButtonWest)
        {
            this.aEngine.interpretCommand("go west");
        }
        if (pE.getSource() == this.aButtonLook)
        {
            this.aEngine.interpretCommand("look");
        }
        if (pE.getSource() == this.aButtonUp)
        {
            this.aEngine.interpretCommand("go up");
        }
        if (pE.getSource() == this.aButtonDown)
        {
            this.aEngine.interpretCommand("go down");
        }
        if (pE.getSource() == this.aButtonQuit)
        {
            this.aEngine.interpretCommand("quit");
        }
        if (pE.getSource() == this.aButtonBack)
        {
            this.aEngine.interpretCommand("back");
        }
        this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        if(this.aNbreTouch>50){
            this.aEngine.perdu();
            return;
        }
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
        
    } // processCommand()
} // UserInterface 
