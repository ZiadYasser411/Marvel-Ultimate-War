package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {
			int i = 0;
			while (i < c.getAppliedEffects().size()) {
				Effect e = c.getAppliedEffects().get(i);
				if (e.getType() == EffectType.DEBUFF) {
					e.remove(c);
					c.getAppliedEffects().remove(e);
				} else
					i++;
			}
			Embrace em = new Embrace(2);
			em.apply(c);
			c.getAppliedEffects().add(em);
		}
	}
	
	public String toString() {
		String string;
		string = "Name: " + this.getName() + ", " + 
				"Hero" + ", " +
				"Maximum Health Points: "+ this.getMaxHP() + ", " + 
				"Maximum Mana: "+ this.getMana() + ", \n" + 
				"Number of Actions: "+ this.getMaxActionPointsPerTurn() + ", " + 
				"Speed: "+ this.getSpeed() + ", " + 
				"Attack Range: "+ this.getAttackRange() + ", " + 
				"Attack Damage: "+ this.getAttackDamage() + ", \n" +
				"Abilities: \n";
		for(int i=0;i<this.getAbilities().size();i++) {
			string += "Ability " + (i+1) + ": ";
			string += this.getAbilities().get(i).toString();
			if(i<this.getAbilities().size()-1) {
				string += ", \n";
			} else {
				string +=". \n";
			}
		}
		return string;
	}
	public String stringgame() {
		String string;
		string = "Name: " + this.getName() + ", " + 
				"Hero" + ", " +
				"Health Points: "+this.getCurrentHP() + " / " +this.getMaxHP() + ", \n" + 
				"Mana: "+this.getMana() + ", " + 
				"Actions Left: "+ this.getCurrentActionPoints() + ", \n" +
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
		string += "Applied Effects: \n";
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
		String string;
		string = "Name: " + this.getName() + ", \n" + 
				"Hero" + ", \n" +
				"Health Points: "+this.getCurrentHP() + " / " +this.getMaxHP() + ", \n" + 
				"Mana: "+ this.getMana() + ", \n" + 
				"Number of Actions: "+ this.getMaxActionPointsPerTurn() + ", \n" + 
				"Speed: "+ this.getSpeed() + ", \n" + 
				"Attack Range: "+ this.getAttackRange() + ", \n" + 
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

