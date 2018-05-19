package LavsAddons.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import LavsAddons.powers.fixPowers.EntanglePowerFake;

public class NetPotion extends CustomAbstractPotion {
	public static final String name="Net";
	public static final String id ="JarSlime";
	public static final PotionRarity rarity= PotionRarity.RARE;
	public NetPotion() {
		super(name, id, rarity,true);
		this.potency=3;
		this.isThrown=true;
		this.noGlow=true;
		this.targetRequired = true;
		this.description="Gives an enemy 3 #yEntangled.";
		this.tips.add(new PowerTip(name, description));
		this.tips.add(new PowerTip("Slimed","Cannot deal damage."));
	}
	
	@Override
	public int getPotency(int arg0) {
		return 3;
	}
	
	@Override
	public AbstractPotion makeCopy() {
		return new NetPotion();
	}
	@Override
	public void use(AbstractCreature target) {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player,new EntanglePowerFake(target,2,true),this.potency));
	}
	
}
