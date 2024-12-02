//Arash Poorhasani 4021299123 project1 of Data-structures-and-algorithms_Soheila-Ashkezari: Watersort game.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WaterSortGame
{   
    private String[] COLORS;
    private int bottleCapacity;
    private int numberOfBottles;
    private CircularSinglyLinkedList<Stack<String>> bottles = new CircularSinglyLinkedList<>();
    private Stack<String> selectedBottle = null;
    /* The actions array is used to store the last 10 called functions, the respective index of the selected bottle,
     * the argument given to the function at the time of it's call and the number of poured colors at the time of action (this is only for the pour method) .*/
    @SuppressWarnings("unchecked")
    private Tuple4<String, Integer, Integer, Integer>[] actions = new Tuple4[10]; 

//---------------------------------------------------------------------------------
    //Colors ANSI code: (with thanks to chatgpt for writing them all out for me.) 
    private String resetColor = "\u001B[0m"; // Reset to default color
    
    private String red = "\u001B[31m";
    private String green = "\u001B[32m";
    private String yellow = "\u001B[33m";
    private String blue = "\u001B[34m";
    private String purple = "\u001B[35m";
    private String cyan = "\u001B[36m";
    //These don't work for some reason so for now the've been commented.
    // private String brown = "\\033[38;5;94m";
    // private String orange = "\\033[38;5;214m";
    // private String pink = "\\033[38;5;213m";

    // Background colors (use with fg)
    // private String redBg = "\u001B[41m";
    // private String greenBg = "\u001B[42m";
    // private String yellowBg = "\u001B[43m";
    // private String blueBg = "\u001B[44m";
    // private String magentaBg = "\u001B[45m";
    // private String cyanBg = "\u001B[46m";
    // private String whiteBg = "\u001B[47m";

    // Bright colors
    // private String brightRed = "\u001B[1;31m";
    // private String brightGreen = "\u001B[1;32m";
    // private String brightYellow = "\u001B[1;33m";
    // private String brightBlue = "\u001B[1;34m";
    // private String brightMagenta = "\u001B[1;35m";
    // private String brightCyan = "\u001B[1;36m";
    // private String brightWhite = "\u001B[1;37m";
    
    //End of ANSI codes.
//----------------------------------------------------------------------------------
    
    //Class constructor.
    public WaterSortGame (String[] COLORS, int bottleCapacity)
    {   
        this.COLORS = COLORS;
        this.numberOfBottles = COLORS.length+1;
        this.bottleCapacity = bottleCapacity;  
        
        // Randomly fill our bottles and add our bottles to the bottles linked list. There must not be more instances of color than our bottleCapacity.
        /* We do this by creating an ArrayList that has bottleCapacity instances of the possible indexes of
         * COLORS added to it we then randomize the order of these indexes using the shuffle() method 
         * and at last we go through the integers in colorCount and push COLORS[currenInteger] to our bottle.*/
        ArrayList<Integer> colorCount = new ArrayList<>();
        for (int i = 0; i < this.COLORS.length; i++)
            for (int j = 0; j < bottleCapacity; j++)
                colorCount.add(i);

        Collections.shuffle(colorCount);

        for (int i = 0; i < numberOfBottles-1; i++)
        {
            Stack<String> bottle = new Stack<>();
            for (int j = i*bottleCapacity; j < (i+1) * bottleCapacity; j++)
                bottle.push(this.COLORS[colorCount.get(j)]);

            bottles.addLast(bottle);
        }
        
        //put an empty bottle in our linked list.
        Stack<String> bottle = new Stack<>();
        bottles.addLast(bottle);    

        //Initialize our actions array
        for (int i = 0; i < 10; i++)
            actions[i] = new Tuple4<String,Integer,Integer,Integer>("noFunc", -1, -1, -1);
    }
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
    //A display method that show us the current state of the bottles
    public void display()
    {
      
        //Print the number of each bottle on top of it and make the number green if bottle is selected.
        for (int i = 1; i<=numberOfBottles; i++) {
            if (bottles.getIndexOf(selectedBottle) == i-1)
                System.out.print(green + i+ " ".repeat(11)+ resetColor);
            else
                System.out.print(i + " ".repeat(11));
        }
        System.out.println();
        

        Stack<String> currentBottle = bottles.getFirst();
        
        //First fill the empty slots of the bottles with "empty" strings.
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
                tempColors[bottleCapacity-1-i][j] = currentColor;//Store the popped color
                //If the color matches any of the cases below print in that color
                switch (currentColor) {
                    
                    case "green":
                        System.out.printf("%-21s","| " + green + currentColor + resetColor);//pad out to right so we have a minimum length of 12
                        break;
                    case "Green":
                        System.out.printf("%-21s","| " + green + currentColor + resetColor);
                        break;    
                    case "GREEN":
                        System.out.printf("%-21s","| " + green + currentColor + resetColor);
                        break;    
                
                    case "blue":
                        System.out.printf("%-21s","| " + blue + currentColor + resetColor);
                        break;
                    case "Blue":
                        System.out.printf("%-21s","| " + blue + currentColor + resetColor);
                        break;
                    case "BLUE":
                        System.out.printf("%-21s","| " + blue + currentColor + resetColor);
                        break;

                    case "yellow":
                        System.out.printf("%-21s","| " + yellow + currentColor + resetColor);
                        break;
                    case "Yellow":
                        System.out.printf("%-21s","| " + yellow + currentColor + resetColor);
                        break;
                    case "YELLOW":
                        System.out.printf("%-21s","| " + yellow + currentColor + resetColor);
                        break;
                        
                    case "red":
                        System.out.printf("%-21s","| " + red + currentColor + resetColor);
                        break;
                    case "Red":
                        System.out.printf("%-21s","| " + red + currentColor + resetColor);
                        break;
                    case "RED":
                        System.out.printf("%-21s","| " + red + currentColor + resetColor);
                        break;

                    case "purple":
                        System.out.printf("%-21s","| " + purple + currentColor + resetColor);
                        break;
                    case "Purple":
                        System.out.printf("%-21s","| " + purple + currentColor + resetColor);
                        break;
                    case "PURPLE":
                        System.out.printf("%-21s","| " + purple + currentColor + resetColor);
                        break;

                    case "cyan":
                        System.out.printf("%-21s","| " + cyan + currentColor + resetColor);
                        break;
                    case "Cyan":
                        System.out.printf("%-21s","| " + cyan + currentColor + resetColor);
                        break;
                    case "CYAN":
                        System.out.printf("%-21s","| " + cyan + currentColor + resetColor);
                        break;
                        
                    //if the color was not found then just print it normally.
                    default:
                        System.out.printf("%-12s","| "+currentColor);
                        break;
                }
                
                //go to the next bottle.
                currentBottle = bottles.getNextItem(currentBottle);
            }
            
            System.out.println();
        }//End of printing the colors.

        //If a bottle is selected print a "^^^^^^^" on below it.
        if (this.selectedBottle != null) {
            int d = bottles.getIndexOf(selectedBottle) * 12;
            System.out.print(" ".repeat(d));
            System.out.println(green + "^^^^^^^" + resetColor);
        }//End if

        //Restore our bottles to their state before the printing.
        for (int i = 0; i < bottleCapacity; i++)
            for (int j = 0; j < numberOfBottles; j++)
                if (!tempColors[i][j].equals("empty"))
                    bottles.getItemAt(j).push(tempColors[i][j]);
    
        System.out.println(blue+"------------------------------------------------------------------------------"+resetColor);
    }//End of display method
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
    
    /* isSelectable method tells us if the given bottle is selectable.
     * if the bottle is empty or filled with just one color then it cannot be selected. */
    private boolean isSelectable(Stack<String> stack)
    {   
        //If the stack is empty return false
        if (stack.isEmpty()) return false;
        //if the bottle isn't empty nor full, it is selectable so return true
        if (stack.getSize() < this.bottleCapacity && !stack.isEmpty()) return true;
        
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
        {
            this.addToActions("select", bottles.getIndexOf(selectedBottle), bottleNumber, -1);
            selectedBottle = bottles.getItemAt(bottleNumber-1);
        }
        
        else {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|             bottle cannot be selected              |");
            System.out.println("------------------------------------------------------"+resetColor );
        }

        return isSelectable(bottles.getItemAt(bottleNumber-1));
        
    }

    //A method for deselecting the current selected bottle.
    public void deselect()
    {
        if (selectedBottle != null)
        {   
            this.addToActions("deselect", bottles.getIndexOf(selectedBottle), -1, -1);
            selectedBottle = null;
        }
    }

    //A method that selects the next bottle in the bottles linked list (if it is selectable).
    public void selectNext()
    {
         if (selectedBottle != null && isSelectable(bottles.getNextItem(selectedBottle))) {
            this.addToActions("selectNext", bottles.getIndexOf(selectedBottle), -1, -1);
            selectedBottle = bottles.getNextItem(selectedBottle);
        }

        else if (selectedBottle != null) {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|             bottle cannot be selected              |");
            System.out.println("------------------------------------------------------"+resetColor );
        }

        else {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|                select a bottle first               |");
            System.out.println("------------------------------------------------------"+resetColor);
        }

    }

    //A method that selects the previous bottle in the bottles linked list (if it is selectable).
    public void selectPrevious()
    {
        
        if (selectedBottle != null && isSelectable(bottles.getPreviousItem(selectedBottle))) {
            this.addToActions("selectPrevious", bottles.getIndexOf(selectedBottle), -1, -1);
            selectedBottle = bottles.getPreviousItem(selectedBottle);
            }
    
        else if (selectedBottle != null) {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|             bottle cannot be selected              |");
            System.out.println("------------------------------------------------------"+resetColor );
        }

        else {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|                select a bottle first               |");
            System.out.println("------------------------------------------------------"+resetColor);
        }     

    }

    /* the pour method pours the top of the current selected bottle onto the given bottle until we reach another color in the selected bottle
     * also if the color at the top of the selected bottle does not match the color at the top of the destination bottle
     * or the destination bottle is full, pouring does not occur.*/
    public boolean pour(int bottleNumber)
    {
        //If no bottle is selected return fase and exit method.
        if (selectedBottle == null) return false;
        //In the case that the player tries to pour a bottle onto itself.
        if(this.bottles.getIndexOf(selectedBottle) + 1 == bottleNumber) {
            System.out.println(red+"------------------------<!!!>-------------------------");
            System.out.println("|            cannot pour onto the same bottle        |");
            System.out.println("------------------------------------------------------"+resetColor );
            return false;
        }

        boolean poured = false;
        //destinationBottle is the bottle that we are pouring into
        Stack<String> destinationBottle = bottles.getItemAt(bottleNumber-1);
        //while the destinationBottle is not empty and the top colors match pour. also pour when the destinationBottle is empty
        int counter = 0;//Count how many colors are poured
        while (destinationBottle.isEmpty() ||
              (!selectedBottle.isEmpty() && selectedBottle.top() == destinationBottle.top() && destinationBottle.getSize() < bottleCapacity)) {
            poured = true;
            destinationBottle.push(selectedBottle.pop());
            counter++;
        }//end while

        //if pouring occured, then add the 4-tuple ("pour", indexOfSelectedBottle, argumentGivenToPour, AmountOfColorsPoured) to actions.
        if (poured) {
            this.addToActions("pour", bottles.getIndexOf(selectedBottle), bottleNumber, counter);
        }
        //if not, give an error message.
        else {
            System.out.println(red + "------------------------<!!!>-------------------------");
            System.out.println("|            cannot pour onto that bottle            |");
            System.out.println("------------------------------------------------------" + resetColor);
        }
        return poured;
        
    }

    //Swap method to swap the selected bottle with the given bottle.
    public void swap(int bottleNumber)
    {
        if (selectedBottle == null) return;//if none of the bottles are selected, do nothing.

        Stack<String> swapBottle = bottles.getItemAt(bottleNumber-1);
        String[] selectedBottleColors = new String[bottleCapacity];
        String[] swapBottleColors = new String[bottleCapacity];
        int selectedBottleSize = selectedBottle.getSize();
        int swapBottleSize = swapBottle.getSize();

        for (int i = selectedBottleSize - 1; !selectedBottle.isEmpty(); i--)
            selectedBottleColors[i] = selectedBottle.pop();

        for (int i = swapBottleSize - 1; !swapBottle.isEmpty(); i--)
            swapBottleColors[i] = swapBottle.pop();        

        for (int i = 0; i < swapBottleSize; i++)
            selectedBottle.push(swapBottleColors[i]);

        for (int i = 0; i < selectedBottleSize; i++)
            swapBottle.push(selectedBottleColors[i]);

        this.addToActions("swap", bottles.getIndexOf(selectedBottle), bottleNumber, -1);
        
        selectedBottle = swapBottle;
    }

    //This method is used to help the undo method by editing the actions[] Tuple4 array.
    private void addToActions(String action, int selectedIndex, int argument, int numberOfPouredColors)
    {
        //First shift everything to the right
        for (int i = 9; i > 0; i--){
            actions[i].setFirst(actions[i-1].getFirst());
            actions[i].setSecond(actions[i-1].getSecond());
            actions[i].setThird(actions[i-1].getThird());
            actions[i].setFourth(actions[i-1].getFourth());
        }//End for
        
        //Then add the current 4-tuple to actions[0].
        actions[0].setFirst(action);
        actions[0].setSecond(selectedIndex);
        actions[0].setThird(argument);
        actions[0].setFourth(numberOfPouredColors);
    }

    //Another method that's for helping undo() this one shifts the actions array to the left.
    private void shiftLeftActions()
    {
        for (int i = 0; i < 9; i++) {
            actions[i].setFirst(actions[i+1].getFirst());
            actions[i].setSecond(actions[i+1].getSecond());
            actions[i].setThird(actions[i+1].getThird());
            actions[i].setFourth(actions[i+1].getFourth());
        }
        actions[9].setFirst("noFunc");
        actions[9].setSecond(-1);
        actions[9].setSecond(-1);
        actions[9].setFourth(-1);
    }
    //The undo method can undo our actions up to the latest 10.
    public void undo()
    {
        String latestAction = actions[0].getFirst();
        int indexOfSelectedAtTimeOfAction = actions[0].getSecond();
        int argumentGivenToAction = actions[0].getThird();

        switch (latestAction) {
            case "select":{
                if (indexOfSelectedAtTimeOfAction == -1) {//if nothing was selected at the time of select simply deselect.
                    selectedBottle = null;
                }

                else {//If a bottle was selected at the time select was called reselect that bottle.
                    selectedBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                }

                this.shiftLeftActions();
                break;
            }
            case "deselect":{//Since deselect is only added to actions if a bottle had already been selected, simply reselect that bottle.
                selectedBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                this.shiftLeftActions();
                break;
            }
            case "selectNext":{//Since selectNext is only added to actions if a bottle had already been selected, simply reselect that bottle.
                selectedBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                this.shiftLeftActions();
                break;
            }
            case "selectPrevious":{//Since selectPrevious is only added to actions if a bottle had already been selected, simply reselect that bottle.
                selectedBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                this.shiftLeftActions();
                break;
            }
            case "swap":{//since the swapBottle becomes the selected bottle, we need to simply swap swapBottle with the original selected bottle at the time of swap.
                
                Stack<String> swapBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                
                String[] selectedBottleColors = new String[bottleCapacity];
                String[] swapBottleColors = new String[bottleCapacity];
                
                int selectedBottleSize = selectedBottle.getSize();
                int swapBottleSize = swapBottle.getSize();
    
                for (int i = selectedBottleSize - 1; !selectedBottle.isEmpty(); i--)
                    selectedBottleColors[i] = selectedBottle.pop();
    
                for (int i = swapBottleSize - 1; !swapBottle.isEmpty(); i--)
                    swapBottleColors[i] = swapBottle.pop();        
    
                for (int i = 0; i < swapBottleSize; i++)
                    selectedBottle.push(swapBottleColors[i]);
    
                for (int i = 0; i < selectedBottleSize; i++)
                    swapBottle.push(selectedBottleColors[i]);

                selectedBottle = swapBottle;

                this.shiftLeftActions();
                break;
            }   
            
            /* In the case that the lastest action is pour, we use the pourUndo() method to undo the pouring.*/
            case "pour":{
                selectedBottle = bottles.getItemAt(argumentGivenToAction - 1);
                this.pourUndo(indexOfSelectedAtTimeOfAction+1,actions[0].getFourth());
                selectedBottle = bottles.getItemAt(indexOfSelectedAtTimeOfAction);
                this.shiftLeftActions();
                break;
            }
            
            default:{
                System.out.println(red + "------------------------<!!!>-------------------------");
                System.out.println("|      cannot undo more than the last 10 actions.    |");
                System.out.println("------------------------------------------------------" + resetColor);
                break;
        
            }
        }
    }

    //The hasWon method checks if the game is over and the player has won or not.
    public boolean hasWon()
    {   
        boolean hasWon = true;

        for (int i = 0; i < numberOfBottles; i++)
        {
            Stack<String> currentBottle = bottles.getItemAt(i);
            
            //There will always be one empty bottle when the game is won so we account for that with this if.
            if (currentBottle.isEmpty()) continue;
            
            //Now that we know our bottle is not empty then it must definetly be full so if it's not full return false.
            if (currentBottle.getSize() != bottleCapacity) return false;
            
            //Now that we know our bottle is full go through all it's colors and check that they are the same.
            String currentColor = currentBottle.top();
            int bottleSize = currentBottle.getSize();
            String[] colors = new String[bottleSize];

            for (int j = 0; !currentBottle.isEmpty(); j++)//pop the bottle into an array so we dont lose it.
                colors[j] = currentBottle.pop();
            
            for (int j = 0; j < bottleSize; j++)//Check if there is a different color in the bottle. 
                if (!colors[j].equals(currentColor))
                    hasWon = false;
            
            for (int j = bottleSize-1; j > -1; j--)//put the colors back in the bottle.
                currentBottle.push(colors[j]);
        }


        return hasWon;
    }

    //A method for starting the game.
    public void playGame()
    {   
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        System.out.println("Write \"Start\" to begin playing and \"finish\" to abort");

        String play = input.next();
        if (play.equals("Start") || play.equals("start") || play.equals("START"))
            this.gaming();

        return;
    }

    //The main method that uses everything we have defined so far to play the game.
    private void gaming()
    {   
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        boolean shouldGameContinue = true;

        //This has been put here, in the case that the colors were initialized in a way that the game was already won.
        if (this.hasWon()) {
            System.out.println(cyan + "-----------------------\\<^-^>/------------------------");
            System.out.println("|                  RARE AUTO WIN!!!!!                |");
            System.out.println("------------------------------------------------------" + resetColor);
            System.out.println("Your final bottles:");
            this.display();
            shouldGameContinue = false; //End the game if it's won
        }

        while(shouldGameContinue)  {
            System.out.println("Current bottles:");
            this.display();

            System.out.println("Input your command: ");
            System.out.println("command list: select, deselect, pour, swap, selectNext, selectPrevious, undo, finish");
            
            String command = input.next();

            switch (command) {
                case "select":
                    int bottleNum = input.nextInt();
                    this.select(bottleNum);
                    break;
                
                case "deselect":
                    this.deselect();
                    break;
                
                case "selectNext":
                    this.selectNext();    
                    break;
                
                case "selectPrevious":
                    this.selectPrevious();                    
                    break;
                
                case "swap":
                    int swapNum = input.nextInt();
                    this.swap(swapNum);                   
                    break;
                
                case "pour":
                    int pourNum = input.nextInt();
                    this.pour(pourNum);
                    break;
                
                case "undo":
                    this.undo();
                    break;
                    
                case "finish":
                    System.out.println("Game is finished");
                    shouldGameContinue = false;
                    break;
                
                default:
                System.out.println(red + "------------------------<!!!>-------------------------");
                System.out.println("|              Invalid command try again             |");
                System.out.println("------------------------------------------------------" + resetColor);
                    /* This if here just consumes the rest of the line so it's not accidentally read by input.next() again.
                     * for example "seleekt 3" causes the default of this switch to run twice but if we clear out the line things should be fine.*/
                    if (input.hasNextLine()) {
                        input.nextLine();
                    }
                    break;
            
            }//End of switch
            
            //Check if the game is won at this point
            if (this.hasWon()) {
                this.deselect();
                System.out.println(purple + "-----------------------\\(^-^)/------------------------");
                System.out.println("|                     YOU WIN!!!!!                   |");
                System.out.println("------------------------------------------------------" + resetColor);
                System.out.println("Your final bottles:");
                this.display();
                shouldGameContinue = false; // End the game if this.hasWon() returns true.
            
            }//End if
        
        }//End while

    }//End of gaming method.

    //This method helps undo() in the case that we have to undo a pouring action.
    private boolean pourUndo(int bottleNumber, int counter)
    {
        //If no bottle is selected return fase and exit method.
        if (selectedBottle == null) return false;

        boolean poured = false;
        //destinationBottle is the bottle that we are pouring onto
        Stack<String> destinationBottle = bottles.getItemAt(bottleNumber-1);
        //Counter show how many colors were poured into a bottle so we move just that many colors back.
        while (counter != 0) {
            poured = true;
            destinationBottle.push(selectedBottle.pop());
            counter--;
        }//end while

        return poured;
        
    }
}//End of WaterSortGame class.