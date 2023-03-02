import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Grader {

    private HashMap <String, Integer> grades = new HashMap<String, Integer>();
    private  File[] pathToCode;

    public Grader(String filePath){
        pathToCode = new File(filePath).listFiles();
    }

    public File[] getFiles(){
        return pathToCode;
    }

    public int getGrade(File file){
        byte byteCount = 0, shortCount = 0, intCount = 0, longCount = 0, floatCount = 0, doubleCount = 0;
        String author;
        String[] types = {"byte","short","int","long","float","double"};
        try{
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                String currentLine = fileReader.nextLine();
                if(currentLine.contains("@author")){
                    author = currentLine.substring(currentLine.indexOf("@author")+8);
                }
                //TODO: Try to figure out a way to count what variable is used using a switch

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
