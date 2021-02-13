package ee.taltech.iti0202.lotr;

public class Ring {
    public Type type;
    public Material material;

    enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }

    public Ring(Type type, Material material) {
        this.type=type;
        this.material=material;
    }

    public Type getType() {
        return type;

    }

    public Material getMaterial() {
        return material;
    }
}
