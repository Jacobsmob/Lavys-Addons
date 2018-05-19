package LavsAddons.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import LavsAddons.potions.NetPotion;
import LavsAddons.potions.SneckoTearsPotion;

@SpirePatch(
		cls="com.megacrit.cardcrawl.helpers.PotionHelper",
		method="getPotion"
)

public class getPotion {
	public static Object Postfix(Object __result,String name) {
		if((__result==null)||(__result.equals(""))) {
			return null;
		}
		
		switch (name) {
			case "SneckoTears Potion":
				return new SneckoTearsPotion();
			case "JarSlime":
				return new NetPotion();
		}
		return __result;
	}
}