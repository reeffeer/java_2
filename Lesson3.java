import java.util.*;

public class Lesson3 {
    public static void main(String[] args) {

        findAndShowUniqueWords();
        System.out.println();
        getAndAddPhoneNumbers();
    }

    public static void findAndShowUniqueWords() {

        System.out.println("Задание 1.");
        //создали массив
        String[] words = new String[] {"apricot", "pork", "raspberry", "beef", "garlic",
            "salt", "garlic", "apple", "raspberry", "juice",
            "onion", "garlic", "orange", "apple", "apple",
            "kiwi", "strawberry", "apple", "raspberry", "apricot"};

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("Общее количество слов в массиве: " + words.length + ".");
        System.out.println("Количество слов без повторов: " + uniqueWords.size() + ".");
        System.out.println("Вот список слов без повторов: \n" + uniqueWords);

        HashMap<String, Integer> wordToCount = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (wordToCount.containsKey(words[i])) {
                wordToCount.put(words[i], wordToCount.get(words[i])+1);
            }else {
                wordToCount.put(words[i], 1);
            }
        }
        System.out.println("Сколько раз встречается каждое слово в списке: \n"  + wordToCount);
    }


    public static void getAndAddPhoneNumbers() {

        System.out.println("Задание 2.");

        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Jose", "891120406");
        phoneBook.add("Tomoko", "87455438");
        phoneBook.add("Luis", "7285630");
        phoneBook.add("Morris", "764500756");
        phoneBook.add("Yokohama", "93635494");
        phoneBook.add("Luis", "43387669");

        System.out.println(phoneBook.get("Tomoko"));
        System.out.println(phoneBook.get("Suzukha"));
        System.out.println(phoneBook.get("Luis"));
    }

}
