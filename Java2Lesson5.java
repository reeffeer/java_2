public class Java2Lesson5 {

    static final int sizeArray = 2000000;
    static final int half = sizeArray / 2;
    static float value;
    static int i;


    public static void main(String[] args) {

        float[] array = new float[sizeArray];
        value = (float) (value * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        calcArrayNewValues(array);
        createNewArray(array);
    }

    private static void calcArrayNewValues(float[] array) {
        for (i = 0; i < sizeArray; i ++) {
            array[i] = 1;
        }

        long time = System.currentTimeMillis();

        for (i = 0; i < sizeArray; i ++) {
            array[i] = value;
        }

        System.out.println(System.currentTimeMillis() - time); //это мы высчитали разницу во времени, используя системное время до цикла и после.
    }


    private static void createNewArray(float[] array) {

        //заменили в основном массиве все значения в ячейках на 1
        for (i = 0; i < sizeArray; i ++) {
            array[i] = 1;
        }

         long time = System.currentTimeMillis();

        //создали массивы, длиной в половину основного массива
        float[] halfArray1 = new float[half];
        float[] halfArray2 = new float[half];

        //делим массив на 2 половины (копируем каждую половину основного массива в новые массивы)
        System.arraycopy(array, 0, halfArray1, 0, half);
        System.arraycopy(array, half, halfArray2, 0, half);

        //создаем потоки для высчитывания новых значений каждой половины массива
        Thread thread1 = new Thread(() -> {
            for (i = 0; i < half; i ++) {
                halfArray1[i] = value;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (i = 0; i < half; i ++) {
                halfArray2[i] = value;
            }
        });

        //запустили потоки поочереди
        thread1.start();
        thread2.start();

        //копируем новые значения малых массивов в один основной (склеиваеи основной массив из его же половин, но с новыми значениями)
        System.arraycopy(halfArray1, 0, array, 0, half);
        System.arraycopy(halfArray2, 0, array, half, half);

        System.out.println(System.currentTimeMillis() - time);

    }

}
