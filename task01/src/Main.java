import java.util.HashMap;
import java.util.Map;

public class Main {
    

    public static void main(String[] args) throws Exception{

        String csv = args[0];
        String template = args[1];

        // Reader r = new FileReader(csv);
        // BufferedReader br = new BufferedReader(r);

        // //Skip the first line
        // String line = br.readLine();

        // while ((line = br.readLine()) != null) {

        //     String[] words = line.split(",");
        //     Info person = new Info(words[0], words[1], words[2], Integer.valueOf(words[3]));
        // }

        Map<String, Info> info = new HashMap<String, Info>();
        info = MailMerge.getPersonInfo(csv);

        // Listing map values
        // for (Info i : info.values()) {
        //     System.out.println("First name: " + i.getFirstName() + " Last name: " + i.getLastName() + 
        //                             "\nAddress: " + i.getAddress() + " Years: " + i.getYears());
        // }

        MailMerge.processTemplate("Harry", info, template);
    }
}
