package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import landlordmod.Landlord;
import landlordmod.actions.LoseGoldAction;
import landlordmod.util.CardStats;

public class IOwnThisPlace extends BaseCard {
    public static final String ID = makeID(IOwnThisPlace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private AbstractMonster m;

    public IOwnThisPlace() {
        super(ID, info);
        setCustomVar("Invest",2,-1);
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
        if (m != null && (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND)){
            addToBot(new LoseGoldAction(this.customVar("Invest")));
            if (!this.upgraded){
                addToBot(new DamageAction(m, new DamageInfo(p, m.getIntentDmg(), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            } else {
                addToBot(new LoseHPAction(m, p, m.getIntentDmg()));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new IOwnThisPlace();
    }
}