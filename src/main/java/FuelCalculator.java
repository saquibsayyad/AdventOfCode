import java.io.*;

class FuelCalculator {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(FuelCalculator.class.getResourceAsStream("input.txt")));
        int mass, total = 0;
        while(reader.ready()){
            mass = Integer.parseInt(reader.readLine());
            total += calculateTotalFuel(mass);
        }

        System.out.println("Total fuel required is: " + total);

    }

    public static int calculateTotalFuel(int mass) {
        int totalFuel = 0, fuel = mass;
        do {
            fuel =calculateFuel(fuel);
            if(fuel > 0) {
                totalFuel+=fuel;
            }
        }while (fuel>0);

        return totalFuel;
    }


    public static int calculateFuel(int mass) {
        return Math.round(mass / 3) - 2;
    }
}