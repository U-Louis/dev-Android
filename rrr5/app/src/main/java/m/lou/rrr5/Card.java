package m.lou.rrr5;

public class Card {

    private String name;
    private Boolean empty;

    public Card(String name, Boolean empty){
        this.name = name;
        this.empty = empty;
    }

    /* SETTERS */
    public void setName(String name){
        this.name = name;
    }

    public void setEmpty(Boolean empty){
        this.empty = empty;
    }

    /* GETTERS */
    public String getName(){
        return name;
    }

    public Boolean getEmpty(){
        return empty;
    }

}
