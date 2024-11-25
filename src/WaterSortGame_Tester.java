public class WaterSortGame_Tester 
{
    public static void main(String[] args) {
        
        String[] colors = {"red", "blue", "green"};
        WaterSortGame game1 = new WaterSortGame(colors, 5);

        game1.display();
        System.out.println();
        
        game1.select(1);
        System.out.println();
        
        game1.pour(6);
        game1.display();

        game1.deselect();
        System.out.println();
        game1.display();
        
        
    }


}
