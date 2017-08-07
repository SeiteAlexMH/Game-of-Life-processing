Cell[] CellArray;
int canvasSize = 800;
int arrayWidth = 50;
int cellSize =floor(canvasSize/arrayWidth);
int arrayLength = arrayWidth * arrayWidth;
boolean paused = true;

void setup() {
  size (801,801);
  colorMode(HSB, 360, 100, 100, 100);
  CellArray = new Cell[arrayLength];
  for (int i=0; i<arrayLength; i++){
    int x = (i%arrayWidth) * cellSize;
    int y = floor(i/arrayWidth)*cellSize;
    CellArray[i] = new Cell(x,y,i);
    CellArray[i].updateFlag= (int)random(2);
    CellArray[i].paint();
    //noLoop();
  }
}

void draw() {
  if(!paused){
    background(0,0,100);
    for(int i=0; i<arrayLength; i++){
      CellArray[i].paint();
    }
    for(int i=0; i<arrayLength; i++){
      CellArray[i].update();
    }
  }
}

class Cell{
  int x,y;
  int cellNum;
  int lifeFlag = 0;
  int updateFlag = 0;
  int neighborCount = 0;
  
  Cell(int InputX, int InputY, int inputCellNum){
    x = InputX;
    y = InputY;
    cellNum = inputCellNum;
    lifeFlag = 0;
  }
  
  void paint(){
    lifeFlag = updateFlag;
    if(lifeFlag == 1) {
      stroke(0,0,100);
      fill(0,0,0);
    } else {
      stroke(0,0,0);
      fill(0,0,100);
    }
    rect(x,y,cellSize,cellSize);
  }
  
