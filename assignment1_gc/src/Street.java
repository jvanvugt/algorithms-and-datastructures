
public class Street {
	private Intersection a;
	private Intersection b;
	
	public Street(Intersection a, Intersection b) {
		this.a = a;
		this.b = b;
	}
	
	public Intersection getA() {
		return a;
	}
	
	public Intersection getB() {
		return b;
	}
}
