import java.util.Random;

public class HealingItem implements Saleable {

	private static final int MAX_HEAL = 20;
	private static final int MIN_HEAL = 5;
	private static final int MAX_HEAL_TIME = 90;
	private static final int MIN_HEAL_TIME = 20;
	private static final int PRICE_COEFF = 40;
	private static final int HEAL_CONSTANT = 4;
	
	Random num = new Random();
	
	private int heal = (num.nextInt(MAX_HEAL - MIN_HEAL) + MIN_HEAL) * HEAL_CONSTANT;
	private int time = num.nextInt(MAX_HEAL_TIME - MIN_HEAL_TIME) + MIN_HEAL_TIME;
	private int price = (heal / time) * PRICE_COEFF;
	private int temp_price;
	
	@Override
	public String getSaleDescriptor(int haggling) {
		
		temp_price = (int) Math.round(price * 100 / haggling);
		
		String description = "Healing Potion\nA bottle per hero\nHealth boost: " + heal + "\nHeal time: " + time;
		
		return description;
	}

	@Override
	public int getPrice() {
		return temp_price;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getHeal() {
		return heal;
	}
	
	public int getHealConstant() {
		return HEAL_CONSTANT;
	}
}