  void update(){
    if(cellNum<arrayWidth){
      if(cellNum == 0){
        neighborCount = CellArray[1].lifeFlag 
                      + CellArray[arrayWidth].lifeFlag 
                      + CellArray[arrayWidth+1].lifeFlag 
                      + CellArray[arrayWidth-1].lifeFlag 
                      + CellArray[2*arrayWidth-1].lifeFlag 
                      + CellArray[arrayLength-1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)+1].lifeFlag;
      } else if(cellNum == (arrayWidth-1)){
        neighborCount = CellArray[0].lifeFlag 
                      + CellArray[arrayWidth-2].lifeFlag 
                      + CellArray[2*arrayWidth-2].lifeFlag 
                      + CellArray[2*arrayWidth-1].lifeFlag 
                      + CellArray[arrayWidth].lifeFlag 
                      + CellArray[arrayLength-1].lifeFlag 
                      + CellArray[arrayLength-2].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)].lifeFlag;
      } else {
        neighborCount = CellArray[cellNum-1].lifeFlag 
                        + CellArray[cellNum+1].lifeFlag 
                        + CellArray[cellNum+(arrayWidth+1)].lifeFlag 
                        + CellArray[cellNum+arrayWidth].lifeFlag 
                        + CellArray[cellNum+(arrayWidth-1)].lifeFlag 
                        + CellArray[cellNum+(arrayWidth*(arrayWidth-1)-1)].lifeFlag 
                        + CellArray[cellNum+(arrayWidth*(arrayWidth-1))].lifeFlag 
                        + CellArray[cellNum+(arrayWidth*(arrayWidth-1)+1)].lifeFlag;
      }
    } else if(cellNum>(arrayWidth*(arrayWidth-1)-1)){
      if(cellNum == (arrayWidth*(arrayWidth-1))){
        neighborCount = CellArray[0].lifeFlag 
                      + CellArray[arrayWidth-1].lifeFlag 
                      + CellArray[1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)+1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)-1].lifeFlag 
                      + CellArray[arrayLength-1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-2)].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-2)+1].lifeFlag;
      } else if(cellNum == arrayLength-1){
        neighborCount = CellArray[0].lifeFlag 
                      + CellArray[arrayWidth-2].lifeFlag 
                      + CellArray[arrayWidth-1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)-1].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-2)].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)-2].lifeFlag 
                      + CellArray[arrayWidth*(arrayWidth-1)].lifeFlag 
                      + CellArray[arrayLength-2].lifeFlag;
      } else {
        neighborCount = CellArray[cellNum-(arrayWidth+1)].lifeFlag 
                        + CellArray[cellNum-arrayWidth].lifeFlag 
                        + CellArray[cellNum-(arrayWidth-1)].lifeFlag 
                        + CellArray[cellNum-1].lifeFlag 
                        + CellArray[cellNum+1].lifeFlag 
                        + CellArray[cellNum-(arrayWidth*(arrayWidth-1)-1)].lifeFlag 
                        + CellArray[cellNum-(arrayWidth*(arrayWidth-1))].lifeFlag 
                        + CellArray[cellNum-(arrayWidth*(arrayWidth-1)+1)].lifeFlag;
      }
    } else if(cellNum%arrayWidth==0){
      neighborCount = CellArray[cellNum+(arrayWidth*2-1)].lifeFlag 
                      + CellArray[cellNum-arrayWidth].lifeFlag 
                      + CellArray[cellNum-(arrayWidth-1)].lifeFlag 
                      + CellArray[cellNum-1].lifeFlag 
                      + CellArray[cellNum+1].lifeFlag 
                      + CellArray[cellNum+(arrayWidth+1)].lifeFlag 
                      + CellArray[cellNum+arrayWidth].lifeFlag 
                      + CellArray[cellNum+(arrayWidth-1)].lifeFlag;
    } else if(cellNum%arrayWidth==(arrayWidth-1)){
      neighborCount = CellArray[cellNum-(arrayWidth+1)].lifeFlag 
                      + CellArray[cellNum-arrayWidth].lifeFlag 
                      + CellArray[cellNum-(arrayWidth-1)].lifeFlag 
                      + CellArray[cellNum-1].lifeFlag 
                      + CellArray[cellNum-(2*arrayWidth-1)].lifeFlag 
                      + CellArray[cellNum+1].lifeFlag 
                      + CellArray[cellNum+arrayWidth].lifeFlag 
                      + CellArray[cellNum+(arrayWidth-1)].lifeFlag;
    } else {
      neighborCount = CellArray[cellNum-(arrayWidth+1)].lifeFlag 
                      + CellArray[cellNum-arrayWidth].lifeFlag 
                      + CellArray[cellNum-(arrayWidth-1)].lifeFlag 
                      + CellArray[cellNum-1].lifeFlag 
                      + CellArray[cellNum+1].lifeFlag 
                      + CellArray[cellNum+(arrayWidth+1)].lifeFlag 
                      + CellArray[cellNum+arrayWidth].lifeFlag 
                      + CellArray[cellNum+(arrayWidth-1)].lifeFlag;
    }

    if(lifeFlag == 1){
      if(neighborCount == 2 || neighborCount == 3){
        updateFlag=1;
      } else {
        updateFlag = 0;
      }
    } else if(neighborCount == 3){
      updateFlag = 1;
    } else {
      updateFlag = 0;
    }
  }
}

void keyPressed() {
  if(key == 'p'){
    paused = !paused;
  }
  if(key == 'c'){
    if(paused){
      for(int i=0; i<arrayLength; i++){
        CellArray[i].updateFlag = 0;
        CellArray[i].paint();
      }
    }
  }
  if(key == 'r'){
    if(paused){
      for(int i=0; i<arrayLength; i++){
        CellArray[i].updateFlag = (int)random(2);
        CellArray[i].paint();
      }
    }
  }
  if(key == ' '){
    if(paused){
      for(int i=0; i<arrayLength; i++){
        CellArray[i].paint();
      }
      for(int i=0; i<arrayLength; i++){
        CellArray[i].update();
      }
    }
  }
}

void mouseClicked() {
  if(paused){
    int cellClicked = floor(mouseX/cellSize) + arrayWidth*(mouseY/cellSize);
    if(CellArray[cellClicked].lifeFlag == 1){
      CellArray[cellClicked].updateFlag = 0;
    } else {
      CellArray[cellClicked].updateFlag = 1;
    }
    CellArray[cellClicked].paint();
  }
}
