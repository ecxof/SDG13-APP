package com.example.sdg13ver5;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;


public class Controller4 {

    @FXML
    TextField admintitle1;

    @FXML
    TextArea Info1;
    @FXML
    public void page_1() throws IOException{
        String page1_desc = Info1.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_One_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
    @FXML
    TextArea Info2;
    @FXML
    public void page_2() throws IOException{
        String page1_desc = Info2.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_Two_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
    @FXML
    TextArea Info3;
    @FXML
    public void page_3() throws IOException{
        String page1_desc = Info3.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_Three_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
    @FXML
    TextArea Info4;
    @FXML
    public void page_4() throws IOException{
        String page1_desc = Info4.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_Four_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
    @FXML
    TextArea Info5;
    @FXML
    public void page_5() throws IOException{
        String page1_desc = Info5.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_Five_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
    @FXML
    TextArea Info6;
    @FXML
    public void page_6() throws IOException{
        String page1_desc = Info6.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Page_Six_Data.txt"));
        writer.write(page1_desc);
        writer.close();
    }
}



