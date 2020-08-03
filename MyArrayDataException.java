public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(String message) {
        super(message);
        System.out.println("Ошибка! Некоторые элементы массива не могут быть приведены к целочисленному типу.");
    }

    public MyArrayDataException() {

    }


    public MyArrayDataException(int i, int j) {
    }
}
