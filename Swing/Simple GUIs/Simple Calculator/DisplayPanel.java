package simplecalc;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DisplayPanel extends JPanel 
{
	
		private JTextField  txtField;
		
		public DisplayPanel ()	
		{
			super();
			txtField = new JTextField(10);
			Font font = new Font("SansSerif", Font.BOLD, 40);
			txtField.setFont(font);
			// set layout
			setLayout(new BorderLayout());
			Border innerBorder = BorderFactory.createLoweredBevelBorder();
			Border outerBorder = BorderFactory.createEmptyBorder(2,2,2,2);
			this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			// Add components.
			this.add(txtField, BorderLayout.CENTER);
		}
		
		public void appendText(String text)
		{
			String tempStr = this.txtField.getText();
			
			this.txtField.setText((tempStr + text));;
		}
		
		// Gets the text from the text field.
		public String getText()
		{
			return this.txtField.getText();
		}
		
		public void clearTextField() 
		{
			this.txtField.setText("");
		}
}
