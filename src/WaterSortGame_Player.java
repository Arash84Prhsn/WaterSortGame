import java.util.Scanner;

public class WaterSortGame_Player
{
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int bottleCapacity;
        System.out.println("Would you like to start the water sort game?(YES/NO)");
        String playGame = input.nextLine();
        
        if (playGame.equals("YES") || playGame.equals("yes") || playGame.equals("Yes")) {
            System.out.println("Please input your colors separated by space. Press enter once you're done:");
            System.out.println("Valid color formats:(Green, green, GREEN)");
            String c = input.nextLine();
            String[] colors = c.split("\\s+");
            
            System.out.println("Please input the capacity of your bottles:");
            bottleCapacity = input.nextInt();
            
            WaterSortGame game1 = new WaterSortGame(colors, bottleCapacity);
            game1.playGame();
        }
        else return;
    }
}
