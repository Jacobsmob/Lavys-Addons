package LavsAddons.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class Backfire 
extends CustomCard {
    public static final String id = "Backfire";
    public static final String name = "Backfire";
    public static final String RawDescription = "Apply 1 Backfire onto all enemies.";
    public static final String img = "LavsAddons/cards/Backfire.png";
    public static final CardType type= CardType.SKILL;
    public static final CardColor color = CardColor.COLORLESS;
    public static final CardRarity rarity= CardRarity.UNCOMMON;
    public static final CardTarget target = CardTarget.ALL_ENEMY;
    public static final int costRaw = 2;
	
	
	public Backfire() {
		super(id, name, img, costRaw, RawDescription, type, color, rarity, target);
		this.price=60;
		
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.rawDescription="Apply 2 Backfire onto all enemies.";
			upgradeBaseCost(1);
			initializeDescription();
		}
	}


	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {
		int effect=1;
		if(this.upgraded) {
			effect=2;
		}
	    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
	    {
	    	AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(m,AbstractDungeon.player,new LavsAddons.powers.Backfire(m,effect),effect));
	    	
	    }
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Backfire();
	}

}
