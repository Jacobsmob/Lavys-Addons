package LavsAddons;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
//import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;

//import basemod.interfaces.EditCharactersSubscriber;



import LavsAddons.cards.Backfire;
//import basemod.helpers.*;
import LavsAddons.cards.Fallen;
import LavsAddons.potions.NetPotion;
import LavsAddons.potions.SneckoTearsPotion;
import LavsAddons.relics.Antivirus;
//import LavsAddons.relics.testRelic;


@SpireInitializer
public class Init implements PostInitializeSubscriber,EditCardsSubscriber, EditRelicsSubscriber, 
EditStringsSubscriber, EditKeywordsSubscriber {

	public Init() {
		BaseMod.subscribe(this);
	}
	
	public static Texture getTexture(String string) {
		return new Texture(string);
    }
	
    public static void initialize() {
        new Init();
    }
	
	@Override
	public void receiveEditStrings() {
		String relicStrings = Gdx.files.internal("LavsAddons/localization/relics.json").readString(
				String.valueOf(StandardCharsets.UTF_8));
		
		String cardStrings = Gdx.files.internal("LavsAddons/localization/cards.json").readString(
				String.valueOf(StandardCharsets.UTF_8));
		
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
		BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		
	}
	public static final String MODNAME ="Lavy's Addons";
	public static final String AUTHOR  ="Lavender";
	public static final String DESCRIPTION="Lavy's Addons is in develpoment but plans to add a bunch of cards, relics, potions, ect to Slay the Spire.";
	@Override
	public void receivePostInitialize() {
		Texture badge= new Texture("LavsAddons/badge/badge.png");
		ModPanel panel = new ModPanel();
		BaseMod.registerModBadge(badge, 
/*Mod Name*/	MODNAME,
/*Developer*/	AUTHOR,
/*Description*/	DESCRIPTION,
/*Config Panel*/panel);
		
	}
	@SuppressWarnings("rawtypes")
	public HashMap<String,Class> potions;
	@SuppressWarnings("rawtypes")
	public void addPotionToArray(String id, Class cls) {
		potions.put("NetPotion", NetPotion.class);
		potions.put("SneckoTearsPotion", SneckoTearsPotion.class);
	}
	
	@Override
	public void receiveEditCards(){
		BaseMod.addCard(new Fallen());
		BaseMod.addCard(new Backfire());
		
	}
	
	@Override
	public void receiveEditRelics() {
		RelicLibrary.addBlue(new Antivirus());
		//RelicLibrary.add(new testRelic());
	}

	@Override
	public void receiveEditKeywords() {
		BaseMod.addKeyword(new String[] {"Backfire","backfire"},"Any #ydebuff is applied to the caster");
		
	}
}
