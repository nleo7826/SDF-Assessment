import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MailMerge {
    
    public static List<Map<String, String>> getPersonInfo(String csv) throws Exception {

        List<String> catogorieList = new LinkedList<String>();
        List<Map<String, String>> profile = new LinkedList<Map<String, String>>();
        

        Reader r = new FileReader("task01/src/resource/" + csv);
        BufferedReader br = new BufferedReader(r);

        //Process first line
        String line = br.readLine();
        String[] cat = line.split(",");
        
        for (String c : cat) {
            catogorieList.add(c);
        }

        while ((line = br.readLine()) != null) {

            Map<String, String> map = new HashMap<String, String>();

            String[] words = line.split(",");

            for (String w : words) {
                for (String c : catogorieList) {
                    map.put(c, w);
                    break;
                }
            }
            System.out.println("key value: " + map.entrySet());
            profile.add(map);
        }
        
        br.close();
        r.close();
        return profile;
    }


    public static void processTemplate(String name, List<Map<String, String>> info, String template) throws Exception {

        File file = new File("task01/src/resource/" + template);
        BufferedReader br = new BufferedReader(new FileReader(file));
        File file1 = new File("task01/src/resource/test.txt");
        Writer w = new FileWriter(file1);
        BufferedWriter bw = new BufferedWriter(w);

        // Print profile information
        // System.out.println("First name: " + profile.getFirstName() + " Last name: " + profile.getLastName() + 
        // "\nAddress: " + profile.getAddress() + " Years: " + profile.getYears());

        String line = null;
        String newLine = null;


        while ((line = br.readLine()) != null) {

            Map<String, String> currentProfile = new HashMap<String, String>();

            for (Map<String, String> m : info) {
                if (m.containsValue(name)) {
                    currentProfile = m;
                    break;
                }
            }

            // prints map
            // for (Map.Entry<String, String> entry : currentProfile.entrySet()) {
            //     String key = entry.getKey();
            //     Object value = entry.getValue();

            //     System.out.println("key value: " + key + " value: " + value);
            // }

            if (line.contains("<")) {

                String keyword = line.substring(line.lastIndexOf("<") + 1, line.indexOf(">"));
            
                if (currentProfile.containsKey(keyword)) {
                    newLine = line.replaceAll("<<" + keyword + ">>", currentProfile.get(keyword));
                    bw.write(newLine);
                }
            } else {
                newLine = line;
                bw.write(newLine);
            }
            
            bw.newLine();
        }

        br.close();
        bw.close();
        w.close();

        file.delete();
        File file2 = new File("task01/src/resource/" + template);
        file1.renameTo(file2);
    }


}