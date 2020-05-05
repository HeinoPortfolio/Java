package customevents;

import java.util.EventObject;

public class ButtonEvent extends EventObject
{
		private String buttonNum;

		public ButtonEvent(Object source)
		{
			super(source);
			
		}
		
		public ButtonEvent(Object source, String btn)
		{
			super(source);
			this.buttonNum = btn;
		}
		
		public String getButtonNum() {
			return buttonNum;
		}

		public void setButtonNum(String buttonNum) {
			this.buttonNum = buttonNum;
		}

		public String getString()
		{
			return this.buttonNum;
		}
		
		
}


