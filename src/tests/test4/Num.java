package tests.test4;

public class Num implements Expression{
	double val;
	public Num(double val) {
		this.val=val;
	}
	@Override
	public Double calculate() {
		return val;
	}
	
}
