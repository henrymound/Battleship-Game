public class opponentRow extends Ship{

  int[] length = {0, 0, 0, 0, 0};
  
  public opponentRow(){
     super(2, 2);
     setPosition("Your opponents");
     setName("Row Boat");
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