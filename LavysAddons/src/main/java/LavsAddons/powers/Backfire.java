package LavsAddons.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.*;

import LavsAddons.Init;
import LavsAddons.powers.fixPowers.ConfusionPowerFake;
import LavsAddons.powers.fixPowers.EntanglePowerFake;

public class Backfire extends AbstractPower {
	public static final String[] DESCRIPTIONS = new String[] {""};
	public static final String image="LavsAddons/powers/backfire.png";
	
	public Backfire(AbstractCreature owner, int amount) {
		this.name = "Backfire";
		this.ID ="lav_backfire";
		this.owner=owner;
		this.amount=amount;
		this.isTurnBased=true;
		this.type=AbstractPower.PowerType.DEBUFF;
		this.img= Init.getTexture(image);
		this.description = "Any #ydebuff is applied to the caster";
		
		if (this.amount >=999) this.amount=999;
	}
	
	@Override
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		power.owner=source;
		if(power.type==AbstractPower.PowerType.DEBUFF && target==AbstractDungeon.player 
				&& power.ID !="Entangled"
				&& power.ID !="Confusion") {
			flash();
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target, source, power.ID));
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(source, target, power, 1,true,com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.NONE));
		}else{
			if(power.ID=="Entangled") {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target, source, power.ID));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(source, target,new EntanglePowerFake(source,1,false)));
			}
			if(power.ID=="Confusion") {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target,source,power.ID));
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(source, target,new ConfusionPowerFake(source,10,true)));
			}
		}
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if(this.amount<=0) {
			AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}else {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner,this.owner,this.ID,1));
		}
	}
}
