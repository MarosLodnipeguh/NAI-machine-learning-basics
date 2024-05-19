public class Entry {

    private double[] vector;
    private String className;


    public Entry (double[] proportionsVector, String language) {
        this.vector = proportionsVector;
        this.className = language;
    }

    public double[] getVector () {
        return vector;
    }
    public String getClassName () {
        return className;
    }

}
