import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
            // String address = null;
            // // \n not working
            // if (words[2].toString().contains("\n")) {
            //     address = words[2].toString().replaceAll("\\n", "\\r\\n");
            // } else {
            //     address = words[2].toString();
            // }
            System.out.println(words[2]);
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

        File file = new File("task01/src/resource/" + template);
        BufferedReader br = new BufferedReader(new FileReader(file));
        Writer w = new FileWriter("task01/src/resource/test.txt");
        BufferedWriter bw = new BufferedWriter(w);
        // FileOutputStream fos = new FileOutputStream("task01/src/resource/" + template);

        Info profile = info.get(name);
        System.out.println("First name: " + profile.getFirstName() + " Last name: " + profile.getLastName() + 
        "\nAddress: " + profile.getAddress() + " Years: " + profile.getYears());
        String line = null;
        String newLine = null;

        while ((line = br.readLine()) != null) {

            if (line.contains("<<address>>")) {
                newLine = line.replaceAll("<<address>>", profile.getAddress());
                bw.write(newLine);
            }
            if (line.contains("<<first_name>>")) {    
                newLine = line.replaceAll("<<first_name>>", profile.getFirstName());
                bw.write(newLine);
            }
            if (line.contains("<<last_name>>")) {    
                newLine = line.replaceAll("<<last_name>>", profile.getLastName());
                bw.write(newLine);
            }
            if (line.contains("<<years>>")) {
                newLine = line.replaceAll("<<years>>", profile.getYears().toString());
                bw.write(newLine);
            }
            bw.newLine();
        }

        br.close();
        bw.close();
        w.close();
    }

}