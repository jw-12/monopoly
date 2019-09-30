package jwrc.game;

public enum Options {
    E_TO_END("e"),  // end turn
    H_TO_BUILD("h"),  // build houses etc
    B_TO_BUY("b"),  // buy a property
    S_TO_SELL("s"),  // sell property
    P_TO_PASS("p"),  // pass on a property THIS MAY NOT BE NEEDED
    R_TO_ROLL("r")
    ;

    public final String keyIn;

    private Options(String keyIn) {
        this.keyIn = keyIn;
    }
}
