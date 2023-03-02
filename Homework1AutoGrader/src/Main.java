import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the file path to all of the code: ");
        // TODO: Need to validate this input later and try/catch it
        String filePath = input.nextLine();
        Grader grader = new Grader(filePath);
        File[] test = grader.getFiles();
        for(File file : test){
            if(file.isFile()){
                grader.getGrade(file);
            }
        }
    }
}
