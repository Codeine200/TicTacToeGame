package gamecontainer;

/**
 * Created by Василий on 12.06.2015.
 */
public enum GameMessage {
    INFO_SUCCESSFUL_MOVE("The player made successful move"),
    INFO_WINNER("The player won"),

    ERROR_NOT_EMPTY_FIELD("The field is not empty"),
    ERROR_ALREADY_MOVE("The player has already made a move"),
    ;

    private String value;
    GameMessage(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
