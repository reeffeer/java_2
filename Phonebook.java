import java.util.TreeMap;
import java.util.TreeSet;

public class Phonebook {

    private TreeMap<String, TreeSet<String>> phonebook;

    public Phonebook() {
        phonebook = new TreeMap<>();
    }

    public void add(String name, String phoneNumber) {
        if (phonebook.containsKey(name)) {
            phonebook.get(name).add(phoneNumber);
        } else {
            TreeSet<String> treeSet = new TreeSet<>();
            treeSet.add(phoneNumber);

            phonebook.put(name, treeSet);
        }
    }

    public String get (String name) {
        if (phonebook.containsKey(name)) {
            return phonebook.get(name).toString();
        } else {
            return "No this phone number.";
        }
    }
}
