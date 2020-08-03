public class MyArraySizeException extends Exception {

    public MyArraySizeException(String message) {
        super(message);
        System.out.println("Ошибка! Неверный размер массива.");
    }

    public MyArraySizeException() {

    }
}
