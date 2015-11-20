package gradecalculator;

import javax.swing.JTextField;

public class CustomText extends JTextField{
    
    public CustomText(){
        super(4);
    }
    
    public JTextField getBox(){
        return this;
    }
    
}
