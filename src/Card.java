public class Card {
    private char frontOfCard;
    Coordinates coordinates;

    public Card(int x, int y, char card){
        coordinates = new Coordinates(x, y);
        frontOfCard = card;
    }

    public char getFrontOfCard(){
        return frontOfCard;
    }
    public void destroyCard(){
        frontOfCard = ' ';
    }

}
