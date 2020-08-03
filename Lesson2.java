import com.sun.jdi.Value;

import java.lang.invoke.VarHandle;
import java.util.Arrays;

public class Lesson2 {

    public static void main(String[] args) {

        try {
            arrayExcept();
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка! Неверный размер массива.");
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка! Некоторые элементы массива не могут быть приведены к целочисленному типу.");
            e.printStackTrace();
        }

    }



    public static void arrayExcept() throws MyArraySizeException, MyArrayDataException {
        String[][] myArray = {{"4", "5", "k", "0"}, {"8", "4", "8", "3"}, {"1", "5", "2", "7"}, {"8", "6", "4", "3"}};
        //System.out.println(Arrays.deepToString(myArray));
        int i, j;
        int summary = 0;
        if (myArray.length != 4) { //если длина массива (количество строк) не равно 4
            throw new MyArraySizeException();
        }
        for (i = 0; i < myArray.length; i++) {
            if (myArray[i].length != 4) {  //если длина каждой строки не равна 4
                throw new MyArraySizeException("Ошибка! Неверный размер массива.");
            }

            for (j = 0; j < myArray.length; j++) {
                dates(myArray[i][j], i, j);
                summary += Integer.parseInt(myArray[i][j]);

            }
        }
        System.out.println(summary);
    }

    private static void dates(String myArray, int x, int y) throws MyArrayDataException {
        for (int i = 0; i < myArray.length(); i++) {
            if (myArray.charAt(i) != '0' || myArray.charAt(i) != '1'|| myArray.charAt(i) != '2'|| myArray.charAt(i) != '3'|| myArray.charAt(i) != '4'|| myArray.charAt(i) != '5'|| myArray.charAt(i) != '6'|| myArray.charAt(i) != '7'|| myArray.charAt(i) != '8'|| myArray.charAt(i) != '9') {
                throw new MyArrayDataException(x, y);
            }
        }
    }


}
