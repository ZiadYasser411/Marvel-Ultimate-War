package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public abstract class Ability {
	private String name;
	private int manaCost;
	private int baseCooldown;
	private int currentCooldown;
	private int castRange;
	private AreaOfEffect castArea;
	private int requiredActionPoints;

	public Ability(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required) {
		this.name = name;
		this.manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.currentCooldown = 0;
		this.castRange = castRange;
		this.castArea = area;
		this.requiredActionPoints = required;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}
	public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

	public void setCurrentCooldown(int currentCoolDown) {
		if (currentCoolDown < 0)
			currentCoolDown = 0;
		else if (currentCoolDown > baseCooldown)
			currentCoolDown = baseCooldown;
		this.currentCooldown = currentCoolDown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}
	
	public String toString() {
		if(this instanceof HealingAbility) {
			return ((HealingAbility)this).toString();
		}
		else if(this instanceof DamagingAbility) {
			return ((DamagingAbility)this).toString();
		}
		else if(this instanceof CrowdControlAbility) {
			return ((CrowdControlAbility)this).toString();
		}
		return "Name: " + this.getName() + ", " +
		"Mana Cost: " + this.getManaCost() + ", " +
		"Cast Range: " + this.getCastRange() + ", \n" +
		"Base Cool Down: " + this.getBaseCooldown() + ", " +
		"Cast Area: " + this.getCastArea() + ", " +
		"Required Action Points: " + this.getRequiredActionPoints() + ". \n";
	}
	
	public String stringgame() {
		if(this instanceof HealingAbility) {
			return ((HealingAbility)this).stringgame();
		}
		else if(this instanceof DamagingAbility) {
			return ((DamagingAbility)this).stringgame();
		}
		else if(this instanceof CrowdControlAbility) {
			return ((CrowdControlAbility)this).stringgame();
		}
		return "Name: " + this.getName() + ", " +
		"Mana Cost: " + this.getManaCost() + ", " +
		"Cast Range: " + this.getCastRange() + ", \n" +
		"Base Cool Down: " + this.getBaseCooldown() + ", " +
		"Current Cool Down: " + this.getCurrentCooldown() + ", " +
		"Cast Area: " + this.getCastArea() + ", " +
		"Required Action Points: " + this.getRequiredActionPoints() + ". \n";
	}
}
