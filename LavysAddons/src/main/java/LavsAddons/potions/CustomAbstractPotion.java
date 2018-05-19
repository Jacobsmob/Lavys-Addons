package LavsAddons.potions;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import LavsAddons.Init;

public class CustomAbstractPotion extends AbstractPotion{
	public final String potionResourses="LavsAddons/potions/";
	public boolean notBottle;
	public boolean noGlow=false;
	private ArrayList<String> locations=new ArrayList<String>();
	public CustomAbstractPotion(String name, String id, PotionRarity rarity,boolean notBottle) {
		super(name, id, rarity, PotionSize.BOTTLE, PotionColor.WHITE);
		this.notBottle=notBottle;
		getImages();
	}
	
	public void getImages(){
		String m=this.potionResourses+this.ID+"/";
		this.locations.add(m+"body.png");	//0
		this.locations.add(m+"hybrid.png");	//1
		this.locations.add(m+"liquid.png");	//2
		this.locations.add(m+"outline.png");//3
		this.locations.add(m+"spots.png");	//4
	}
	
	@Override
	public void renderLightOutline(SpriteBatch sb) {
		if(!this.noGlow) {
			sb.setColor(new Color(0.0F,0.0F,0.0F,0.25F));
			sb.draw(Init.getTexture(this.locations.get(3)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		}
	}
	@Override
	public void renderOutline(SpriteBatch sb) {
		if(!this.noGlow) {
			sb.setColor(new Color(0.0F,0.0F,0.0F,0.5F));
			sb.draw(Init.getTexture(this.locations.get(3)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		}
	}
	@Override
	public void renderShiny(SpriteBatch sb) {
		if(!this.noGlow) {
			sb.setBlendFunction(770, 1);
			sb.setColor(new Color(1.0F,1.0F,1.0F,0.1F));
			sb.draw(Init.getTexture(this.locations.get(1)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);;
	
			sb.setBlendFunction(770, 771);
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		if(!notBottle) {
		sb.setColor(this.liquidColor);
		sb.draw(Init.getTexture(this.locations.get(2)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		}
		
		if(this.hybridColor != null && !notBottle) {
			sb.setColor(this.hybridColor);
			sb.draw(Init.getTexture(this.locations.get(1)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		}
		
		if(this.spotsColor!=null && !notBottle) {
			sb.setColor(this.spotsColor);
			sb.draw(Init.getTexture(this.locations.get(4)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		}
		
		sb.setColor(Color.WHITE);
		
		sb.draw(Init.getTexture(this.locations.get(0)),this.posX-32.0F,this.posY-32.0F,32.0F,32.0F,64.0F,64.0F,this.scale,this.scale,0.0F,0,0,64,64,false,false);
		
	}

	@Override
	public int getPotency(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractPotion makeCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(AbstractCreature arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
