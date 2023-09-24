package landlordmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import landlordmod.Landlord;
import landlordmod.util.CardStats;

public class StainOnShirt extends BaseCard {
    public static final String ID = makeID(StainOnShirt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Landlord.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public StainOnShirt() {
        super(ID, info);
        setMagic(3,2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MarkPower(m, this.magicNumber)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower mark = m.getPower(MarkPower.POWER_ID);
                addToTop(new GainBlockAction(p, mark != null ? mark.amount : 0));
                isDone = true;
            }
        });
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StainOnShirt();
    }
}