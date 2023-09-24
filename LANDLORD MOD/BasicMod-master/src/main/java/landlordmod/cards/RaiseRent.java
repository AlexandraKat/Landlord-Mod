package landlordmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

public class RaiseRent extends BaseCard {
    public static final String ID = makeID(RaiseRent.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public RaiseRent() {
        super(ID, info);
        setExhaust(true);
        setCostUpgrade(0);
        setCustomVar("Invest",1);
        setMagic(2);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (p.gold < customVar("Invest")) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int shelteredEnemyAmount=0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(mo.hasPower(ShelteredPower.POWER_ID)){
                shelteredEnemyAmount++;
            }
        }
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, shelteredEnemyAmount*this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, shelteredEnemyAmount*this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RaiseRent();
    }
}