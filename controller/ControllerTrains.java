/* ***************************************************************
* Autor............: Luan Pinheiro Azevedo
* Nome.............: Problema de Exclusao Mutua
* Funcao...........: Simulação do problema dos Trems com thread na linguagem java, possuíndo GUI.
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.TrainA;
import model.TrainB;

public class ControllerTrains implements Initializable{
    @FXML
    private CheckBox boxEstritAlternancia;
    @FXML
    private ChoiceBox<String> boxOrientacao;
    @FXML
    private CheckBox boxTravamento;
    @FXML
    private Button btnInit;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView imgTremA;
    @FXML
    private ImageView imgTremArev;
    @FXML
    private ImageView imgTremB;
    @FXML
    private ImageView imgTremBrev;
    @FXML
    private Slider sliderA;
    @FXML
    private Slider sliderB;

    private int posiOrientacao;
    private int typeOfMutualExclusion = 0;
    private int VT1 = 0;
    private int VT2 = 0;
    private int VEZ1 = 0;
    private int VEZ2 = 0;
    Alert alerta = new  Alert(AlertType.ERROR);
    List<String> listaChoiceBox = new ArrayList<>();
    TrainA trainA = new TrainA();
    TrainB trainB = new TrainB();
    private int speedA;
    private int speedB;
    @FXML
    void OnClickInit(ActionEvent event) {
      if(boxOrientacao.getValue() != null && getTypeOfMutualExclusion() != 0){
        btnInit.setVisible(false);
        trainA.start();
        trainB.start();
      }else{
        alerta.setTitle("Erro na selecao de orientacao");
        alerta.setHeaderText("Opcao vazia ou inexistente");
        alerta.setContentText("Selecione uma opcao valida!");
        alerta.showAndWait();
      }
    }
    
    @FXML
    void OnClickReturn(ActionEvent event){
      reset();
    }
    
    @FXML
    void onSelectedBoxAlternancia(ActionEvent event) {
      if(boxTravamento.isSelected()){
        boxTravamento.setSelected(false);
      }
    }
    
    @FXML
    void onSelectedBoxTravamento(ActionEvent event) {
      if(boxEstritAlternancia.isSelected()){
        boxEstritAlternancia.setSelected(false);
      }
    } 
    public int getPosiOrientacao() {
      switch(boxOrientacao.getSelectionModel().getSelectedIndex()){
        case 0:
        posiOrientacao = 0;
        break;
        case 1:
        posiOrientacao = 1;
        break;
        case 2:
        posiOrientacao = 2;
        break;            
        case 3:
        posiOrientacao = 3;
        break;
        default:
        System.out.println("Erro na selecao de orientacao");
        break;
      }
      return posiOrientacao;
    }
    
    public int getTypeOfMutualExclusion(){
      if(boxEstritAlternancia.isSelected())
      typeOfMutualExclusion = 1;
      else if(boxTravamento.isSelected())
      typeOfMutualExclusion = 2;
      else
      typeOfMutualExclusion = 0;
      
      return typeOfMutualExclusion;
    }

    public void reset() {
      posiOrientacao = 0;
      typeOfMutualExclusion = 0;
      VT1 = 0;
      VT2 = 0;
      VEZ1 = 0;
      VEZ2 = 0;
      speedA = 0;
      speedB = 0;
      boxEstritAlternancia.setSelected(false);
      boxOrientacao.getSelectionModel().selectFirst();
      boxTravamento.setSelected(false);
      sliderA.setValue(0);
      sliderB.setValue(0);
      btnInit.setVisible(true);
      trainA.interrupt();
      trainB.interrupt();
      trainA = new TrainA();
      trainB = new TrainB();
   }

    //Getters e Setters
    public int getVEZ1() {
      return VEZ1;
    }
    public void setVEZ1(int vEZ1) {
      VEZ1 = vEZ1;
    }
    public int getVEZ2() {
      return VEZ2;
    }
    public void setVEZ2(int vEZ2) {
      VEZ2 = vEZ2;
    }
    public int getSpeedA() {
      return speedA;
    }
    public int getSpeedB() {
      return speedB;
    }
    public int getVT1() {
      return VT1;
    }
    public void setVT1(int vT1) {
      VT1 = vT1;
    }
    public int getVT2() {
      return VT2;
    }
    public void setVT2(int vT2) {
      VT2 = vT2;
    }
    public ImageView getImgTremA() {
      return imgTremA;
    }
    public void setImgTremA(ImageView imgTremA) {
      this.imgTremA = imgTremA;
    }
    public ImageView getImgTremArev() {
      return imgTremArev;
    }
    public void setImgTremArev(ImageView imgTremArev) {
      this.imgTremArev = imgTremArev;
    }
    public ImageView getImgTremB() {
      return imgTremB;
    }
    public void setImgTremB(ImageView imgTremB) {
      this.imgTremB = imgTremB;
    }
    public ImageView getImgTremBrev() {
      return imgTremBrev;
    }
    public void setImgTremBrev(ImageView imgTremBrev) {
      this.imgTremBrev = imgTremBrev;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /* Subdividindo as barras de velocidades em tres partes, 
      setando as bolinhas de selecao para ocupar as tres partes da divisao. */
      sliderA.setMin(0);
      sliderA.setMax(2);
      sliderA.setValue(0);
      //definindo unidade de marcacao principal do slider
      sliderA.setMajorTickUnit(1);
      //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
      sliderA.setMinorTickCount(0);
      sliderA.setSnapToTicks(true);
      sliderB.setMin(0);
      sliderB.setMax(2);
      sliderB.setValue(0);
      //definindo unidade de marcacao principal do slider
      sliderB.setMajorTickUnit(1);
      //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
      sliderB.setMinorTickCount(0);
      sliderB.setSnapToTicks(true);

      //Passando a referencia desse controlle para as threads que o chamam.
      trainA.linkController(this);
      trainB.linkController(this);

      //Demarcando o conteudo presente no Choice Box para ser selecionado.
      boxOrientacao.getItems().addAll("Mesma orientacao esquerda", "Mesma orientacao direita", "Trem A comeca na esquerda e B na direita", "Trem B comeca na esquerda e A na direita");
      
      //Deixando os Image Views inicialmente invisiveis.
      imgTremA.setVisible(false);
      imgTremB.setVisible(false);
      imgTremArev.setVisible(false);
      imgTremBrev.setVisible(false);

      //definindo listener para mudanca de velocidade em dos trems tempo real
      sliderA.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
            speedA = newValue.intValue();
        }
      });
      sliderB.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
            speedB = newValue.intValue();
        }
      });
    }
}

