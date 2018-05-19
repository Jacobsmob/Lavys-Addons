package LavsAddons.powers.fixPowers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EntanglePowerFake
 extends AbstractPower{
	public boolean justApplied=false;
  public EntanglePowerFake(AbstractCreature owner,int amount,boolean justApplied)
  {
	this.name = "Entagled";
	loadRegion("entangle");
	this.justApplied=justApplied;
    this.ID = "EntangledFake";
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.isTurnBased = true;
    this.type = AbstractPower.PowerType.DEBUFF;
  }
  @Override
  public float atDamageGive(float damage, DamageInfo.DamageType type) {
	  damage=0;
	  return damage;
  }
  @Override
  public void updateDescription()
  {
    this.description ="Cannot deal damage.";
  }
  @Override
  public void atEndOfTurn(boolean isPlayer)
  {
    if (!isPlayer && amount<=0 && !justApplied) {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "EntangledFake"));
    }else {
    	if(!justApplied) {
    		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner,"EntangledFake", 1));
    	}else {
    		justApplied=false;
    	}
    }
  }
}
