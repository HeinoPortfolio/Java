import javax.swing.*;

public class ConCircTest {

	public static void main(String[] args) 
	{
		JFrame concircApp = new JFrame();
		
		concircApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		concircApp.setSize(300, 300);
		concircApp.setTitle("Concentric Circles");
		concircApp.setResizable(false);
		
		System.out.println(concircApp.getSize().height  + "    " +concircApp.getSize().width );
		
		concircApp.add(new ConcentricCircles(concircApp.getSize().height, concircApp.getSize().width));
		
		concircApp.setVisible(true);
	}

}
