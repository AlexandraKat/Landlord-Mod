package landlordmod.actions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

//This action gives vigor to the player per card discarded, currently only used on "Dump the Trash"

public class PerDiscard extends AbstractGameAction {
    private final AbstractPlayer p = AbstractDungeon.player;
    private final int cards;
    private final AbstractCard caller;
    private final int vigorPerCard;
    private final boolean anyNumber;

    public PerDiscard(int vigorPerCard, int cardsToDiscard, boolean anyNumber, AbstractCard callingCard) {
        this.vigorPerCard = vigorPerCard;
        this.cards = cardsToDiscard;
        this.anyNumber = anyNumber;
        this.caller = callingCard;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            /*if (!this.anyNumber && p.hand.size() <= cards) {
                for (AbstractCard c : new ArrayList<>(p.hand.group))
                    if (c != caller) {
                        p.hand.moveToDiscardPile(c);
                        addToTop(new ApplyPowerAction(p, p, new VigorPower(p, this.vigorPerCard)));
                    }
                tickDuration();
                return;
            }*/

            AbstractDungeon.handCardSelectScreen.open("discard, up to " + caller.magicNumber + " cards.", cards, true);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                p.hand.moveToDiscardPile(c);
                addToTop(new ApplyPowerAction(p, p, new VigorPower(p, this.vigorPerCard)));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }
 }
