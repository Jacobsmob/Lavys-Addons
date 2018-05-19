package LavsAddons.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import LavsAddons.Init;

public class Antivirus extends CustomRelic {
	public static final String ID = "Antivirus";
	public static final String Texture = "LavsAddons/relics/antivirus.png";

	public Antivirus() {
		super(
				ID,
			
				Init.getTexture(Texture), 
			
				RelicTier.SHOP, 
			
				LandingSound.MAGICAL
		);
		this.cost = 99;
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	@Override	
	public void atBattleStart() {
	    AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new com.megacrit.cardcrawl.powers.FocusPower(AbstractDungeon.player,2),2));
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Antivirus();
	}
}
	
