package assigment2_javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 101117858
 */
public class FXMLDocumentController implements Initializable {
    static MemberManager newMember;
    private Label label;
    @FXML
    private Button btn_AddMember;
    @FXML
    private TableColumn<Member, Integer> clm_ID;
    @FXML
    private TableColumn<Member, String> clm_FName;
    @FXML
    private TableColumn<Member, String> clm_LName;
    @FXML
    private TableColumn<Member, Integer> clm_NumGames;
    @FXML
    private TableColumn<Member, Integer> clm_NumWins;
    @FXML
    private TableColumn<Member, Integer> clm_NumLosses;
    @FXML
    private TableColumn<Member, Double> clm_WinRate;
    @FXML
    private TextField txt_FName;
    @FXML
    private TextField txt_LName;
    @FXML
    private TextField txt_AddWinID;
    @FXML
    private Button btn_AddWIn;
    @FXML
    private Button btn_AddLoss;
    @FXML
    private TextField txt_AddLossID;
    @FXML
    private Button btn_ShowBestPlayer;
    @FXML
    private Button btn_ShowMostWin;
    @FXML
    private TableView<Member> tb_AllMembers;
    @FXML
    private Button btn_DelMember;
    @FXML
    private TextField txt_DelMember;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newMember = new MemberManager(9);
        populateTable();
    }    
    
    private void populateTable(){
        clm_ID.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberId"));
        clm_FName.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
        clm_LName.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
        clm_NumGames.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numGames"));
        clm_NumWins.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numWins"));
        clm_NumLosses.setCellValueFactory(new PropertyValueFactory<Member, Integer>("numLoss"));
        clm_WinRate.setCellValueFactory(new PropertyValueFactory<Member, Double>("winRate"));
        for(int i = 0; i < newMember.getNumMembers(); i++){
            tb_AllMembers.getItems().add(newMember.getMemberList()[i]);
        }        
    }
    
    private void clearTable(){
        tb_AllMembers.getItems().clear();
    }

    @FXML
    private void handleBtnAddMemberAction(ActionEvent event) {
        String FirstName = txt_FName.getText();
        String LastName = txt_LName.getText();
        
        if(newMember.addMember(FirstName, LastName)){
            btnSuccess(FirstName+" "+LastName+" has been added.");
            txt_FName.clear();
            txt_LName.clear();
            clearTable();
            populateTable();
        }
        else{
            btnError("Error. "+FirstName+" "+LastName+" has NOT been added.");
        }
    }
    
    @FXML
    private void handleBtnDelMemberAction(ActionEvent event) {
        try{
            int MemberID = Integer.parseInt(txt_DelMember.getText());
            if(newMember.delMember(MemberID)){
                btnSuccess("Member with ID "+MemberID+" has been deleted.");
                txt_DelMember.clear();
                clearTable();
                populateTable();
            }
            else{
                btnError("Error. Member wiht ID "+MemberID+" has NOT been deleted.");
            }
        } catch (NumberFormatException ex)
        {
            btnError("Error. Enter a Number.");
        }
    }
    
    @FXML
    private void handleBtnAddWinAction(ActionEvent event) {
        try
        {
            int MemberID = Integer.parseInt(txt_AddWinID.getText());
            if(newMember.addWin(MemberID)){
                btnSuccess("Memeber with ID "+MemberID+" has recorded a Win.");
                txt_AddWinID.clear();
                clearTable();
                populateTable();
            }
            else{
                btnError("Error. Member with ID "+MemberID+" has NOT added a Win.");
            }
        } catch (NumberFormatException ex)
        {
            btnError("Error. Enter a Number.");
        }

    }

    @FXML
    private void handleBtnAddLossAction(ActionEvent event) {
        try{
            int MemberID = Integer.parseInt(txt_AddLossID.getText());
            if(newMember.addLoss(MemberID)){
                btnSuccess("Member with ID "+MemberID+" has recorded a Loss.");
                txt_AddLossID.clear();
                clearTable();
                populateTable();
            }
            else{
                btnError("Error. Member with ID "+MemberID+" has NOT added a Loss.");
            }
        } catch (NumberFormatException ex)
        {
            btnError("Error. Enter a Number.");
        }
    }   

    @FXML
    private void handleBtnBestPlayerAction(ActionEvent event) {
        Stage popupwindow=new Stage();      
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Best Player");
        Label label1= new Label(newMember.bestPlayer());
        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 275);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    @FXML
    private void handleBtnMostWinsAction(ActionEvent event) {
        Stage popupwindow=new Stage();      
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Player With Most Wins");
        Label label1= new Label(newMember.mostWins());
        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 275);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    
    @FXML
    private void btnSuccess(String msg) {
        Stage popupwindow=new Stage();      
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Success");
        Label label1= new Label(msg);
        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 125);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    
    @FXML
    private void btnError(String msg) {
        Stage popupwindow=new Stage();      
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Error");
        Label label1= new Label(msg);
        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 125);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
}
