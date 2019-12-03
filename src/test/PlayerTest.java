package test;

import jwrc.board.Property;
import jwrc.board.Sites;
import jwrc.board.Utility;
import jwrc.game.Game;
import jwrc.player.Player;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class PlayerTest {
    @Test
    public void test_payToPlayer_transferOfProperites() {
        Game game = new Game();
        Sites s1 = new Sites("Mediterranean Avenue", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
        Sites s2 = new Sites("Other Ave", 30, 1, "Brown", new int[]{2, 4, 10, 30, 90, 160, 250}, 50);
        Utility u1 = new Utility("Water Works", 75, 28);
        Utility u2 = new Utility("Other Works", 75, 28);

        Player owed = new Player("OWED");
        Player payer = new Player("PAYER");

        Game.playerList = new ArrayList<>();
        Game.playerList.add(payer);
        Game.playerList.add(owed);

        ArrayList<Property> allProp = new ArrayList<>();
        allProp.add(s1);
        allProp.add(u1);
        allProp.add(s2);
        allProp.add(u2);

        owed.addProperty(s1);
        owed.addProperty(u1);
        payer.addProperty(s2);
        payer.addProperty(u2);

        owed.payToPlayer(payer, 1501);

        assertEquals("All properties transferred to owed-player if payer can't pay:", owed.getPropertiesOwned(), allProp);

    }
}
