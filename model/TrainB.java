/* ***************************************************************
* Autor............: Luan Pinheiro Azevedo
* Nome.............: Problema de Exclusao Mutua
* Funcao...........: Simulação do problema dos Trems com thread na linguagem java, possuíndo GUI.
*************************************************************** */
package model;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import controller.ControllerTrains;

public class TrainB extends Thread {
  private int posiEixoX;
  private int posiEixoY;
  private ControllerTrains cT;
  private int speed;

  public int realTimeSpeedTrainB() {
    speed= cT.getSpeedB();

    switch(speed) {
        case 0: 
            speed = 10;
            break;
        case 1: 
            speed = 5;
            break;
        case 2: 
            speed = 2;
            break;
        default:
            System.out.println("Não foi possivel atribuir");
            break;
    }
    return speed;
  }

  public void linkController(ControllerTrains cT){
    this.cT = cT;
  }

  public void moveToRight(int stopPoint){
    while(posiEixoX <= stopPoint){
      try {
        Platform.runLater(()->cT.getImgTremB().setX(posiEixoX));
        posiEixoX++;
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  public void moveDown(ImageView img,int stopPoint){
    while(posiEixoY<=stopPoint){
      try {
        Platform.runLater(()->img.setY(posiEixoY));
        posiEixoY++;
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  public void moveUp(ImageView img,int stopPoint){
    while(posiEixoY >= stopPoint){
      try {
        Platform.runLater(()->img.setY(posiEixoY));
        posiEixoY--;
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void moveToLeft(int stopPoint){
    while(posiEixoX >= stopPoint){
      try {
        Platform.runLater(()->cT.getImgTremBrev().setX(posiEixoX));
        posiEixoX--;
        sleep(realTimeSpeedTrainB());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void leftToRight(ImageView img){
    //Fazendo o Trem A aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(cT.getImgTremB().getX());
    posiEixoY = (int)(cT.getImgTremB().getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      moveToRight(95);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVT1()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem B esperando vez para Zona Critica [1]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT1(1);
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      cT.setVT1(0);
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVT2()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem B esperando vez para Zona Critica [2]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT2(1);
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      cT.setVT2(0);
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }

  public void RightToLeft(ImageView img){
    //Fazendo o Trem A aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
  
  public void leftToRightEA(ImageView img){
    System.out.println("entrouLREA");
    //Fazendo o Trem A aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(cT.getImgTremB().getX());
    posiEixoY = (int)(cT.getImgTremB().getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      moveToRight(95);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVEZ1()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      cT.setVEZ1(0);
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVEZ2()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      cT.setVEZ2(0);
      Platform.runLater(()->img.setRotate(90));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }

  public void RightToLeftEA(ImageView img){
    System.out.println("entrouRLEA");
    //Fazendo o Trem A aparecer na tela e definindo suas posições, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      cT.setVEZ2(1);
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVEZ2()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      cT.setVEZ2(0);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVEZ1()!=1){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveUp(img,-50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      cT.setVEZ1(0);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }

   public void selectedMutualExclusionAndOrientation(){
    switch(cT.getTypeOfMutualExclusion()){
      case 1:
        System.out.println("Estrita Alternancia selecionada como solucao!");
        switch(cT.getPosiOrientacao()){
          case 0:
            leftToRightEA(cT.getImgTremB());
            break;
          case 1:
            RightToLeftEA(cT.getImgTremBrev());
            break;
          case 2:
            RightToLeftEA(cT.getImgTremBrev());
            break;            
          case 3:
            leftToRightEA(cT.getImgTremB());
            break;
          default:
            System.out.println("Erro!");
            break;
        }
        break;
      case 2:
        System.out.println("Variavel de Travamento selecionada como solucao!");
        switch(cT.getPosiOrientacao()){
          case 0:
            leftToRight(cT.getImgTremB());
            break;
          case 1:
            RightToLeft(cT.getImgTremBrev());
            break;
          case 2:
            RightToLeft(cT.getImgTremBrev());
            break;            
          case 3:
            leftToRight(cT.getImgTremB());
            break;
          default:
            System.out.println("Erro!");
            break;
        }
        break;
      default:
        System.out.println("Erro! Tipo de Exclusao nao identidicado");
        break;
    }
  }

  public void run(){
    //Verificando orientacao e tipo de exclusao mutua.
    selectedMutualExclusionAndOrientation();
  }
}
