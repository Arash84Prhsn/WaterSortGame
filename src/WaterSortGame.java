//Arash Poorhasani 4021299123 First project: Watersort game
import java.util.Random;
import java.util.Scanner;

public class WaterSortGame
{
    private final String[] COLORS;
    private int bottleCapacity;
    private int numberOfBottles;
    private CircularSinglyLinkedList<Stack<String>> bottles = new CircularSinglyLinkedList<>();
    private Stack<String> selectedBottle = null;
    
    
    //Class constructor.
    public WaterSortGame (String[] COLORS, int bottleCapacity)
    {
        this.COLORS = COLORS;
        this.numberOfBottles = bottleCapacity+1;
        this.bottleCapacity = bottleCapacity;  
        
        //Randomly fill our bottles and add our bottles to the bottles linked list.
        for (int i = 0; i < numberOfBottles-1; i++)
        {
            Stack<String> bottle = new Stack<>();
            Random random = new Random();
            for (int j = 0; j < bottleCapacity; j++)
                bottle.push(COLORS[random.nextInt(COLORS.length)]);

            bottles.addLast(bottle);
        }
        
        //put an empty bottle in our linked list.
        Stack<String> bottle = new Stack<>();
        bottles.addLast(bottle);    
    }
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
    //A display method that show us the current state of the bottles
    public void display()
    {
        //CircularSinglyLinkedList<Stack<String>> tempList = new CircularSinglyLinkedList<>();
        //tempList.copy(this.bottles);
        Stack<String> currentBottle = bottles.getFirst();
        
        //First fill the empty bottles with "empty" strings.
        for (int i = 0; i < numberOfBottles; i++)
        {
            while (currentBottle.getSize() != bottleCapacity) {
                currentBottle.push("empty");
            }

            currentBottle = bottles.getNextItem(currentBottle);
        }
        
        currentBottle = bottles.getFirst();
        String[][] tempColors = new String[bottleCapacity][numberOfBottles];//Stores colors as they pop()
        
        //Start the printing by going through the bottles and popping
        for (int i = 0; i < bottleCapacity; i++)
        {
            for (int j = 0; j < numberOfBottles; j++) {
                
                String currentColor = bottles.getItemAt(j).pop();
                tempColors [bottleCapacity-1-i][j] = currentColor;//Store the popped color
                
                System.out.printf("%-12s","| "+currentColor);//pad out to right so we have a minimum length of 12
                currentBottle = bottles.getNextItem(currentBottle);
            }
            System.out.println();

        }
        //Restore our stacks to their state before the printing.
        for (int i = 0; i < bottleCapacity; i++)
            for (int j = 0; j < numberOfBottles; j++)
                if (!tempColors[i][j].equals("empty"))
                    bottles.getItemAt(j).push(tempColors[i][j]);
    
        System.out.println("------------------------------------------------------------------------------");
    }//End of display method
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
    /* isSelectable method tells us if the given bottle is selectable.
    /* if the bottle is empty or filled with just one color then it cannot be selected.
     */
    private static boolean isSelectable(Stack<String> stack)
    {   
        //If the stack is empty return false
        if (stack.isEmpty()) return false;
        
        //Make a new String[] storing the colors
        String[] tempColors = new String[stack.getSize()];
        for (int i = 0; i < tempColors.length; i++)
            tempColors[i] = stack.pop();

        String topColor = tempColors[0];
        boolean isSelectable = false;

        /*If any color mismatches the top color of the stack then not all the colors are the same 
         *and we must return false*/
        for ( String i : tempColors)
            if (!topColor.equals(i))
                isSelectable = true;

        //Restore our stack to it's initial state before getting popped.
        for (int i = tempColors.length-1; i >= 0; i--)
            stack.push(tempColors[i]);

        //Return isSelectable boolean value
        return isSelectable;
    }

    //A method that selects a bottle for us if it is selectable
    public boolean select(int bottleNumber)
    {
        if (isSelectable(bottles.getItemAt(bottleNumber-1)))
            selectedBottle = bottles.getItemAt(bottleNumber-1);

        return isSelectable(bottles.getItemAt(bottleNumber-1));
        
    }

    //A method for deselecting the current selected bottle.
    public void deselect()
    {
        if (selectedBottle != null)
            selectedBottle = null;

    }

    //A method that selects the next bottle in the bottles linked list.
    public void selectNext()
    {
        if (selectedBottle!=null)
        {
            selectedBottle = bottles.getNextItem(selectedBottle);
        }

    }

    //A method that selects the previous bottle in the bottles linked list.
    public void selectPrevious()
    {
        if (selectedBottle!=null)
        {
            selectedBottle = bottles.getPreviousItem(selectedBottle);
        }

    }

    /* the pour method pours the top of the current selected bottle onto the given bottle until we reach another color in the selected bottle
     * also if the color at the top of the selected bottle does not match the color at the top of the destination bottle
     * or the destination bottle is full, pouring does not occur.
     */
    public boolean pour(int bottleNumber)
    {
        //If no bottle is selected return fase and exit method.
        if (selectedBottle == null) return false;

        boolean poured = false;
        //destinationBottle is the bottle that we are pouring into
        Stack<String> destinationBottle = bottles.getItemAt(bottleNumber-1);
        //while the destinationBottle is not empty and the top colors match pour. also pour when the destinationBottle is empty
        while (destinationBottle.isEmpty() ||
              (!selectedBottle.isEmpty() && selectedBottle.top() == destinationBottle.top() && destinationBottle.getSize() < bottleCapacity)) {
            poured = true;
            destinationBottle.push(selectedBottle.pop());
        }//end while

        return poured;
        
    }

    //Swap method to swap the selected bottle with the given bottle.
    public void swap(int bottleNumber)
    {
        if (selectedBottle == null) return;//if no bottle is selected do nothing.

        


    }

    //A recursive method that uses everything we have defined so far and and allows us to play the game.
    public void playGame()
    {

    }
}


