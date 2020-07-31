public class Robot implements Actions {

    private int endurance;
    private int jumpingAbility;

    public Robot (int endurance, int jumpingAbility) {
        this.endurance = endurance;
        this.jumpingAbility = jumpingAbility;
    }

    @Override
    public void run () {
        System.out.println("Робот побежал.");
    }

    @Override
    public void jump ( ) {
        System.out.println("Робот прыгнул.");
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getJumpingAbility() {
        return jumpingAbility;
    }

    public void setJumpingAbility(int jumpingAbility) {
        this.jumpingAbility = jumpingAbility;
    }
}
