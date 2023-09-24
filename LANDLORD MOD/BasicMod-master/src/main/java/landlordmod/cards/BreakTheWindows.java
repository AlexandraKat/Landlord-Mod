package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

public class BreakTheWindows extends BaseCard {
    public static final String ID = makeID(BreakTheWindows.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public BreakTheWindows() {
        super(ID, info);
        setDamage(7,2);
        setMagic(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (m.hasPower(ShelteredPower.POWER_ID)){
            for (int i =0; i < m.getPower(ShelteredPower.POWER_ID).amount - 3; i++){
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
            addToBot(new RemoveSpecificPowerAction(m, p, ShelteredPower.POWER_ID));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BreakTheWindows();
    }
}