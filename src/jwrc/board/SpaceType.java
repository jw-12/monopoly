package jwrc.board;

/**
 * Describes if a BoardSpace instance triggers any special action, and if so what kind.
 *
 * */
public enum SpaceType {

    NO_ACTION, GO_TO_JAIL, PROPERTY, COMMUNITY_CHEST, CHANCE, FREE_PARKING, PENALTY
    //may need multiple property types: prop_owned etc
}
