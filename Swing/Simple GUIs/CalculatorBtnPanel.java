package simplecalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import customevents.ButtonEvent;
import customlisteners.ButtonListener;

public class CalculatorBtnPanel extends JPanel implements ActionListener
{

	private JButton[] calcBtns;
	private JButton equalsBtn;
	
	private ButtonListener buttonListener;
	
	
	
	public CalculatorBtnPanel() 
	{
		String[] btnLabels = {"1", "2", "3","+", "4", "5", "6","-" ,"7", "8", "9", "*"
				,".","0","C", "/" };
		
		calcBtns = new JButton[btnLabels.length];
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;   // how much space the component takes up
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(5,5,5,5);
		
		// Create the buttons and their associated labels.
		int llbIndex = 1;
		for (int nxt = 0; nxt < calcBtns.length; nxt++)
		{
			// Create the buttons
			calcBtns[nxt] = new JButton(btnLabels[nxt]);
			// Add the action listener
			calcBtns[nxt].addActionListener(this);
			//add the components to the panel.
			calcBtns[nxt].setPreferredSize(new Dimension(40, 40));
			
			if((llbIndex % 4) != 0)
			{
				this.add(calcBtns[nxt], gc);
				gc.gridx = gc.gridx + 1;
			}
			else if(llbIndex % 4 == 0) 
			{
				this.add(calcBtns[nxt], gc);
				gc.gridx = 0;
				gc.gridy = gc.gridy + 1;		
			}
			llbIndex++;
		}// end for.
		
		// add other components
		equalsBtn = new JButton("=");
		equalsBtn.setBackground(SystemColor.textHighlight);
		equalsBtn.addActionListener(this);
		equalsBtn.setPreferredSize(new Dimension(40, 40));
		
		// place the button
		gc.gridx = 3;
		gc.gridy = 4;
		gc.weighty = 2;
		
		this.add(equalsBtn, gc);
		
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
	}
	
	// Set the button listener.-------------------------------------------------------------
	public void setButtonListener(ButtonListener btnListnr)
	{
		this.buttonListener = btnListnr;
	}
	
	
	public JButton[] getCalcBtns() {
		return calcBtns;
	}

	public void setCalcBtns(JButton[] calcBtns) {
		this.calcBtns = calcBtns;
	}

	public JButton getEqualsBtn() {
		return equalsBtn;
	}

	public void setEqualsBtn(JButton equalsBtn) {
		this.equalsBtn = equalsBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String tmpStr= ((JButton)e.getSource()).getText();
		ButtonEvent be = new ButtonEvent(this, tmpStr);
		
		if(this.buttonListener != null)
		{
			this.buttonListener.buttonPressed(be);
		}
		
		
	}
	
	
	
}
