

import java.util.ArrayList;

/*=====================================*/
/* This is the object structure  class */
/* in the class diagram of the visitor */
/* design pattern                      */
/*=====================================*/

public class CompositeStructure{

	private ArrayList<ComputerPart> parts;

	public CompositeStructure(){
		parts = new ArrayList<ComputerPart>();
    }

	public void attach(ComputerPart equip){
        if(equip != null)
		parts.add(equip);
    }

    public void detach(ComputerPart equip){
	    if(equip != null)
		    parts.remove(equip);
    }

	public void accept(Visitor v){
		int len =parts.size();
		for (int i=0; i < len; i++){
			ComputerPart part = parts.get(i);
			part.accept(v);
        }
	}
}