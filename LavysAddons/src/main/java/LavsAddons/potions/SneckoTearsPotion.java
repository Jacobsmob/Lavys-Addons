package LavsAddons.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import LavsAddons.powers.fixPowers.ConfusionPowerFake;

public class SneckoTearsPotion extends AbstractPotion {
	public static final String name="Snecko Tears";
	public static final String id ="SneckoTears Potion";
	public static final PotionRarity rarity= PotionRarity.COMMON;
	public static final PotionSize size = PotionSize.SNECKO;
	public static final PotionColor color = PotionColor.SWIFT;
	public SneckoTearsPotion() {
		super(name, id, rarity, size, color);
		this.potency=getPotency();
		this.isThrown=true;
		this.targetRequired = true;
		this.description="Apply #yconfusion onto selected enemy for #b"+this.potency+" turns.";
		this.tips.add(new PowerTip(name, description));
		this.tips.add(new PowerTip("Confusion","Damage is random between 0 and Enemy damage.\nCan hit self and another monster."));
	}

	@Override
	public int getPotency(int ascensionLevel) {
		
		return ascensionLevel < 11?  3 : 1;
	}

	@Override
	public AbstractPotion makeCopy() {
		return new SneckoTearsPotion();
	}

	@Override
	public void use(AbstractCreature target) {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player,new ConfusionPowerFake(target,this.potency,false),this.potency));
	}
}
