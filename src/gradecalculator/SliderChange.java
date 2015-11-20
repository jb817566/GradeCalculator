package gradecalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.math.BigDecimal;

public class SliderChange implements ChangeListener, ActionListener, WindowListener {

    CustomSlider slider = null;
    AtomicInteger val = new AtomicInteger();
    JTextField linked = null;
    JTextField averageBox = null;
    ArrayList<CustomSlider> otherSliders = null;
    private boolean isWeighted = false;
    private ArrayList<Double> weights = null;

    public SliderChange(CustomSlider j, ArrayList<CustomSlider> other, boolean tf) {
        super();
        slider = j;
        otherSliders = other;
        if (tf) {
            linked = slider.getLinkedBox();
        }
    }

    public SliderChange(CustomSlider j, ArrayList<CustomSlider> other, JTextField average, boolean tf, boolean isWeighted, ArrayList<Double> percentages) {
        super();
        averageBox = average;
        slider = j;
        otherSliders = other;
        if (tf) {
            linked = slider.getLinkedBox();
        }
        this.isWeighted = isWeighted;
        this.weights = percentages;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        val.set(slider.getValue());
        //System.out.println(new Double(val.get()) / new Double(100));
        linked.setText(new Double((new Double(val.get()) / new Double(100))).toString());
        int total = 0;
        int numActual = 0;
        String box = "";
        if (isWeighted) {
            for (int i = 0; i < otherSliders.size(); i++) {
                total += otherSliders.get(i).getValue() * weights.get(i);
                numActual++;

            }
            box = new Double(new Double(total)/new Double(100)).toString();
        } else {
            for (int i = 0; i < otherSliders.size(); i++) {
                if (otherSliders.get(i).getValue() != 0) {
                    total += otherSliders.get(i).getValue();
                    numActual++;
                }
            }
            if (total != 0) {
                BigDecimal bigTotal = new BigDecimal(total);
                BigDecimal bigNumActual = new BigDecimal(numActual);
                BigDecimal divider = new BigDecimal(100);
                box = new String(bigTotal.divide(bigNumActual, 2, BigDecimal.ROUND_DOWN).divide(divider, 2, BigDecimal.ROUND_DOWN).toString()).toString();
            }
        }
        averageBox.setText(box);

    }

    public JTextField getTBox() {
        return linked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
