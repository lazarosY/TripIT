import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class ProgramCreator {
    public static void main (String[] args){
        String s = "Hey";
        ProgramCreator helper = new ProgramCreator();
    try{
        helper.programMaker(s);
    } catch (IOException e){
        System.err.println("An error occured while trying to write to the file" + e.getMessage());
        System.exit(1);
    } 
}
    public static void programMaker(String gptresponse) throws IOException {
        File file =  new File(System.getProperty("user.home") + "/" +"vacation program.txt");
        try (var out = new BufferedWriter(new FileWriter(file))) {
            out.write(gptresponse);
        } catch (IOException e) {
            System.err.println("Couldn't write byte" + e.getMessage());
            System.exit(1);
        }
}
}