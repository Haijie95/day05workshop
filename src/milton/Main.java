package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Main {
    
    public static void main(String[] args) throws Exception {

        String filename=args[0];
        System.out.printf("Processing %s\n", filename);

        //open the file, read the first 100 lines print out each line
        //close the file when done

        String filepath=""+filename;
        //Path pth=Paths.get(filepath); //making a path from string
        //File fobj=pth.toFile(); //pointing to the file from the path
        String line;
        
        FileReader fr = new FileReader(filepath);
        BufferedReader bdf = new BufferedReader(fr);
        //System.out.println("Check here\n");
        
        Integer i=0;
        Integer totalwords=0;
        Map<String,Integer> wordKey=new HashMap<String,Integer>();

        //while(null!=(line=bfr.readline()))
        for(i=0;i<100;i++){
            line=bdf.readLine();
            if(null==line){
                break;
            }
            
            String[] words=line.trim().split(" ");
            
            for(String eachWord:words){
                //Integer eachWord=wordds.getOrDefault(eachword,0) if exist get value else set as 0
                //v++; //0+1=1
                //wordMap.put(w,v); //put back 1
                
                if(wordKey.containsKey(eachWord)){
                    wordKey.put(eachWord, wordKey.get(eachWord)+1);
                }
                else{
                    wordKey.put(eachWord,1);
                }
            }
            totalwords+=words.length;
            System.out.printf("%d: %s  \n",i,line.trim().toUpperCase());
        }
        System.out.printf("Total number of words: %d",totalwords);
        System.out.println(wordKey);
        Set<String> uniqueWords= wordKey.keySet();
        System.out.printf("Number of Unique words: %d",uniqueWords.size()); //wordkey.size also works
        
        String fileName="words.csv";
        Path pth=Paths.get(fileName); 
        File fobj=pth.toFile();
        fobj.createNewFile();
        String summary;
        FileWriter fw = new FileWriter(fileName, false);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write("Word,Count\n");
        bfw.flush();
        //bfw.close();
        for(String w:uniqueWords){
            
            summary=String.format("%s,%d\n",w,wordKey.get(w));
            bfw.write(summary);

            bfw.flush(); //make sure changes are saved before closing
            //bfw.close();
            //System.out.printf("Word: %s Count:%d\n",w,wordKey.get(w));
        }
    /*  Chuk's Version
        public static final String HEADER="Word,Count";
    
    
    
        Create csv file
        FileOutputStream fs = new FileoutputStream(args[1]);
        OutputStreamWriter osw = new OutputStreamWriter(fs);

        osw.write(HEADER);
        for(String w:uniqueWords){
            
            String lineS=String.format("%s,%d\n",w,wordKey.get(w));
            osw.write(lineS);
            
        }
        osw.flush();
        osw.close();
        fos.close();

    */

        bfw.close();
        bdf.close();
        fr.close();
    }
}
