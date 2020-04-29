package simplecalc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JTextField;

import customclasses.CalculatorClass;
import customevents.ButtonEvent;
import customlisteners.ButtonListener;

public class MainCalcFrame extends JFrame 
{

	private CalculatorBtnPanel calcBtnPanel;
	private DisplayPanel displayPanel;
	private CalculatorClass calc = new CalculatorClass();
	
	public MainCalcFrame() 
	{
		super("Simple Calculator");
		// set the layout
		//getContentPane().setBackground(SystemColor.textHighlight);
		
		getContentPane().setLayout(new BorderLayout());
		
		calcBtnPanel = new CalculatorBtnPanel();
		displayPanel = new DisplayPanel();
	
		// Add listeners to the panel.------------------
		calcBtnPanel.setButtonListener(new ButtonListener()
		{
			@Override
			public void buttonPressed(ButtonEvent be) 
			{
				//System.out.println(be.getString());
				char tmp = be.getString().charAt(0);
				String number = be.getString();
				String num;
				int numCnt = 1; 
				
				if((tmp >= '0' && tmp <= '9')  || (tmp == '.'))
				{
					//System.out.println("Value" +tmp);
					displayPanel.appendText(number);
				}
				else if(tmp == 'C')
				{
					displayPanel.clearTextField();
					numCnt = 1;
				}
				else if (tmp == '=')
				{
					num = displayPanel.getText();
					//System.out.println("Equals entered:  " + tmp);
					calc.setNumberTwo(Double.parseDouble(num));
					
					// Perform the operation.
					double result = calc.translateOperation();
					
					// set the panel
					displayPanel.clearTextField();
					displayPanel.appendText(Double.toString(result));
					
					// Set 
					numCnt = 1;
				}
				else
				{
					// Set the operation.
					calc.setOperation(tmp);
					
					// Get the number from field.
					num = displayPanel.getText();
					if(numCnt == 1)
					{
						calc.setNumberOne(Double.parseDouble(num));
						displayPanel.clearTextField();
						//System.out.print("Number ONe: " + calc.getNumberOne());
						numCnt++;
					}
					
					//ystem.out.println("Number entered" + num);
				}
			}
		});
		
		this.getContentPane().add(calcBtnPanel, BorderLayout.CENTER);
		this.getContentPane().add(displayPanel, BorderLayout.NORTH);
		
		
		// Set the frame.------------------------------------
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}
}
