package entities;

public class Characteristics {

	private Long id;
	private int weaponSkill;
	private int ballisticSkill;
	private int strength;
	private int toughness;
	private int agility;
	private int intelligence;
	private int perception;
	private int willpower;
	private int fellowship;

	private CharacteristicsPurchaseLevel purchaseLevel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWeaponSkill() {
		return weaponSkill;
	}

	public void setWeaponSkill(int weaponSkill) {
		this.weaponSkill = weaponSkill;
	}

	public int getBallisticSkill() {
		return ballisticSkill;
	}

	public void setBallisticSkill(int ballisticSkill) {
		this.ballisticSkill = ballisticSkill;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getToughness() {
		return toughness;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getPerception() {
		return perception;
	}

	public void setPerception(int perception) {
		this.perception = perception;
	}

	public int getWillpower() {
		return willpower;
	}

	public void setWillpower(int willpower) {
		this.willpower = willpower;
	}

	public int getFellowship() {
		return fellowship;
	}

	public void setFellowship(int fellowship) {
		this.fellowship = fellowship;
	}

	public CharacteristicsPurchaseLevel getPurchaseLevel() {
		return purchaseLevel;
	}

	public void setPurchaseLevel(CharacteristicsPurchaseLevel purchaseLevel) {
		this.purchaseLevel = purchaseLevel;
	}

}
