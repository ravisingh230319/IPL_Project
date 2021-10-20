import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ipl {
    public static void main(String[] args){
        String matchesPath = "/home/ravi/Mountblue/IPL_Project/input/matches.csv";
        String deliveriesPath = "/home/ravi/Mountblue/IPL_Project/input/deliveries.csv";
        String matchesLine;
        String deliveriesLine;

        try {
            BufferedReader matchesBufferedReader = new BufferedReader(new FileReader(matchesPath));
            BufferedReader deliveriesBufferedReader = new BufferedReader(new FileReader(deliveriesPath));
            matchesBufferedReader.readLine();
            deliveriesBufferedReader.readLine();

            while((matchesLine = matchesBufferedReader.readLine()) != null) {
            }
            while((deliveriesLine = deliveriesBufferedReader.readLine()) != null)
            {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
