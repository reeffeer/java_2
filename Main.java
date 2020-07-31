public class Main {
    public static void main(String[] args) {
        Man man = new Man(150,30);
        Cat cat = new Cat(100,15);
        Robot robot = new Robot(200, 20);
        Track track1 = new Track("дорога",80);
        Track track2 = new Track("дорога", 200);
        Track track3 = new Track("дорога",110);
        Wall wall1 = new Wall("стена", 12);
        Wall wall2 = new Wall("стена", 25);

        Actions[] members = new Actions[] {man, cat, robot};
        Obstacles[] obstacles = new Obstacles[] {track1, wall1, track3, wall2, track2};

        int j;
        for (int i = 0; i < members.length; i ++) {
            boolean result = true;

            for (j = 0; j < obstacles.length; j ++) {
                result = obstacles[j].goAhead(members[i]);
                if (!result) {
                    break;
                }
            }
                if (result) {
                    System.out.println("Препятствие " + obstacles[j].getName() + " успешно преодолено.");
                } else {
                    System.out.println("Препятствие " + obstacles[j].getName() + " не по силам.");
                }
            }
        }





}
