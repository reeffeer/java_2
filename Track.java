public class Track extends Obstacles {
    private int distance;

    public Track (String name, int distance) {
        super(name);
        this.distance = distance;
    }

    @Override
    protected boolean goAhead (Actions actions) {
        actions.run();
        System.out.println("Препятствие  " + super.getName() + " длиной " + this.distance);
        if (getDistance() <= actions.getEndurance()) {
            System.out.println(" преодолено.");
            return true;
        } else {
            System.out.println(" не преодолено.");
            return false;
        }
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
