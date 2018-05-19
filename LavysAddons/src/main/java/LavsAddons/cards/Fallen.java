package LavsAddons.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import basemod.abstracts.CustomCard;

public class Fallen
extends CustomCard {
    public static final String ID = "Fallen";
    public static final String NAME = "Fallen";
    public static final String DESCRIPTION = "If a card is played, discard 3 cards.";
    public static final String IMG_PATH = "LavsAddons/cards/destruction_card.png";

    public Fallen() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION,
        		AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE,
        		AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.NONE);
    }
    
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 3,true));
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if (p.hasRelic("Blue Candle")) {
    		useBlueCandle(p);
    	} else {
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.UseCardAction(this));
    	}
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fallen();
    }

	@Override
	public void upgrade() {}
}