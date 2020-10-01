package assignment4.part1.fruit;

public class Citris extends Fruit{
    private String taste;
    public Citris() {
        taste = "defaultTaste";
    }
    public Citris(String taste, String color, boolean rotten) {
        /* this(); */
        this.taste = taste;
        super.setColor(color);
        super.setRotten(rotten);
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String toString() {
        return "Citris  color:" +  getColor() + "  isrotten:" + isRotten() + "  id:" +
                getId()+  "  taste:" + getTaste();
    }

    public boolean equals(Citris citris) {
        if (citris.getColor() == super.getColor() &&
            citris.isRotten() == super.isRotten())
            return true;
        else
            return false;
    }
}
