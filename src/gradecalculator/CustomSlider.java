package gradecalculator;

import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class CustomSlider extends JSlider {

    private JTextField linkedField = new JTextField(4);
    
    public CustomSlider(int pos, int min, int max, int init) {
        super(pos, min, max, init);
    }
    
    public JTextField getLinkedBox(){
        linkedField.setText("VAL");
        return linkedField;
    }
        
}