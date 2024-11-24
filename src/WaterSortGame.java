//Arash Poorhasani 4021299123 First project Watersort game
import java.util.Random;

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
        
        //fill our bottles and add our bottles to the bottles the linked list.
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
        //for (int i = 0; i < bottleCapacity; i++)
          //  bottle.push("empty");
        
        bottles.addLast(bottle);    
    }

    public void display()
    {
        CircularSinglyLinkedList<Stack<String>> tempList = this.bottles;
        Stack<String> currentBottle = tempList.getFirst();
        
        //First fill the empty bottles with "empty" strings.
        for (int i = 0; i < numberOfBottles; i++)
        {
            while (currentBottle.getSize() != bottleCapacity) {
                currentBottle.push("empty");
            }

            currentBottle = tempList.getNextItem(currentBottle);
        }
        
        currentBottle = tempList.getFirst();

        for (int i = 0; i < bottleCapacity; i++)
        {
            for (int j = 0; j < numberOfBottles; j++) {
                System.out.print(" | "+currentBottle.pop());
                currentBottle = tempList.getNextItem(currentBottle);
            }
            System.out.println(" |");

        }
    }

    private static boolean isSelectable(Stack<String> stack)
    {
        String color = stack.top();
        int counter = stack.getSize();
        for (int i = 0; i < counter; i++)
            if (!color.equals(stack.pop()))
                return false;

        return true;

    }

    public boolean select(int bottleNumber)
    {
        if (isSelectable(bottles.getItemAt(bottleNumber-1)))
            selectedBottle = bottles.getItemAt(bottleNumber-1);

        return isSelectable(bottles.getItemAt(bottleNumber-1));
        
    }

    public void deselect()
    {
        if (selectedBottle != null)
            selectedBottle = null;

    }

    public void selectNext()
    {
        if (selectedBottle!=null)
        {
            selectedBottle = bottles.getNextItem(selectedBottle);
        }

    }

    public void selectPrevious()
    {
        if (selectedBottle!=null)
        {
            selectedBottle = bottles.getPreviousItem(selectedBottle);
        }

    }

    public boolean pour(int bottleNumber)
    {
        if (selectedBottle == null) return false;

        Stack<String> destinationBottle = bottles.getItemAt(bottleNumber);

        if (selectedBottle.top() == destinationBottle.top() && destinationBottle.getSize() < bottleCapacity)
        {    
            selectedBottle.push(bottles.getItemAt(bottleNumber).pop());
            this.deselect();
            return true;
        }

        if(selectedBottle.isEmpty())
        {
            selectedBottle.push(bottles.getItemAt(bottleNumber).pop());
            this.deselect();
            return true;
        }

        return false;
        
    }

}



