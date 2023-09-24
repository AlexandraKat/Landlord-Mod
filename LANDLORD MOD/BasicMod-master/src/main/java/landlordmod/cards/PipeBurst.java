package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.powers.ShelteredPower;
import landlordmod.util.CardStats;

public class PipeBurst extends BaseCard {
    public static final String ID = makeID(PipeBurst.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public PipeBurst() {
        super(ID, info);
        setCustomVar("Invest", 1);
        setMagic(2, 1);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m) || m==null)
            return false;
        if(p.gold < customVar("Invest")) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        if(!m.hasPower(ShelteredPower.POWER_ID)) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, m.getPower(ShelteredPower.POWER_ID).amount * this.magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PipeBurst();
    }
}