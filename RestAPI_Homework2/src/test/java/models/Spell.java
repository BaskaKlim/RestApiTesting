package models;

import com.fasterxml.jackson.annotation.*;

public class Spell {

    private String id;
    private String spell;
    private String type;
    private String effect;

    Boolean isUnforgivable;

    public Spell() {
    }

    //id neposielam na server, preto nepotrebujem v konstruktory
    public Spell(String spell, String type, String effect, Boolean isUnforgivable) {
        this.spell = spell;
        this.type = type;
        this.effect = effect;
        this.isUnforgivable = isUnforgivable;
    }

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

    @JsonGetter( value = "isUnforgivable")
    public Boolean getUnforgivable() {
        return isUnforgivable;
    }

    public void setUnforgivable(Boolean newValue) {
        isUnforgivable = newValue;
    }
}
