public class Entry {

    private String group;
    private Double[] values;
    private double distanceToCentroid;

    public Entry(String group, Double[] values) {
        this.group = group;
        this.values = values;
        this.distanceToCentroid = 0;
    }

    public String getGroup() {
        return group;
    }

    public Double[] getValues() {
        return values;
    }

    public double getDistanceToCentroid() {
        return distanceToCentroid;
    }

    public void setDistanceToCentroid(double distance) {
        distanceToCentroid = distance;
    }

    public void setGroup (String key) {
        group = key;
    }
}
