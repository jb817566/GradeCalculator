package gradecalculator;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GradeCalculator {

    protected static ArrayList<Double> processedw = new ArrayList<Double>();
    
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        Viewer v = new Viewer("GradeCalc", 800, 450);
        v.setNumOfPairs(3);
        
        String in1 = "";
        do {
            in1 = JOptionPane.showInputDialog("Enter the " + "weights. Comma-separated and in decimal form (i.e. \".25\" for 25%)");
            in1 = in1.replace(" ","");
        } while (!(in1.contains(",")));
        String[] wArr = in1.split(",");
        for (int i = 0; i < wArr.length; i++) {
            processedw.add(Double.parseDouble(wArr[i]));
        }
        
        v.newSlidersWeighted(0, 10000, 0, processedw.size(), processedw, true);
        v.enablePanels();
        v.enableVisibility();



    }
}
