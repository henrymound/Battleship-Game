public class Ship{
  
  public int health;
  public int healthCapacity;
  int[] xCoords = null;
  int[] yCoords = null;
  
  public Ship(int current, int max){
     health = current;
     healthCapacity = max;
     
     xCoords = new int[max];
     yCoords = new int[max];
     
     for(int c = 0; c < max; c++){
        xCoords[c] = 0; yCoords[c] = 0;
     }
  }
  
  public int getHealth(){return health;}
  
  public int getHealthCapacity(){return healthCapacity;}
  
  public boolean ifHit(int x, int y){
    for(int z = 0; z < xCoords.length; z++){
      if((xCoords[z] == x) && (yCoords[z] == y)){
         xCoords[z] = 1;
         yCoords[z] = 1;
         refreshLife();
         return true;
      }
    }
    return false;
  }
  
  public void refreshLife(){
     int h = healthCapacity;
     for(int z = 0; z < xCoords.length; z++){
       if(xCoords[z] == 1 && yCoords[z] == 1){
          h--;
       }
    }
    health = h;
  }
  
  public void coords(int[] xcoords, int[] ycoords){
    for(int i = 0; i < xcoords.length; i++){
       xCoords[i] = xcoords[i];
    }
    for(int j = 0; j < ycoords.length; j++){
       yCoords[j] = ycoords[j];
    }
  }
  
}