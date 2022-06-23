package model.world;

import java.awt.Point;
import java.util.ArrayList;


import model.abilities.Ability;
import model.effects.Effect;

@SuppressWarnings("rawtypes")
public abstract class Champion implements Damageable,Comparable {
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	
	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints=maxActionPointsPerTurn;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp <= 0) {
			currentHP = 0;
			condition=Condition.KNOCKEDOUT;
			
		} 
		else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}

	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if(currentActionPoints>maxActionPointsPerTurn)
			currentActionPoints=maxActionPointsPerTurn;
		else 
			if(currentActionPoints<0)
			currentActionPoints=0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int compareTo(Object o)
	{
		Champion c = (Champion)o;
		if(speed==c.speed)
			return name.compareTo(c.name);
		return -1 * (speed-c.speed);
	}
	
	public abstract void useLeaderAbility(ArrayList<Champion> targets);
	
	public String stringgame() {
		if(this instanceof Hero) {
			return ((Hero)this).stringgame();
		}
		else if(this instanceof AntiHero) {
			return ((AntiHero)this).stringgame();
		}
		else if(this instanceof Villain) {
			return ((Villain)this).stringgame();
		}
		String string;
		string = "Name: " + this.getName() + ", " +
				"Health Points: "+this.getCurrentHP() + " / " +this.getMaxHP() + ", " + 
				"Mana: "+this.getMana() + ", \n" + 
				"Actions Left: "+ this.getCurrentActionPoints() + ", " +
				"Attack Range: "+ this.getAttackRange() + ", " + 
				"Attack Damage: "+ this.getAttackDamage() + ", \n" +
				"Abilities: \n";
		for(int i=0;i<this.getAbilities().size();i++) {
			string += "Ability " + (i+1) + ": ";
			string += this.getAbilities().get(i).stringgame();
			if(i<this.getAbilities().size()-1) {
				string += ", \n";
			} else {
				string +=". \n";
			}
		}
		for(int i=0;i<this.getAppliedEffects().size();i++) {
			string += "Effect " + (i+1) + ": ";
			string += this.getAppliedEffects().get(i).stringgame();
			if(i<this.getAppliedEffects().size()-1) {
				string += ", \n";
			} else {
				string +=". \n";
			}
		}
		return string;
	}
	public String stringremaining() {
		if(this instanceof Hero) {
			return ((Hero)this).stringremaining();
		}
		else if(this instanceof AntiHero) {
			return ((AntiHero)this).stringremaining();
		}
		else if(this instanceof Villain) {
			return ((Villain)this).stringremaining();
		}
		String string;
		string = "Name: " + this.getName() + ", \n" + 
				"Health Points: "+this.getCurrentHP() + " / " +this.getMaxHP() + ", " + 
				"Mana: "+ this.getMana() + ", \n" + 
				"Number of Actions: "+ this.getMaxActionPointsPerTurn() + ", " + 
				"Speed: "+ this.getSpeed() + ", " + 
				"Attack Range: "+ this.getAttackRange() + ", " + 
				"Attack Damage: "+ this.getAttackDamage() + ", \n" +
				"Applied Effects: \n";
		for(int i=0;i<this.getAppliedEffects().size();i++) {
			string += "Effect " + (i+1) + ": ";
			string += this.getAppliedEffects().get(i).stringgame();
			if(i<this.getAppliedEffects().size()-1) {
				string += ", \n";
			} else {
				string +=". \n";
			}
		}
		return string;
	}
}
