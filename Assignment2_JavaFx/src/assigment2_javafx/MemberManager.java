package assigment2_javafx;
/*
	Noah Huboux 101117858
	Nikola Vojinovic 101181089
	Abdurahman Ahmed 101207567
*/
public class MemberManager {
    private Member[] allMembers;
    private int nextId;
    private int maxMembers;
    private int numMembers;
    
    public MemberManager(int maxMembers){
        this.maxMembers = maxMembers;
        nextId = 100;
        allMembers = new Member[maxMembers];  
        numMembers = 0;
    }
    
    public boolean addMember(String firstName, String lastName){
         if(numMembers >= maxMembers){   
            return false;
        }
        allMembers[numMembers] = new Member(nextId, firstName, lastName);  
        numMembers = numMembers + 1;
        nextId = nextId + 1;
        return true;
    }
    
    public boolean delMember(int mNum){
        if (numMembers != 0) {
            int mID = findMember(mNum);
            if(mID == -1){
                return false;
            }
            allMembers[mID] = allMembers[numMembers-1];
            allMembers[numMembers-1] = null;
            numMembers = numMembers - 1;
            return true;
        }
	return false;       
    }
    
    public boolean addWin(int mNum){
        if (numMembers != 0) {
            int mID = findMember(mNum);
            if(mID == -1){
                return false;
            }
            allMembers[mID].addWin();
            return true;
        }
	return false;       
    }
    
    public boolean addLoss(int mNum){
        if (numMembers != 0) {
            int mID = findMember(mNum);
            if(mID == -1){
                return false;
            }
            allMembers[mID].addLoss();
            return true;
        }
	return false;       
    }
    
    private int findMember(int mId){
         for(int i = 0; i < allMembers.length ; i++){
            if(allMembers[i] != null && allMembers[i].getMemberId() == mId){
                return i;
            }
        }
        return -1;
    }
    
    public Member[] getMemberList(){
        return allMembers;
    }

    public String bestPlayer(){
        if(numMembers != 0){
            double bestWinRate = 0.0;
            String all = "";
            int count = 0;
            for(int i = 0; i < numMembers; i++){
                if(count == 2 && allMembers[i].getWinRate() == bestWinRate){
                    all += "\netc...";
                }
                if(count < 2 && allMembers[i].getWinRate() == bestWinRate){
                    all += "\n"+allMembers[i].toString();
                    all +="\n---------------------------------";
                    count++;
                }
                if(allMembers[i].getWinRate() > bestWinRate){
                    all = "\n"+allMembers[i].toString();
                    all +="\n---------------------------------";
                    bestWinRate = allMembers[i].getWinRate();
                    count = 1;
                }
            }
            String s = "Best Player(s) (Highest Win Rate)";
            s += "\n---------------------------------";
            s += all;
            return s;
        }
        return "No Members in System";
    }
    
    public String mostWins(){
        if(numMembers != 0){
            int mostWins = 0;
            String all = "";
            int count = 0; 
            for(int i = 0; i < numMembers; i++){
                if(count == 2 && allMembers[i].getNumWins() == mostWins){
                    all += "\netc...";
                }
                if(count < 2 && allMembers[i].getNumWins() == mostWins){
                    all += "\n"+allMembers[i].toString();
                    all +="\n---------------------------------";
                    count ++;
                }
                if(allMembers[i].getNumWins() > mostWins){
                    all = "\n"+allMembers[i].toString();
                    all +="\n---------------------------------";
                    mostWins = allMembers[i].getNumWins();
                    count = 1;
                }
            }
            String s = "Player With Most Wins";
            s += "\n---------------------------------";
            s += all;
            return s;
        }
        return "No Members in System";
    }
    
    public int getNumMembers(){
        return numMembers;
    }
}
