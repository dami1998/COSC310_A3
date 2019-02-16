
public class item {
	private String[] clothesItem = new String[5];
	private String[][] keyword = new String[5][5];
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
			keyword[1][0]="buy";
			keyword[1][1]="looking for";
			keyword[1][2]="";
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
		if(question.contains("looking for")||question.contains("there any"))
			return 1;
		return -1;
	}
	
}
