
public class item {
	private String[] clothesItem = new String[5];
	String[][] keyword = new String[5][5];
	private boolean initialized = false;
	public void initialize() {
		if(!initialized) {
			clothesItem[0] = "t-shirt";
			clothesItem[1] = "pants";
			clothesItem[2] = "hat";
			clothesItem[3] = "jacket";
			clothesItem[4] = "glove";
			//row0->state-1 keyword
			//row1->state0 keyword
			//row2->state1 keyword
			//row3->state2 keyword
			//row4->state3 keyword
			keyword[0][0]="that's all";
			keyword[0][1]="nevermind";
			keyword[0][2]="bye";
			keyword[0][3]="that is all";
			
			//no keyword for state 0
			
			keyword[2][0]="buy";
			keyword[2][1]="looking for";
			keyword[2][2]="any";
			keyword[2][3]="finding";
			keyword[2][4]="in stock";
			
			
			keyword[3][0]="online";
			initialized = true;
		}
		
	}
	
	public boolean findItem(String item) {
		for(int i = 0; i < clothesItem.length;i++) {
			if(item.toLowerCase().equals(clothesItem[i])) {
				return true;
			}
		}
		return false;
	}
	public int key(String question) {
		for(int i = 0; i < keyword.length;i++)
		{
			for(int j = 0; j < keyword[i].length;j++)
			{
				if(question.contains(keyword[i][j]))
				{
					return i-1;
				}
			}
		}
		return -1;
	}
	
}
