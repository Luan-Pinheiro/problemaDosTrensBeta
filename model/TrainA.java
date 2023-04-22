/* ***************************************************************
* Autor............: Luan Pinheiro Azevedo
* Nome.............: Problema de Exclusao Mutua
* Funcao...........: Simulação do problema dos Trems com thread na linguagem java, possuíndo GUI.
*************************************************************** */
package model;

import controller.ControllerTrains;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class TrainA extends Thread{
  private int posiEixoX;
  private int posiEixoY;
  private ControllerTrains cT;
  private int speed;

  public int realTimeSpeedTrainA() {
    speed= cT.getSpeedA();
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
        Platform.runLater(()->cT.getImgTremA().setX(posiEixoX));
        posiEixoX++;
        sleep(realTimeSpeedTrainA());
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
        sleep(realTimeSpeedTrainA());
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
        sleep(realTimeSpeedTrainA());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  public void moveToLeft(int stopPoint){
    while(posiEixoX >= stopPoint){
      try {
        Platform.runLater(()->cT.getImgTremArev().setX(posiEixoX));
        posiEixoX--;
        sleep(realTimeSpeedTrainA());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  

  public void leftToRight(ImageView img){ 
    //Fazendo o Trem A aparecer na tela e definindo suas posicoes, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      System.out.println("Velocidade tem A" + speed);
      moveToRight(95);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVT1()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem A esperando vez para Zona Critica [1]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT1(1);
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      cT.setVT1(0);
      Platform.runLater(()->img.setRotate(270));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVT2()==1){
        try {
          Thread.sleep(1);
          System.out.println("Trem A esperando vez para Zona Critica [2]");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      cT.setVT2(1);
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      cT.setVT2(0);
      Platform.runLater(()->img.setRotate(270));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }

  public void RightToLeft(ImageView img){
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      System.out.println("Velocidade tem A" + speed);
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(270));
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }
   
  public void leftToRightEA(ImageView img){ 
    System.out.println("entrouLREA");
    //Fazendo o Trem A aparecer na tela e definindo suas posicoes, para depois faze-lo se mover
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      moveToRight(95);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVEZ1()!=0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(255);
      cT.setVEZ1(1);
      Platform.runLater(()->img.setRotate(270));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(395);
      Platform.runLater(()->img.setRotate(90));
      while(cT.getVEZ2()!=0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(555);
      cT.setVEZ2(1);
      Platform.runLater(()->img.setRotate(270));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToRight(680);
      posiEixoX = auxX;
      posiEixoY = auxY;
    }
  }

  public void RightToLeftEA(ImageView img){
    System.out.println("entrouRLEA");
    img.setVisible(true);
    posiEixoX = (int)(img.getX());
    posiEixoY = (int)(img.getY());
    int auxX = posiEixoX;
    int auxY = posiEixoY;
    while(true){
      cT.setVEZ1(1);
      moveToLeft(-95);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVEZ2()!=0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-255);
      cT.setVEZ2(1);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,0);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-395);
      Platform.runLater(()->img.setRotate(270));
      while(cT.getVEZ1()!=0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      moveDown(img,50);
      Platform.runLater(()->img.setRotate(0));
      moveToLeft(-555);
      cT.setVEZ1(1);
      Platform.runLater(()->img.setRotate(90));
      moveUp(img,0);
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
            leftToRightEA(cT.getImgTremA());
            break;
          case 1:
            RightToLeftEA(cT.getImgTremArev());
            break;
          case 2:
            leftToRightEA(cT.getImgTremA());
            break;            
          case 3:
            RightToLeftEA(cT.getImgTremArev());
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
            leftToRight(cT.getImgTremA());
            break;
          case 1:
            RightToLeft(cT.getImgTremArev());
            break;
          case 2:
            leftToRight(cT.getImgTremA());
            break;            
          case 3:
            RightToLeft(cT.getImgTremArev());
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
