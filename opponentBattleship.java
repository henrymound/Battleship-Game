public class opponentBattleship extends Ship{

  int[] length = {0, 0, 0, 0, 0};
  
  public opponentBattleship(){
     super(4, 4);
  }
  
  public void hit(int where){
     length[where] = 1;
  }
  
  public boolean isDead(){
    for(int t = 0; t < length.length; t++){
       if (length[t] == 0);
         return false;
    }
     return true;
  }

}