import javax.swing.*; // Import Swing components
import java.awt.*; // Import event handling classes

public class SwingTest extends JPanel {
    
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int width = getWidth(); // total width
        int height = getHeight();

        g.drawLine( 12, 12, width-12, 12 );
        g.drawLine( 12, height-12, width-12, height-12 );
    }
    
    public static void main(String[] args) {

        String start = JOptionPane.showInputDialog("Do you want to Start the water sort game?");


        JOptionPane.showMessageDialog( null, "Welcometo\nthe WaterSortGame" );
        
        SwingTest drawpanel = new SwingTest();

        JFrame application = new JFrame();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.add( drawpanel ); // add the panel to the frame
        application.setSize( 250, 250 ); // set the size of the frame
        application.setVisible( true );
        

    }//End main

}//End of SwingTest class
