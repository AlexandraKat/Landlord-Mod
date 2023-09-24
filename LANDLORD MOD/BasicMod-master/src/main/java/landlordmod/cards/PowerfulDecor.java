package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

public class PowerfulDecor extends BaseCard {
    public static final String ID = makeID(PowerfulDecor.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public PowerfulDecor() {
        super(ID, info);
        setMagic(4,2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(ShelteredPower.POWER_ID)){
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
                addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber)));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PowerfulDecor();
    }
}