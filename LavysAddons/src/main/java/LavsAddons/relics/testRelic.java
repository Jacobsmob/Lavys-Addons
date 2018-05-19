package LavsAddons.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import LavsAddons.Init;
import LavsAddons.powers.Backfire;

public class testRelic extends CustomRelic {
	public static final String ID = "testRelic";
	public static final String Texture = "LavsAddons/relics/antivirus.png";

	public testRelic() {
		super(
				ID,
			
				Init.getTexture(Texture), 
			
				RelicTier.STARTER, 
			
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
	    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
	    {
	    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(m,AbstractDungeon.player,new Backfire(m,1),1));
	    	
	    }
		
	    //AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Backfire(AbstractDungeon.player,1),1));
	    
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new testRelic();
	}
}