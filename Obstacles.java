public abstract class Obstacles {
    private String name;
    protected Obstacles(String name) {
        this.name = name;
    }

    protected abstract boolean goAhead (Actions actions);

    public String getName() {
        return name;
    }
}
