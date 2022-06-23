package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

	}
	
	public String toString() {
		return "Name: " + this.getName() + ", " +
				"Type of Ability: Healing Ability, " +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Heal Amount: " + this.getHealAmount();
	}
	
	public String stringgame() {
		return "Name: " + this.getName() + ", \n" +
				"Type of Ability: Healing Ability, \n" +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Current Cool Down: " + this.getCurrentCooldown() + ", \n" +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Heal Amount: " + this.getHealAmount();
	}
}
