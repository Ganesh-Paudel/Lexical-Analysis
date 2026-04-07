package parser;

import java.util.HashMap;

public class Memory {
    private static final HashMap<String, Double> table = new HashMap<>();

    public static void set(String variableName, double variableValue) {
        table.put(variableName, variableValue);
    }

    public static double get(String variableName) {
        if (!table.containsKey(variableName)) {
            System.out.println("Undefined Variable Name");
        }
        return table.get(variableName);
    }

}
