package landlordmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static landlordmod.LandLordThingyMod.makeID;

public class ShelteredPower extends BasePower{
    public static final String POWER_ID = makeID("ShelteredPower");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ShelteredPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        if (amount > 10){
            amount = 10;
        }
    }
    public void reducePower(int reduceAmount) {
        stackPower(-reduceAmount);
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        amount += stackAmount;
        if (amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, ShelteredPower.POWER_ID));
        }
        if (amount >= 10) {
            amount = 10;
        }
    }
    int totalGoldGained = 0;
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount <4 && totalGoldGained < 100){
            addToBot(new GainGoldAction(2));
            totalGoldGained += 2;
        } else if (amount < 8 && totalGoldGained < 100){
            addToBot(new GainGoldAction(3));
            totalGoldGained += 3;
        } else if (amount <= 10 && totalGoldGained < 100){
            addToBot(new GainGoldAction(5));
            totalGoldGained += 5;
        }
        if (amount<10){
            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new ShelteredPower(this.owner, AbstractDungeon.player, 1)));
        }
        if (amount >5){
            addToBot(new ApplyPowerAction(this.owner, AbstractDungeon.player, new StrengthPower(this.owner, 1)));
        }
    }
}
