package classes;

public class Plastic {
    private final int id; // id del elemento plástico
    private final String name; // nombre del elemento plástico

    public Plastic(int id) {
        this.id = id;
        this.name = "Plastic # " + id;
    }

    public String getName() {
        return name;
    }
}
