import javax.swing.*; // Import Swing components
import java.awt.event.*; // Import event handling classes

public class SwingTest {
    public static void main(String[] args) {
        // Step 1: Create a JFrame (main window)
        JFrame frame = new JFrame("Simple Swing Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the app when the window is closed
        frame.setSize(300, 200); // Set window size (width, height)

        // Step 2: Create a JLabel (text display)
        JLabel label = new JLabel("Click the button to change this text!");
        
        // Step 3: Create a JButton (button)
        JButton button = new JButton("Click Me!");
        
        // Step 4: Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Text changed after button click!"); // Change the text when clicked
            }
        });
        
        // Step 5: Set Layout (using default layout)
        frame.setLayout(new java.awt.FlowLayout());
        
        // Step 6: Add components to the frame
        frame.add(label);  // Add JLabel to JFrame
        frame.add(button); // Add JButton to JFrame
        
        // Step 7: Make the frame visible
        frame.setVisible(true); // Show the window
    
    }//End main

}//End of SwingTest class
