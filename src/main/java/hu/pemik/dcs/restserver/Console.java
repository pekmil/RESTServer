package hu.pemik.dcs.restserver.;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {

    private static Map<String, Integer> colorMap = new HashMap<String, Integer>();

    static {

        colorMap.put("reset", 0);

        colorMap.put("header", 92);
        colorMap.put("footer", 92);

        colorMap.put("title", 92);
        colorMap.put("label", 94);
        colorMap.put("info", 96);
        colorMap.put("highlight", 93);
        colorMap.put("number", 95);

        colorMap.put("alert", 91);

        colorMap.put("action", 91);
        colorMap.put("input", 91);

        colorMap.put("gray", 2);

        // View available colors
        // for(int i=0; i<=255; i++){
        //     changeColor(i);
        //     System.out.println( i + " == " + i);
        //     resetColor();
        // }

        // Show colorMap
        // for (String label : colorMap.keySet()) {
        //     changeColor(label);
        //     System.out.println( label + " == " + colorMap.get(label));
        //     resetColor();
        // }
        // waitForEnter();

    }

    /**
     * Private constructor. This class should not be instantiated.
     * Every method is static on this class.
     */
    private Console() {
    }

    /**
     * Clear the console
     */
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n" + doubleLineString());
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Change console output color by id
     *
     * @param label
     */
    public static void changeColor(String label) {
        System.out.print(colorString(label));
    }

    /**
     * Change console output color by id
     *
     * @param id
     */
    public static void changeColor(int id) {
        System.out.print(colorString(id));
    }

    /**
     * Change console output color by id
     *
     * @param label
     */
    public static String colorString(String label) {
        return colorString(colorMap.get(label));
    }

    /**
     * Generate color string by id
     *
     * @param id
     * @return String
     */
    public static String colorString(int id) {
        return (char) 27 + "[0m" + (char) 27 + "[" + Integer.toString(id) + "m";
    }

    /**
     * Reset colors
     */
    public static void resetColor() {
        System.out.print(resetString());
    }

    /**
     * Reset colors string
     */
    public static String resetString() {
        return colorString("reset");
    }

    /**
     * Render header
     *
     * @param text
     */
    public static void header(String text) {
        System.out.println(colorString("header") + text + resetString());
        doubleLine();
        System.out.println();
    }

    /**
     * Render footer
     *
     * @param text
     */
    public static void footer(String text) {
        doubleLine();
        System.out.println(colorString("footer") + text + resetString());
        resetColor();
    }


    /**
     * Render title
     *
     * @param text
     */
    public static void title(String text) {
        System.out.print(titleString(text));
        System.out.println();
    }

    /**
     * Generate title string
     *
     * @param text
     * @return String
     */
    public static String titleString(String text) {
        return colorString("title") + text + resetString();
    }

    /**
     * Render highlighted string
     *
     * @param text
     */
    public static void highlight(String text) {
        System.out.println(highlightString(text));
    }

    /**
     * Generate highlighted string
     *
     * @param text
     * @return String
     */
    public static String highlightString(String text) {
        return colorString("highlight") + text + resetString();
    }

    /**
     * Render info string
     *
     * @param text
     */
    public static void info(String text) {
        System.out.println(infoString(text));
    }

    /**
     * Generate info string
     *
     * @param text
     * @return String
     */
    public static String infoString(String text) {
        return colorString("info") + text + resetString();
    }

    /**
     * Render action string
     *
     * @param text
     */
    public static void action(String text) {
        System.out.println(actionString(text));
    }

    /**
     * Generate action string
     *
     * @param text
     * @return String
     */
    public static String actionString(String text) {
        return colorString("action") + text + resetString();
    }

    /**
     * Render label
     *
     * @param text
     */
    public static void label(String text) {
        System.out.print(labelString(text));
    }

    /**
     * Generate label string
     *
     * @param text
     * @return String
     */
    public static String labelString(String text) {
        return colorString("label") + text + resetString();
    }

    /**
     * Render number
     *
     * @param number
     */
    public static void number(int number) {
        System.out.print(numberString(number));
    }

    /**
     * Generate number string
     *
     * @param number
     * @return String
     */
    public static String numberString(int number) {
        return colorString("number") + Integer.toString(number) + resetString();
    }

    /**
     * Generate number string
     *
     * @param number
     * @return String
     */
    public static String numberString(String number) {
        return colorString("number") + number + resetString();
    }

    /**
     * Render menu action
     *
     * @param action
     */
    public static void menu(String action, String label) {
        System.out.println(colorString("action") + action + resetString() + " - " + colorString("label") + label + resetString());
    }

    /**
     * Render alert box
     *
     * @param text
     */
    public static void alert(String text) {
        System.out.println("\n" + lineString());
        System.out.print(colorString("alert") + text + resetString());
        System.out.println("\n" + lineString());
    }


    /**
     * Draw line
     */
    public static void line() {
        System.out.println(lineString());
    }

    /**
     * Get line string
     *
     * @return String
     */
    public static String lineString() {
        return colorString("gray") + "----------------------------------------------------------------------" + resetString();
    }

    /**
     * Draw doubleLine
     */
    public static void doubleLine() {
        System.out.println(doubleLineString());
    }

    /**
     * Get doubleLine string
     *
     * @return String
     */
    public static String doubleLineString() {
        return colorString("gray") + "======================================================================" + resetString();
    }


    /**
     * Format and print minutes.
     *
     * @param time [in minutes]
     */
    public static void timeFormat(int time) {
        System.out.print(timeString(time));
    }

    /**
     * Generate formatted String from minutes. E.g.:
     * 135 --> "2h 15m"
     *
     * @param time [in minutes]
     * @return String
     */
    public static String timeString(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        return colorString("number") + hours + "h " + minutes + "m" + resetString();
    }

    /**
     * Format and print money.
     *
     * @param money
     */
    public static void moneyFormat(int money) {
        System.out.print(moneyString(money));
    }

    /**
     * Generate formatted String from minutes. E.g.:
     * 517500 --> "517 500 Ft"
     *
     * @param money
     * @return String
     */
    public static String moneyString(int money) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();

        String format = "####";

        if (String.valueOf(money).length() > 4) {
            format = "###,###,###";
        }

        symbols.setGroupingSeparator(' ');
        return colorString("number") + (new DecimalFormat(format, symbols).format(money)) + " Ft" + resetString();
    }

    /**
     * Sleep
     *
     * @param ms
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println("This thread cannot sleep...");
        }
    }

    /**
     * Default sleep parameter
     */
    public static void sleep() {
        sleep(3000);
    }

    /**
     * Wait for user to continue
     */
    public static void waitForEnter() {
        System.out.println("\n(Press enter to continue...)");
        try {
            java.io.Console c = System.console();
            c.readLine();
        } catch (Exception e) {
            try {
                System.in.read();
            } catch (Exception x) {
            }
        }
    }

    /**
     * Get user input
     *
     * @param label
     * @return String
     */
    public static String getInput(String label) {

        Console.label(label);

        changeColor("input");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        try {
            input = reader.readLine();
        } catch (Exception e) {
            System.out.println("Cannot get input: " + e.getMessage());
        }

        resetColor();

        return input;

    }

    /**
     * Default param for getInput with overloading
     *
     * @return String
     */
    public static String getInput() {

        return getInput("\nChoose an option from the list: ");

    }

    /**
     * Get int input
     *
     * @param label
     * @return int
     */
    public static int getIntInput(String label) {
        while (true) {
            try {
                return Integer.valueOf(Console.getInput(label));
            } catch (Exception e) {
                info("Please provide a number...");
            }
        }
    }


    /**
     * Get date input
     *
     * @param label
     * @return String
     */
    public static String getDateInput(String label) {
        while (true) {
            try {
                String date = Console.getInput(label);
                Timestamp timestamp = Timestamp.valueOf(date + ":00");
                return date;
            } catch (Exception e) {
                info("Expected format: [YYYY-mm-dd HH:ii]. Example: 2016-05-16 18:30");
            }
        }
    }

    /**
     * Confirm. "Are you sure? (y/n)"
     * Default for wrong input is 'n'.
     *
     * @param label
     * @return boolean
     */
    public static boolean confirm(String label) {

        label(label);

        Scanner in = new Scanner(System.in);
        System.out.print(colorString("action"));
        String answer = in.nextLine().trim().toLowerCase();
        System.out.print(resetString());

        return answer.equals("y");

    }

    /**
     * Default param method for confirm
     *
     * @return boolean
     */
    public static boolean confirm() {
        return confirm("Are you sure? (y/n) ");
    }

    public static void handleException(Exception e) {
        Console.alert(e.getMessage());
        Console.info("Trace:");
        e.printStackTrace(System.out);
        Console.line();
        Console.waitForEnter();
    }

}