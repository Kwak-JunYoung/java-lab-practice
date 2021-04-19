abstract class Mammal extends Animal{
	
	Mammal(String Name, float Weight) {
		super(Name, Weight);
	}
	
	abstract void bark();
	abstract String getFood();
}