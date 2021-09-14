package ca.bcit.winter2021.comp2522.midterm.question2;

public class Road implements Comparable<Road>{
    private double distance;
    private String[] cities;

    Road(double distance, String[] cities) {
        this.distance = distance;
        this.cities = cities;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        }

        String[] otherCities = ((Road) obj).getCities();
        return cities[0].equals(otherCities[0]) && cities[1].equals(otherCities[1]) ||
                cities[0].equals(otherCities[1]) && cities[1].equals(otherCities[0]);
    }

    public String[] getCities() {
        return cities;
    }

    public boolean isCityConnected(String cityName) {
        return cities[0].equals(cityName) || cities[1].equals(cityName);
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Road road) {
        if (this.equals(road)) {
            return 0;
        } else if (this.distance < road.getDistance()) {
            return -1;
        } else {
            return 1;
        }
    }
}
