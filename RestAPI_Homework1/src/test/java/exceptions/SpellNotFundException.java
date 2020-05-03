package exceptions;

public class SpellNotFundException extends Exception {

    public SpellNotFundException(String message) {
        //volam konstruktor materskej triedy Exception
        super(message);
    }
}
