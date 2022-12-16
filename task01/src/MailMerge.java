import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class MailMerge {
    
    public static Map<String, Info> getPersonInfo(String csv) throws Exception {

        Map<String, Info> infoList = new HashMap<String, Info>();

        Reader r = new FileReader("task01/src/resource/" + csv);
        BufferedReader br = new BufferedReader(r);

        //Skip the first line
        String line = br.readLine();

        while ((line = br.readLine()) != null) {

            String[] words = line.split(",");
            Info person = new Info(words[0], words[1], words[2], Integer.valueOf(words[3]));
            String id = words[0].toString();
            // Set up map with id and the person's information
            infoList.put(id, person);
        }
        br.close();
        r.close();
        return infoList;
    }


    public static void processTemplate(String name, Map<String, Info> info, String template) throws Exception {

        Reader r = new FileReader("task01/src/resource/" + template);
        BufferedReader br = new BufferedReader(r);
        Writer w = new FileWriter("task01/src/resource/test.txt");
        BufferedWriter bw = new BufferedWriter(w);
        FileOutputStream fos = new FileOutputStream("task01/src/resource/" + template);

        Info profile = info.get(name);
        System.out.println("First name: " + profile.getFirstName() + " Last name: " + profile.getLastName() + 
        "\nAddress: " + profile.getAddress() + " Years: " + profile.getYears());
        String line;

        while ((line = br.readLine()) != null) {
            
            if (line.contains("<<address>>"))
                line.replaceAll("<<address>>", profile.getAddress());
            if (line.contains("<<first_name>>"))    
                line.replaceAll("<<first_name>>", profile.getFirstName());
            if (line.contains("<<last_name>>"))     
                line.replaceAll("<<last_name>>", profile.getLastName());
            if (line.contains("<<years>>"))
                line.replaceAll("<<years>>", profile.getYears().toString());

            bw.write(line);
        }

        br.close();
        r.close();
        bw.close();
        w.close();
    }

}
