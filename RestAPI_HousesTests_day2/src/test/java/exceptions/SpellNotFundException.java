package exceptions;

public class SpellNotFundException extends Exception {

    public SpellNotFundException(String message) {
        //volam konstruktor materskej triedy Exception
        //pouzijem placeholder %s a prihodim message
        super(String.format("Spell '%s' not found in list of spells", message));
    }
}
