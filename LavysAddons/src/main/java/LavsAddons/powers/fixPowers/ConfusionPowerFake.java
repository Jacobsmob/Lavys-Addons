package LavsAddons.powers.fixPowers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConfusionPowerFake
  extends AbstractPower
{
  public static final String POWER_ID = "Confusion";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Confusion");
  public static final String NAME = powerStrings.NAME;
  public static boolean selfHit=false;
  public static boolean friendHit=false;
  public static boolean damageMade=false;
  public static boolean justApplied=false;
  public ConfusionPowerFake(AbstractCreature owner,int amount,boolean isSourceMonster)
  {
    this.name = NAME;
    this.ID = "Confusion";
    this.owner = owner;
    this.amount=amount;
    updateDescription();
    if(isSourceMonster) {
    	justApplied=true;
    }
    loadRegion("confusion");
    this.type = AbstractPower.PowerType.DEBUFF;
  }
  @Override
  public void updateDescription(){
    this.description =
    		"Damage is random between 0 and enemy damage.\nCan hit self and another monster.";
  }
  @Override
  public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
		damage= MathUtils.random(0,damage);
	  	return damage;
  }
  @Override
  public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
	  DamageInfo editInfo=info;
	  editInfo.base=MathUtils.random(5,info.base*2);
	  if(MathUtils.random(100)<=30 && !selfHit) {
		  AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner,(DamageInfo)editInfo,AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		  flash();
	  }
	  if(MathUtils.random(100)<=70 && !friendHit) {
		  AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getRandomMonster((AbstractMonster)this.owner),(DamageInfo)editInfo,AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		  flash();
	  }
	  selfHit=true;
	  friendHit=true;
  }
  @Override
  public void atStartOfTurn() {
	  selfHit=false;
	  friendHit=false;
  }
  @Override
  public void atEndOfRound() {
	if(this.amount<0) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this.ID));
	}else {
		if(!justApplied) {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner,this.owner,this.ID,1));
		}else {
			justApplied=false;
		}
	}
	damageMade=false;
  }
}