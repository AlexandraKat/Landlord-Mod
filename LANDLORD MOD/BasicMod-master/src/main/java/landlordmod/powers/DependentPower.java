package landlordmod.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

import static landlordmod.LandLordThingyMod.makeID;

public class DependentPower extends BasePower{
    public static final String POWER_ID = makeID("DependentPower");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public DependentPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = -1;
    }
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
    public void checkBlock(){
        if (this.owner.currentBlock == 0){
            addToTop(new RemoveSpecificPowerAction(this.owner, this.source, BarricadePower.POWER_ID));
            addToTop(new RemoveSpecificPowerAction(this.owner, this.source, this));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        checkBlock();
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onRemove() {
        super.onRemove();
        addToTop(new StunMonsterAction((AbstractMonster) this.owner,this.source,1));
    }
}
