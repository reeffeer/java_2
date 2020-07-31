public class Wall extends Obstacles {
    private int height;

    public Wall (String name, int height) {
        super(name);
        this.height = height;
    }

    @Override
    protected boolean goAhead (Actions actions) {
        actions.jump();
        System.out.println("Препятствие  " + super.getName() + " высотой " + this.height);
        if (getHeight() <= actions.getJumpingAbility()) {
            System.out.println(" преодолено.");
            return true;
        } else {
            System.out.println(" не преодолено.");
            return false;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
