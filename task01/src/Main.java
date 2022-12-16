import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    

    public static void main(String[] args) throws Exception{


        String csv = args[0];
        String template = args[1];

        List<Map<String, String>> info = new LinkedList<Map<String, String>>();
        info = MailMerge.getPersonInfo(csv);

        MailMerge.processTemplate("Harry", info, template);
    }
}
