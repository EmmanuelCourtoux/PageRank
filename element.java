package LastVersion;

public class element implements Comparable<element>{
	int i;
	int j;
	double value;
	
	element(int i, int j, double value){
		this.i = i;
		this.j = j;
		this.value = value;
	}

	@Override
	public int compareTo(element e2) {
		return Integer.compare(this.j, e2.j);
	}
	
	public void print() {
		System.out.println( " "+ this.i +" "+ this.j +" "+ this.value);
	}

}
