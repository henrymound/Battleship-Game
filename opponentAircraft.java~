public class opponentAircraft extends Ship{

  int[] length = {0, 0, 0, 0, 0};
  
  public opponentAircraft(){
     super(5, 5);
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