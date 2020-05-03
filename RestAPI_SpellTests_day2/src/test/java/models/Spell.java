package models;

public class Spell {

    private String id;
    private String spell;
    private String type;
    private String effect;

    Boolean isUnforgivable;

    public String getId() {
        return id;
    }

    public void setId(String newValue) {
        id = newValue;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String newValue) {
        spell = newValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String newValue) {
        type = newValue;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String newValue) {
        effect = newValue;
    }

    public Boolean getUnforgivable() {
        return isUnforgivable;
    }

    public void setUnforgivable(Boolean newValue) {
        isUnforgivable = newValue;
    }
}
