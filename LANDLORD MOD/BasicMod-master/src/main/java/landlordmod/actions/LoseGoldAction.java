package landlordmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import landlordmod.powers.CutTheFundingPower;

//This action reduces the players gold by a specified amount.
//If the player has the power "Cut the Funding" active, the amount gets reduced
public class LoseGoldAction extends AbstractGameAction {
    public LoseGoldAction(int amount) {
        this.amount = amount;
    }

    public void update() {
        if (AbstractDungeon.player.hasPower(CutTheFundingPower.POWER_ID)){
            int finalAmount = this.amount - AbstractDungeon.player.getPower(CutTheFundingPower.POWER_ID).amount;
            if (finalAmount < 0) {
                finalAmount = 0;
            }
            AbstractDungeon.player.loseGold(finalAmount);
            } else {
                AbstractDungeon.player.loseGold(this.amount);
            }
        this.isDone = true;
    }
}
