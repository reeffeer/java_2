public class Cat implements Actions {

    private int endurance;
    private int jumpingAbility;

    public Cat (int endurance, int jumpingAbility) {
        this.endurance = endurance;
        this.jumpingAbility = jumpingAbility;
    }

    @Override
    public void run () {
        System.out.println("Кот побежал.");
    }

    @Override
    public void jump () {
        System.out.println("Кот прыгнул.");
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
