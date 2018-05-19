package LavsAddons.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.PotionHelper;


@SpirePatch(
		cls="com.megacrit.cardcrawl.helpers.PotionHelper",
		method="initialize"
)

public class addPotion {
	public static void Postfix(AbstractPlayer.PlayerClass chosenClass) {
		PotionHelper.potions.add("SneckoTears Potion");
		PotionHelper.potions.add("JarSlime");
	}
}
