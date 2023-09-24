package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

public class RatInfestation extends BaseCard {
    public static final String ID = makeID(RatInfestation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    public RatInfestation() {
        super(ID, info);
        setDamage(8, 3);
        setCustomVar("Invest",3,-1);
        setMagic(4,2);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (!super.canUse(p, m))
            return false;
        if (m != null) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return p.gold >= customVar("Invest") && super.canUse(p,m);
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseGoldAction(customVar("Invest")));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (m.hasPower(ShelteredPower.POWER_ID)){
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RatInfestation();
    }
}