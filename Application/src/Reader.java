import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    public static void read(){
        try
        {
            File file = new File("Data.txt");
            FileInputStream fis=new FileInputStream(file);
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine())
            {
                System.out.println(sc.nextLine());      //returns the line that was skipped
            }
            sc.close();     //closes the scanner
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
