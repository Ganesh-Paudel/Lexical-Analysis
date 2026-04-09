package parser;

import java.util.HashMap;

/**
 * Global variable storage and lookup for the parser/evaluator.
 * 
 * This class maintains a static HashMap of all variables assigned during
 * program execution. The Parser uses this to store variable values when
 * processing assignment statements, and TreeNode implementations use it
 * to look up variable values during expression evaluation.
 * 
 * @author Ganesh
 * @version 1.0
 */
public class Memory {
    /** Static storage for all defined variables (name -> value) */
    private static final HashMap<String, Double> table = new HashMap<>();

    /**
     * Stores or updates a variable value in memory.
     * 
     * @param variableName The name of the variable to store
     * @param variableValue The numeric value to assign to the variable
     */
    public static void set(String variableName, double variableValue) {
        table.put(variableName, variableValue);
    }

    /**
     * Retrieves the value of a variable from memory.
     * 
     * Prints a warning message if the variable is not defined (not yet assigned).
     * 
     * @param variableName The name of the variable to look up
     * @return The value of the variable, or null if not found (which would cause an error)
     */
    public static double get(String variableName) {
        if (!table.containsKey(variableName)) {
            System.out.println("Undefined Variable Name");
        }
        return table.get(variableName);
    }

}
