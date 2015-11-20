package gradecalculator;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class Viewer extends JFrame {

    private ArrayList<CustomSlider> arrSliders = new ArrayList<CustomSlider>();
    private JPanel textBoxes = new JPanel(new GridLayout());
    private JPanel sliderPanel = new JPanel(new GridLayout());
    private JPanel avgPanel = new JPanel(new GridLayout());
    private JPanel weightsPanel = null;
    private JPanel wrapperPanel = new JPanel();
    private int numOfPairs = 0;
    private int currCount = 0;
    private JTextField avg = null;

    public Viewer(String title, int wid, int hei) throws HeadlessException {
        super.setSize(wid, hei);
        this.setLayout(new FlowLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void enableVisibility() {
        super.setVisible(true);
    }

    public void setNumOfPairs(int n) {
        numOfPairs = n;
    }

    public void newSlidersAvg(int min, int max, int init, int times, boolean avgBox) {

        if (avgBox) {
            avg = new JTextField(4);
        }
        for (int i = 0; i < times; i++) {
            CustomSlider js = new CustomSlider(CustomSlider.HORIZONTAL, min, max, init);
            arrSliders.add(js);
            arrSliders.get(arrSliders.size() - 1).addChangeListener((ChangeListener) new SliderChange(js, arrSliders, avg, true, false, new ArrayList<Double>())); //passes arrsliders so that it will pay attention to the other sliders values
            System.out.println(arrSliders.size());
            textBoxes.add(js.getLinkedBox());
            sliderPanel.add(js);
        }
    }

    public void newSlidersWeighted(int min, int max, int init, int times, ArrayList<Double> weights, boolean avgBox) {
        weightsPanel = new JPanel(new GridLayout());
        if (avgBox) {
            avg = new JTextField(4);
        }
        for (int i = 0; i < weights.size(); i++) {
            if (i == 0) { //set label
                JLabel label = new JLabel();
                label.setText("Weights");
                weightsPanel.add(label);
            }
            JTextField j = new JTextField(4);
            j.setText(new Double(new Double(weights.get(i)) * new Double(100)).toString() + "%");
            weightsPanel.add(j);
        }
        for (int i = 0; i < times; i++) {
            if (i == 0) { //set label
                JLabel label = new JLabel();
                label.setText("Grade Sliders");
                sliderPanel.add(label);
                
                JLabel labelVal = new JLabel(); //set val label
                labelVal.setText("Set Value");
                textBoxes.add(labelVal);
            }
            CustomSlider js = new CustomSlider(CustomSlider.HORIZONTAL, min, max, init);
            arrSliders.add(js);
            arrSliders.get(arrSliders.size() - 1).addChangeListener((ChangeListener) new SliderChange(js, arrSliders, avg, true, true, weights)); //passes arrsliders so that it will pay attention to the other sliders values

            System.out.println(arrSliders.size());
            textBoxes.add(js.getLinkedBox());
            sliderPanel.add(js);
        }
    }

    public void enablePanels() {
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        if (weightsPanel != null) {
            
            wrapperPanel.add(weightsPanel);
        }
        wrapperPanel.add(sliderPanel, FlowLayout.LEFT);
        wrapperPanel.add(textBoxes, FlowLayout.CENTER);
        if (avg != null) {
            JLabel label = new JLabel();
            label.setText("Total Grade");
            avgPanel.add(label);
            avgPanel.add(avg);
            wrapperPanel.add(avgPanel);
        }
        
        super.add(wrapperPanel);
        super.pack();
    }
//    public void enablePanels() {
//        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
//        super.add(sliderPanel, FlowLayout.LEFT);
//        super.add(textBoxes, FlowLayout.CENTER);
//        if (avg != null) {
//            avgPanel.add(avg);
//            super.add(avgPanel);
//        }
//        if(weightsPanel!=null){
//            super.add(weightsPanel);
//        }
//
//    }
}
