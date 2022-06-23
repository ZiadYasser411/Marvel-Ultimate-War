package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	
	public String toString() {
		return "Name: " + this.getName() + ", " +
				"Type of Ability: Damaging Ability, " +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Damage Amount: " + this.getDamageAmount();
	}
	public String stringgame() {
		return "Name: " + this.getName() + ", \n" +
				"Type of Ability: Damaging Ability, \n" +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Current Cool Down: " + this.getCurrentCooldown() + ", \n" +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Damage Amount: " + this.getDamageAmount();
	}
}
