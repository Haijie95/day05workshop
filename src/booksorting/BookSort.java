package booksorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BookSort {

    //private String filepath;
    
    public static void main(String[] args) throws Exception{
        
        //System.out.println(args[0]);
        FileInputStream input = new FileInputStream(args[0]);
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader bdf = new BufferedReader(isr);

        String line;
        Map<String,String> allPublisher=new HashMap<String,String>();

        while(null!=(line=bdf.readLine())){
            //System.out.println(line);
            String[] perLine= line.split(",");
            String publisher=perLine[11];
            
            //System.out.printf("Publisher: %s Title: %s\n",publisher,perLine[1]);
            if(allPublisher.containsKey(publisher)){
                allPublisher.put(publisher,allPublisher.get(publisher)+","+perLine[1]);
            } else{
                allPublisher.put(publisher,perLine[1]);
            }
        }
//harrypootter+
        //System.out.println(allPublisher); //continue to read map
        
        
        Set<String> uniqueP=allPublisher.keySet();
        System.out.println(uniqueP.size());
        for (String eachP:uniqueP){
            System.out.printf("Publisher: %s\n",eachP);
            String filepath="src/bookDB/"+eachP+".csv";//define filepath for each unique publisher
            String[] allT=allPublisher.get(eachP).split(",");
            
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write("Number,Title\n");
            
            for(Integer i=1;i<allT.length+1;i++){
                System.out.printf("%d. -> %s\n",i,allT[i-1]); //number and
                String titles=String.format("%d,%s\n",i,allT[i-1]); //write in the csv file , means next column
                bfw.write(titles);
        
            }
            bfw.flush();
            bfw.close();
        }


    }
}
