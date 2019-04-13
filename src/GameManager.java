import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    int sizeX;
    int sizeY;
    private char cardBack = 'X';
    ArrayList<ArrayList<Card>> board = new ArrayList<>();
    ArrayList<Character> available = new ArrayList<>();
    Player player = new Player();
    int remainingCouples = 0;


    public GameManager(int x, int y){
        setSize(x, y);
        fillAvailableCharacters();
        createBoard();
        remainingCouples = (sizeX * sizeY) / 2;
    }

    private void getCoordinatesFromUser(){
        String[] coordinates;
        Scanner scn =  new Scanner(System.in);

        System.out.print("Coordinates of first card as x,y : ");
        coordinates = scn.nextLine().trim().split(",");

        player.coordinatesOfFristCard.x = Integer.parseInt(coordinates[0]);
        player.coordinatesOfFristCard.y = Integer.parseInt(coordinates[1]);

        System.out.print("Coordinates of second card as x,y : ");
        coordinates = scn.nextLine().trim().split(",");

        player.coordinatesOfSecondCard.x = Integer.parseInt(coordinates[0]);
        player.coordinatesOfSecondCard.y = Integer.parseInt(coordinates[1]);
    }

    public void startGame(){
        player.startTimer();

        while(remainingCouples != 0){
            printcardBacks();
            getCoordinatesFromUser();
            printSelectedCards();

            try{
                Thread.sleep(3500);
            }catch(Exception ex){
                System.out.print("Couldn't sleep, continuing");
            }

            matchCheck();
            player.increaseTriesCount();
            //clear screen
            for(int i = 0; i < 50; i++)
                System.out.print("\n");
        }
        player.stopTimer();
        System.out.printf("!Congratulations!\n\n Total time: %.2f\n Total turns: %d", player.getTime() / 1000, player.getTriesCount());
    }

    private void matchCheck(){
        if(board.get(player.coordinatesOfFristCard.y).get(player.coordinatesOfFristCard.x).getFrontOfCard() ==
                board.get(player.coordinatesOfSecondCard.y).get(player.coordinatesOfSecondCard.x).getFrontOfCard()){
            board.get(player.coordinatesOfFristCard.y).get(player.coordinatesOfFristCard.x).destroyCard();
            board.get(player.coordinatesOfSecondCard.y).get(player.coordinatesOfSecondCard.x).destroyCard();
            remainingCouples--;
        }

    }

    private void printcardBacks(){
        System.out.print("  ");
        for(int i = 0; i < sizeX; i++)
            System.out.printf("%d ", i);
        System.out.println();

        for(int i = 0; i < sizeY; i++){
            System.out.printf("%d ", i);
            for(int j = 0; j < sizeX; j++){
                if(board.get(i).get(j).getFrontOfCard() == ' ')
                    System.out.print("  ");
                else
                    System.out.printf("%c ", cardBack);
            }
            System.out.println();
        }
    }

    private void printSelectedCards(){
        System.out.print("  ");
        for(int i = 0; i < sizeX; i++)
            System.out.printf("%d ", i);
        System.out.println();

        for(int i = 0; i < sizeY; i++){
            System.out.printf("%d ", i);
            for(int j = 0; j < sizeX; j++){

                if(board.get(i).get(j).getFrontOfCard() == ' ')
                    System.out.print("  ");
                else if(player.coordinatesOfFristCard.x == j && player.coordinatesOfFristCard.y == i
                || player.coordinatesOfSecondCard.x == j && player.coordinatesOfSecondCard.y == i)
                    System.out.printf("%c ", board.get(i).get(j).getFrontOfCard());
                else
                    System.out.printf("%c ", cardBack);

            }
            System.out.println();
        }
    }

    //Method to make sure there are dual counts of cards in the board
    private void setSize(int sizeX, int sizeY){
        if((sizeX * sizeY) % 2 != 0){
            sizeX++;
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;

    }

    private void fillAvailableCharacters(){
        int characterCount = (sizeX * sizeY) / 2;

        for(int i = 0; i < characterCount; i++){
            for(int j = 0; j < 2; j++){ //Duplicating each letter to have something to match
                available.add((char) ('a' + i));
            }
        }
    }

    private void createBoard(){
        int randomIndex;
        Random r = new Random();
        board.add(new ArrayList<>());
        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){
                randomIndex = r.nextInt(available.size());
                Card card = new Card(j, i, available.get(randomIndex));
                board.get(i).add(card);
                available.remove(randomIndex);
            }
            board.add(new ArrayList<>());
        }
    }

    public char getCardBack() {
        return cardBack;
    }

    public void setCardBack(char cardBack) {
        this.cardBack = cardBack;
    }
}
