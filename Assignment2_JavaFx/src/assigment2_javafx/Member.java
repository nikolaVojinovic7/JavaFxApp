package assigment2_javafx;
/*
	Noah Huboux 101117858
	Nikola Vojinovic 101181089
	Abdurahman Ahmed 101207567
*/
public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private int numGames;
    private int numWins;
    private int numLoss;
    private double winRate;
    
    public Member(int memberId, String firstName, String lastName){
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        numGames = 0;
        numWins = 0;
        numLoss = 0;
        winRate = 0;
    }
    
    public int getMemberId(){
        return memberId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getNumGames(){
        return numGames;
    }
    public int getNumWins(){
        return numWins;
    }
    public int getNumLoss(){
        return numLoss;
    }
    public double getWinRate(){
        return winRate;
    }
    
    public void addWin(){
        numWins++;
        numGames++;
        winRate();
    }
    
    public void addLoss(){
        numLoss++;
        numGames++;
        winRate();
    }
    
    public void winRate(){
        try{
            winRate = (double)numWins/numGames;
        }
        catch(Exception e){
            winRate = 0;
        }        
    }
    
    public String toString(){
        return "Member ID: "+memberId+"\nName: " +firstName+" "+lastName+"\nWin Rate: "+winRate+"\nWins: "+numWins;
    }
}
