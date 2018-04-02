package entities;

public class CharacteristicsPurchaseLevel {

	private Long charcteristicsId;
	private int weaponSkillPurchaseLevel;
	private int ballisticSkillPurchaseLevel;
	private int strengthPurchaseLevel;
	private int toughnessPurchaseLevel;
	private int agilityPurchaseLevel;
	private int intelligencePurchaseLevel;
	private int perceptionPurchaseLevel;
	private int willpowerPurchaseLevel;
	private int fellowshipPurchaseLevel;

	public Long getCharcteristicsId() {
		return charcteristicsId;
	}

	public void setCharcteristicsId(Long charcteristicsId) {
		this.charcteristicsId = charcteristicsId;
	}

	public int getWeaponSkillPurchaseLevel() {
		return weaponSkillPurchaseLevel;
	}

	public void setWeaponSkillPurchaseLevel(int weaponSkillPurchaseLevel) {
		if(weaponSkillPurchaseLevel > 4 || weaponSkillPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.weaponSkillPurchaseLevel = weaponSkillPurchaseLevel;
	}

	public int getBallisticSkillPurchaseLevel() {
		return ballisticSkillPurchaseLevel;
	}

	public void setBallisticSkillPurchaseLevel(int ballisticSkillPurchaseLevel) {
		if(ballisticSkillPurchaseLevel > 4 || ballisticSkillPurchaseLevel< 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.ballisticSkillPurchaseLevel = ballisticSkillPurchaseLevel;
	}

	public int getStrengthPurchaseLevel() {
		return strengthPurchaseLevel;
	}

	public void setStrengthPurchaseLevel(int strengthPurchaseLevel) {
		if(strengthPurchaseLevel > 4 || strengthPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.strengthPurchaseLevel = strengthPurchaseLevel;
	}

	public int getToughnessPurchaseLevel() {
		return toughnessPurchaseLevel;
	}

	public void setToughnessPurchaseLevel(int toughnessPurchaseLevel) {
		if(toughnessPurchaseLevel > 4 || toughnessPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.toughnessPurchaseLevel = toughnessPurchaseLevel;
	}

	public int getAgilityPurchaseLevel() {
		return agilityPurchaseLevel;
	}

	public void setAgilityPurchaseLevel(int agilityPurchaseLevel) {
		if(agilityPurchaseLevel > 4 || agilityPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.agilityPurchaseLevel = agilityPurchaseLevel;
	}

	public int getIntelligencePurchaseLevel() {
		return intelligencePurchaseLevel;
	}

	public void setIntelligencePurchaseLevel(int intelligencePurchaseLevel) {
		if(intelligencePurchaseLevel > 4 || intelligencePurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.intelligencePurchaseLevel = intelligencePurchaseLevel;
	}

	public int getPerceptionPurchaseLevel() {
		return perceptionPurchaseLevel;
	}

	public void setPerceptionPurchaseLevel(int perceptionPurchaseLevel) {
		if(perceptionPurchaseLevel > 4 || perceptionPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.perceptionPurchaseLevel = perceptionPurchaseLevel;
	}

	public int getWillpowerPurchaseLevel() {
		return willpowerPurchaseLevel;
	}

	public void setWillpowerPurchaseLevel(int willpowerPurchaseLevel) {
		if(willpowerPurchaseLevel > 4 || willpowerPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");
		this.willpowerPurchaseLevel = willpowerPurchaseLevel;
	}

	public int getFellowshipPurchaseLevel() {
		return fellowshipPurchaseLevel;
	}

	public void setFellowshipPurchaseLevel(int fellowshipPurchaseLevel) {
		if(fellowshipPurchaseLevel > 4 || fellowshipPurchaseLevel < 0) throw new IllegalArgumentException("Nooit groter dan 4 of lager dan 0");		
		this.fellowshipPurchaseLevel = fellowshipPurchaseLevel;
	}

}
