package com.example.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Controller {
    private static Controller controllerInstance;

    @FXML
    private Button btn0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnMultiply;

    @FXML
    private Button btnDivision;

    @FXML
    private Button btnC;

    @FXML
    private Button btnCloseParentheses;

    @FXML
    private Button btnCos;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnNegate;

    @FXML
    private Button btnOpenParentheses;

    @FXML
    private Button btnDot;

    @FXML
    private Button btnPow;

    @FXML
    private Button btnReciprocal;

    @FXML
    private Button btnSin;

    @FXML
    private Button btnSqrt;

    @FXML
    private Button btnTan;

    @FXML
    private TextArea txtHistory;

    @FXML
    private TextField txtInput;

    @FXML
    private Text txtResult;

    @FXML
    private void initialize() {
        controllerInstance = this;

        btn0.setOnAction(event -> Insert("0"));
        btn1.setOnAction(event -> Insert("1"));
        btn2.setOnAction(event -> Insert("2"));
        btn3.setOnAction(event -> Insert("3"));
        btn4.setOnAction(event -> Insert("4"));
        btn5.setOnAction(event -> Insert("5"));
        btn6.setOnAction(event -> Insert("6"));
        btn7.setOnAction(event -> Insert("7"));
        btn8.setOnAction(event -> Insert("8"));
        btn9.setOnAction(event -> Insert("9"));
        btnPlus.setOnAction(event -> Insert("+"));
        btnMinus.setOnAction(event -> Insert("-"));
        btnMultiply.setOnAction(event -> Insert("*"));
        btnDivision.setOnAction(event -> Insert("/"));
        btnOpenParentheses.setOnAction(event -> Insert("("));
        btnCloseParentheses.setOnAction(event -> Insert(")"));
        btnSqrt.setOnAction(event -> Insert("sqrt()", 5));
        btnReciprocal.setOnAction(event -> {
            txtInput.setText("1/(" + txtInput.getText() + ")");
            txtInput.positionCaret(txtInput.getText().length());
        });
        btnNegate.setOnAction(event -> {
            txtInput.setText("-(" + txtInput.getText() + ")");
            txtInput.positionCaret(txtInput.getText().length());
        });
        btnPow.setOnAction(event -> Insert("^"));
        btnSin.setOnAction(event -> Insert("sin()", 4));
        btnCos.setOnAction(event -> Insert("cos()", 4));
        btnTan.setOnAction(event -> Insert("tan()", 4));
        btnDot.setOnAction(event -> Insert("."));
        btnSave.setOnAction(event -> SaveToHistory());
        btnC.setOnAction(event -> {
            SaveToHistory();
            txtInput.setText("");
        });
        btnDel.setOnAction(event -> {
            if (txtInput.getCaretPosition() > 0) {
                if (txtInput.isFocused()) {
                    int caretPosition = txtInput.getCaretPosition();
                    txtInput.setText(txtInput.getText().substring(0, caretPosition - 1) + txtInput.getText().substring(caretPosition));
                    txtInput.positionCaret(caretPosition - 1);
                } else {
                    txtInput.setText(txtInput.getText().substring(0, txtInput.getText().length() - 1));
                    PlaceCaret();
                }
            }
        });
        btnEnter.setOnAction(event -> Evaluate());
        txtInput.textProperty().addListener((observable, oldValue, newValue) -> InputFieldChanged());
    }

    public static void KeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) controllerInstance.Evaluate();
    }

    private void Evaluate() {
        if (!txtResult.getText().equals("NaN")) {
            SaveToHistory();
            txtInput.setText(Result());
            PlaceCaret();
        }
    }

    private void InputFieldChanged() {
        String result = Result();
        if (result.isEmpty()) {
            txtResult.setText("= 0");
        } else if (result.equals("NaN")) {
            txtResult.setText(result);
        } else {
            txtResult.setText("= " + result);
        }

        if (txtInput.getText().equals("0/0")) {
            txtInput.setText("(˶˃ᆺ˂˶)");
            txtHistory.setText("⠀⠀⠀⠀⠀⣀⣤⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⢤⣄⡀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠠⠄⠤⠐⠚⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠓⠢⠤⣀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⢀⠤⣖⣶⣭⣷⣼⣄⠁⠀⠀⠀⠀⠀⠀⢐⣫⣭⣴⣶⣦⢄⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⣪⣿⣿⣿⠿⢿⣿⣿⠻⣄⠀⠀⠀⠀⠀⢀⣼⠿⠿⢿⣿⣿⣿⣧⡀⠀⠀" + "\n" +
                    "⠀⠀⣩⣿⣿⡟⣿⣠⣼⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠁⢸⣤⣼⣿⣿⠻⣿⣿⠀⠀" + "\n" +
                    "⠀⢀⣿⣿⡟⠀⠹⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⠏⠀⢹⣿⡄⠀" + "\n" +
                    "⠀⠈⢿⣿⡃⠀⠀⠀⠉⢁⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣀⠈⠀⠀⠀⢰⠟⡇⠀" + "\n" +
                    "⠀⠀⠀⠉⠗⠖⠀⠊⠉⠉⠁⠀⠀⠀⠀⠀⠀⠰⠀⠀⠈⠙⠛⠒⠀⠐⠆⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣒⣢⣤⣤⣤⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣝⠿⣿⣿⣿⣿⣿⠿⣻⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⣈⡭⠭⣭⠴⠚⠁⠀⠀⠀⠀⠀ " + "\n" + txtHistory.getText());
        }
    }

    private String Result() {
        try {
            return String.valueOf(Calculator.eval(txtInput.getText()));
        } catch (Exception e) {
            return "NaN";
        }
    }

    private void Insert(String string) {
        Insert(string, 1);
    }

    private void Insert(String string, int caretShift) {
        if (txtInput.isFocused()) {
            int caretPosition = txtInput.getCaretPosition();
            txtInput.setText(txtInput.getText().substring(0, caretPosition) + string + txtInput.getText().substring(caretPosition));
            txtInput.positionCaret(caretPosition + caretShift);
        } else {
            txtInput.appendText(string);
            if (caretShift != 0) {
                PlaceCaret();
            }
        }
    }

    private void PlaceCaret() {
        txtInput.requestFocus();
        txtInput.positionCaret(txtInput.getText().length());
    }

    private String previousRecord;

    private void SaveToHistory() {
        String result = Result();
        String string = txtInput.getText() + " = " + Result();
        if (!string.equals(previousRecord) && !txtInput.getText().equals(result) && !Result().equals("NaN")) {
            txtHistory.setText(string + "\n" + txtHistory.getText());
            previousRecord = string;
        }
    }
}
