public class Player {
    private int triesCount = 0;
    private double time;
    public Coordinates coordinatesOfFristCard = new Coordinates(-1, -1);
    public Coordinates coordinatesOfSecondCard = new Coordinates(-1, -1);

    public void startTimer(){
        time = System.currentTimeMillis();
    }

    public void stopTimer(){
        time = System.currentTimeMillis() - time;
    }


    public int getTriesCount() {
        return triesCount;
    }

    public void increaseTriesCount() {
        this.triesCount++;
    }

    public double getTime() {
        return time;
    }

}
