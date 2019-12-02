import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GravityAssistProgram {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(FuelCalculator.class.getResourceAsStream("gravity-assist.txt")));

        String line = reader.readLine();

        List<Integer> memory = Arrays.asList(line.split(","))
                .stream()
                .map(c -> Integer.parseInt(c))
                .collect(Collectors.toList());


        for(int i=0; i<100; ++i) {
            for(int j=0; j<100; ++j) {

                List<Integer> freshMem = new ArrayList<>(memory);
                memory.set(1, i);
                memory.set(2, j);

                System.out.println("Trying with i,j : " + memory.get(1) + "," +memory.get(2));

                for(int k=0; k<freshMem.size(); k+=4) {
                    if(!performOperation(k, freshMem)) {
                        break;
                    }
                }
                System.out.println("Value at 0: " + memory.get(0));
                if(memory.get(0) == 19690720) {
                    System.out.println("verb: " + i + " noun: " + j);
                    break;
                }
            }
        }
    }


    private static boolean performOperation(int pos, List<Integer> memory) {

        switch (memory.get(pos)) {
            case 1:
                memory.set( memory.get(pos + 3), memory.get(memory.get(pos + 1)) + memory.get(memory.get(pos + 2)));
                System.out.println(memory.get(pos) +"," + memory.get(pos + 1) + "," + memory.get(pos + 2)+ "," + memory.get(pos + 3));
                return true;
            case 2:
                memory.set( memory.get(pos + 3), memory.get(memory.get(pos + 1)) * memory.get(memory.get(pos + 2)));
                System.out.println(memory.get(pos) +"," + memory.get(pos + 1) + "," + memory.get(pos + 2)+ "," + memory.get(pos + 3));
                return true;
            case 99:
                return false;
            default:
                System.out.println("Fatal error!! Opcode is " + memory.get(pos));
                return false;
        }

    }
}
