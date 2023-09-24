package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.util.CardStats;

public class DriveBy extends BaseCard {
    public static final String ID = makeID(DriveBy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );


    public DriveBy() {
        super(ID, info);
        setDamage(12,5);
        setCustomVar("Invest",4,-1);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (!super.canUse(p, m))
            return false;
        if (m != null) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return p.gold >= customVar("Invest") && super.canUse(p,m);
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseGoldAction(customVar("Invest")));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DriveBy();
    }
}