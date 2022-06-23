package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}
	
	public String toString() {
		return "Name: " + this.getName() + ", " +
				"Type of Ability: Crowd Control Ability, " +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Effect: " + this.getEffect();
	}
	public String stringgame() {
		return "Name: " + this.getName() + ", \n" +
				"Type of Ability: Crowd Control Ability, \n" +
				"Mana Cost: " + this.getManaCost() + ", " +
				"Cast Range: " + this.getCastRange() + ", \n" +
				"Base Cool Down: " + this.getBaseCooldown() + ", " +
				"Current Cool Down: " + this.getCurrentCooldown() + ", \n" +
				"Cast Area: " + this.getCastArea() + ", " +
				"Required Action Points: " + this.getRequiredActionPoints() + ", \n" + 
				"Effect " + this.getEffect();
	}
}
