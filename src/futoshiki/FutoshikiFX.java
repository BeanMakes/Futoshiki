/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author bdtw20
 */
public class FutoshikiFX extends Application {

    private String numInsert = "";
    private Futoshiki game = null;
    private Pane pane = null;
    private Random rnd = new Random();
    private MusicPlayer mp = new MusicPlayer();
    private SaveAndLoad saveAndLoad = new SaveAndLoad();
    private Graphics graphics = new Graphics();
    private Stage stage;
    private boolean editing = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        File folder = new File("src\\SaveFile");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        mp.playMusic();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 460, 300);

        pane = mainMenuPane();

        root.setCenter(pane);
        stage = primaryStage;
        stage.setScene(scene);

        stage.setTitle("Futoshiki Game");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
    Pane for the display of the Futoshiki grid from loading the instance
    */
    private Pane gameLoadPane(Futoshiki g) throws IOException {

        mp.changeMusicToGameMusic();
        game = g;
        int num = game.getSize();

        HBox colNum = new HBox();
        colNum.setSpacing(0);
        colNum.setAlignment(Pos.CENTER);
        colNum.setPadding(new Insets(0, 0, 0, 0));
        for (int j = 0; j < num; j++) {
            VBox rowNum = new VBox();
            rowNum.setSpacing(0);
            rowNum.setAlignment(Pos.CENTER);
            rowNum.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0; i < num; i++) {
                Button btn = null;
                int col = j;
                int row = i;
                if (game.getGrid()[j][i] == null) {
                    btn = new Button();

                } else {
                    btn = new Button("" + game.getGrid()[j][i].getNumber());
                }
                final Button btn2 = btn;

                btn2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (editing) {

                            if (game.getGrid()[col][row] == null) {
                                btn2.setText(numInsert);
                                btn2.setTextFill(Color.GREY);
                            } else {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                invalidChangeAlert();
                            }
                        } else {
                            if (game.getGrid()[col][row] == null) {
                                if (numInsert.equals("")) {

                                } else {
                                    game.setSquare(Integer.parseInt(numInsert), col, row);
                                    if (game.isPuzzleComplete()) {
                                        Parent root;
                                        root = winMenu();
                                        stage.setTitle("You Won");
                                        stage.setScene(new Scene(root));
                                    } else if (!game.isLegal()) {
                                        btn2.setGraphic(graphics.errorNum());
                                    } else {
                                        btn2.setGraphic(graphics.numImage());
                                    }
                                    btn2.setText(numInsert);
                                }
                            } else if (numInsert.equals("")) {
                                if (game.getGrid()[col][row].isEditable()) {
                                    game.clearSquare(col, row);
                                    btn2.setText("");
                                    btn2.setGraphic(graphics.numImage());
                                } else {
                                    game.clearSquare(col, row);
                                    invalidChangeAlert();
                                }
                            } else if (!game.getGrid()[col][row].isEditable()) {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                invalidChangeAlert();
                            } else {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                btn2.setText(numInsert);
                                if (!game.isLegal()) {
                                    btn2.setGraphic(graphics.errorNum());
                                } else if (game.isPuzzleComplete()) {
                                    Parent root;
                                    root = winMenu();
                                    stage.setTitle("You Won");
                                    stage.setScene(new Scene(root));
                                } else {
                                    btn2.setGraphic(graphics.numImage());
                                }

                            }
                            btn2.setTextFill(Color.BLACK);
                        }
                    }
                });
                btn2.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                Font font = new Font(24.0);

                btn2.setFont(font);
                btn2.setTextFill(Color.BLACK);
                btn2.setBackground(Background.EMPTY);

                btn2.setGraphic(graphics.numImage());
                btn2.setMaxWidth(Double.MAX_VALUE);
                rowNum.getChildren().add(btn2);
                Button lbl = null;
                if (i >= num - 1 || game.getColConstraints()[j][i] == null) {
                    lbl = new Button();
                } else {
                    lbl = new Button(game.getColConstraints()[j][i].setConstraintsForGrid());
                }
                lbl.setMaxWidth(Double.MAX_VALUE);
                lbl.setBackground(Background.EMPTY);
                if (i == num - 1) {
                    lbl.setText("" + num);
                    lbl.setVisible(false);
                    lbl.setMaxWidth(Double.MAX_VALUE);
                }
                lbl.setFont(new Font(24));
                rowNum.getChildren().add(lbl);
            }
            colNum.getChildren().addAll(rowNum);
            VBox rowCon = new VBox();
            rowCon.setSpacing(0);
            rowCon.setAlignment(Pos.CENTER);
            rowCon.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0; i < num; i++) {
                Button lbl = null;
                if (j >= num - 1 || game.getRowConstraints()[j][i] == null) {
                    lbl = new Button();
                } else {
                    lbl = new Button(game.getRowConstraints()[j][i].setConstraintsForGrid());

                }
                lbl.setBackground(Background.EMPTY);
                lbl.setMaxWidth(Double.MAX_VALUE);
                lbl.setGraphic(graphics.backImage());
                lbl.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                lbl.setFont(new Font(24));
                rowCon.getChildren().add(lbl);
                Button blank = new Button("" + num);
                blank.setFont(new Font(24));
                blank.setMaxWidth(Double.MAX_VALUE);
                blank.setVisible(false);
                rowCon.getChildren().add(blank);
            }
            colNum.getChildren().addAll(rowCon);
        }
        colNum.getChildren().remove(num * 2 - 1);

        BorderPane bp = new BorderPane();
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(5);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPadding(new Insets(0, 20, 20, 20));
        ToggleButton empty = new ToggleButton("X");
        empty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (empty.isSelected()) {
                    numInsert = "";
                    for (Node node : vbButtons.getChildren()) {
                        if (node instanceof ToggleButton) {

                            if ((((ToggleButton) node).getText()).equals(numInsert)) {

                            } else {
                                ((ToggleButton) node).setSelected(false);
                            }

                        }

                    }
                }
            }
        });

        empty.setMaxWidth(Double.MAX_VALUE);

        vbButtons.getChildren().add(empty);
        for (int i = 0; i < num; i++) {
            ToggleButton btn = new ToggleButton("" + (i + 1));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (btn.isSelected()) {
                        numInsert = btn.getText();
                        for (Node node : vbButtons.getChildren()) {
                            if (node instanceof ToggleButton) {

                                if ((((ToggleButton) node).getText()).equals(numInsert)) {

                                } else {
                                    ((ToggleButton) node).setSelected(false);
                                }

                            }

                        }
                    }
                }
            });

            btn.setMaxWidth(Double.MAX_VALUE);
            vbButtons.getChildren().add(btn);
        }

        bp.setPadding(new Insets(20, 0, 20, 20));
        HBox editButtons = new HBox();
        ToggleButton markButton = new ToggleButton();
        markButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editing = true;
                for (Node node : editButtons.getChildren()) {
                    if ((((ToggleButton) node)).equals(markButton)) {

                    } else {
                        ((ToggleButton) node).setSelected(false);
                    }
                }
            }

        });
        markButton.setGraphic(graphics.pencilIconImage());

        ToggleButton enterButton = new ToggleButton();
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editing = false;
                for (Node node : editButtons.getChildren()) {
                    if ((((ToggleButton) node)).equals(enterButton)) {

                    } else {
                        ((ToggleButton) node).setSelected(false);
                    }
                }
            }

        });

        enterButton.setSelected(true);
        enterButton.setGraphic(graphics.penIconImage());
        editButtons.getChildren().addAll(enterButton, markButton);
        VBox settings = new VBox();
        settings.setAlignment(Pos.CENTER);
        settings.setPadding(new Insets(0, 20, 10, 20));
        Button settingsBtn = new Button("Settings");
        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = optionPane();
                Stage stage = new Stage();
                stage.setTitle("Futoshiki Options");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            }

        });
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        Button solve = new Button("I give up!");
        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = lossMenu();
                stage.setTitle("Game Over");
                stage.setScene(new Scene(root));
            }

        });
        solve.setMaxWidth(Double.MAX_VALUE);
        Button giveHint = new Button("Give Hint");
        giveHint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int randCol = rnd.nextInt(game.getSize());
                int randRow = rnd.nextInt(game.getSize());
                while (game.getGrid()[randRow][randCol] != null) {
                    randCol = rnd.nextInt(game.getSize());
                    randRow = rnd.nextInt(game.getSize());
                }
                hintAlert(game.getAnswer()[randCol][randRow].getNumber(), randCol, randRow);
            }

        });
        giveHint.setMaxWidth(Double.MAX_VALUE);

        settings.getChildren().addAll(giveHint, solve, settingsBtn);
        bp.setRight(settings);
        bp.setLeft(vbButtons);
        bp.setCenter(colNum);
        bp.setBottom(editButtons);
        bp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return bp;
    }

    /*
    Pane for the display of the Futoshiki grid from creating a new Futoshiki instance
    */
    private Pane gamePane(int num, int rowCons, int colCons) throws IOException {
        game = new Futoshiki(num);
        game.fillPuzzle(num, rowCons, colCons);

        mp.changeMusicToGameMusic();
        HBox colNum = new HBox();
        colNum.setSpacing(0);
        colNum.setAlignment(Pos.CENTER);
        colNum.setPadding(new Insets(0, 0, 0, 0));
        for (int j = 0; j < num; j++) {
            VBox rowNum = new VBox();
            rowNum.setSpacing(0);
            rowNum.setAlignment(Pos.CENTER);
            rowNum.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0; i < num; i++) {
                Button btn = null;
                int col = j;
                int row = i;
                if (game.getGrid()[j][i] == null) {
                    btn = new Button();

                } else {
                    btn = new Button("" + game.getGrid()[j][i].getNumber());
                }
                final Button btn2 = btn;

                btn2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (editing) {

                            if (game.getGrid()[col][row] == null) {
                                btn2.setText(numInsert);
                                btn2.setTextFill(Color.GREY);
                            } else {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                invalidChangeAlert();
                            }
                        } else {
                            if (game.getGrid()[col][row] == null) {
                                if (numInsert.equals("")) {

                                } else {
                                    game.setSquare(Integer.parseInt(numInsert), col, row);
                                    if (game.isPuzzleComplete()) {
                                        Parent root;
                                        root = winMenu();
                                        stage.setTitle("You Won");
                                        stage.setScene(new Scene(root));
                                    } else if (!game.isLegal()) {
                                        btn2.setGraphic(graphics.errorNum());
                                    } else {
                                        btn2.setGraphic(graphics.numImage());
                                    }
                                    btn2.setText(numInsert);
                                }
                            } else if (numInsert.equals("")) {
                                if (game.getGrid()[col][row].isEditable()) {
                                    game.clearSquare(col, row);
                                    btn2.setText("");
                                    btn2.setGraphic(graphics.numImage());
                                } else {
                                    game.clearSquare(col, row);
                                    invalidChangeAlert();
                                }
                            } else if (!game.getGrid()[col][row].isEditable()) {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                invalidChangeAlert();
                            } else {
                                game.setSquare(Integer.parseInt(numInsert), col, row);
                                btn2.setText(numInsert);
                                if (!game.isLegal()) {
                                    btn2.setGraphic(graphics.errorNum());
                                } else if (game.isPuzzleComplete()) {
                                    Parent root;
                                    root = winMenu();
                                    stage.setTitle("You Won");
                                    stage.setScene(new Scene(root));
                                } else {
                                    btn2.setGraphic(graphics.numImage());
                                }

                            }
                            btn2.setTextFill(Color.BLACK);
                        }
                    }

                });
                btn2.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                Font font = new Font(24.0);

                btn2.setFont(font);
                btn2.setTextFill(Color.BLACK);
                btn2.setBackground(Background.EMPTY);

                btn2.setGraphic(graphics.numImage());
                btn2.setMaxWidth(Double.MAX_VALUE);
                rowNum.getChildren().add(btn2);
                Button lbl = null;
                if (i >= num - 1 || game.getColConstraints()[j][i] == null) {
                    lbl = new Button();
                } else {
                    lbl = new Button(game.getColConstraints()[j][i].setConstraintsForGrid());
                }
                lbl.setMaxWidth(Double.MAX_VALUE);
                lbl.setBackground(Background.EMPTY);
                if (i == num - 1) {
                    lbl.setText("" + num);
                    lbl.setVisible(false);
                    lbl.setMaxWidth(Double.MAX_VALUE);
                }
                lbl.setFont(new Font(24));
                rowNum.getChildren().add(lbl);
            }
            colNum.getChildren().addAll(rowNum);
            VBox rowCon = new VBox();
            rowCon.setSpacing(0);
            rowCon.setAlignment(Pos.CENTER);
            rowCon.setPadding(new Insets(0, 0, 0, 0));
            for (int i = 0; i < num; i++) {
                Button lbl = null;
                if (j >= num - 1 || game.getRowConstraints()[j][i] == null) {
                    lbl = new Button();
                } else {
                    lbl = new Button(game.getRowConstraints()[j][i].setConstraintsForGrid());

                }
                lbl.setBackground(Background.EMPTY);
                lbl.setMaxWidth(Double.MAX_VALUE);
                lbl.setGraphic(graphics.backImage());
                lbl.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                lbl.setFont(new Font(24));
                rowCon.getChildren().add(lbl);
                Button blank = new Button("" + num);
                blank.setFont(new Font(24));
                blank.setMaxWidth(Double.MAX_VALUE);
                blank.setVisible(false);
                rowCon.getChildren().add(blank);
            }
            colNum.getChildren().addAll(rowCon);
        }
        colNum.getChildren().remove(num * 2 - 1);

        BorderPane bp = new BorderPane();
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(5);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPadding(new Insets(0, 20, 20, 20));
        ToggleButton empty = new ToggleButton("X");
        empty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (empty.isSelected()) {
                    numInsert = "";
                    for (Node node : vbButtons.getChildren()) {
                        if (node instanceof ToggleButton) {

                            if ((((ToggleButton) node).getText()).equals(numInsert)) {

                            } else {
                                ((ToggleButton) node).setSelected(false);
                            }

                        }

                    }
                }
            }
        });

        empty.setMaxWidth(Double.MAX_VALUE);

        vbButtons.getChildren().add(empty);
        for (int i = 0; i < num; i++) {
            ToggleButton btn = new ToggleButton("" + (i + 1));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (btn.isSelected()) {
                        numInsert = btn.getText();
                        for (Node node : vbButtons.getChildren()) {
                            if (node instanceof ToggleButton) {

                                if ((((ToggleButton) node).getText()).equals(numInsert)) {

                                } else {
                                    ((ToggleButton) node).setSelected(false);
                                }

                            }

                        }
                    }
                }
            });

            btn.setMaxWidth(Double.MAX_VALUE);
            vbButtons.getChildren().add(btn);
        }

        HBox editButtons = new HBox();
        ToggleButton markButton = new ToggleButton();
        markButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editing = true;
                for (Node node : editButtons.getChildren()) {
                    if ((((ToggleButton) node)).equals(markButton)) {

                    } else {
                        ((ToggleButton) node).setSelected(false);
                    }
                }
            }

        });
        markButton.setGraphic(graphics.pencilIconImage());

        ToggleButton enterButton = new ToggleButton();
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editing = false;
                for (Node node : editButtons.getChildren()) {
                    if ((((ToggleButton) node)).equals(enterButton)) {

                    } else {
                        ((ToggleButton) node).setSelected(false);
                    }
                }
            }

        });

        enterButton.setSelected(true);
        enterButton.setGraphic(graphics.penIconImage());
        editButtons.getChildren().addAll(enterButton, markButton);

        bp.setPadding(new Insets(20, 0, 20, 20));
        VBox settings = new VBox();
        settings.setAlignment(Pos.CENTER);
        settings.setPadding(new Insets(0, 20, 10, 20));
        Button settingsBtn = new Button("Settings");
        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = optionPane();
                Stage stage = new Stage();
                stage.setTitle("Futoshiki Options");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
            }

        });
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        Button solve = new Button("I give up!");
        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = lossMenu();
                stage.setTitle("Game Over");
                stage.setScene(new Scene(root));
            }

        });
        solve.setMaxWidth(Double.MAX_VALUE);
        Button giveHint = new Button("Give Hint");
        giveHint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int randCol = rnd.nextInt(game.getSize());
                int randRow = rnd.nextInt(game.getSize());
                while (game.getGrid()[randRow][randCol] != null) {
                    randCol = rnd.nextInt(game.getSize());
                    randRow = rnd.nextInt(game.getSize());
                }
                hintAlert(game.getAnswer()[randCol][randRow].getNumber(), randCol, randRow);
            }

        });
        giveHint.setMaxWidth(Double.MAX_VALUE);

        settings.getChildren().addAll(giveHint, solve, settingsBtn);

        bp.setRight(settings);
        bp.setLeft(vbButtons);
        bp.setCenter(colNum);
        bp.setBottom(editButtons);
        bp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return bp;
    }

    /*
    Pane for displaying the main menu of the game
    */
    private Pane mainMenuPane() {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        Label title = new Label("Futoshiki");

        Button newGame = new Button("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = gameModeSelecPane();
                stage.setTitle("Futoshiki Gamemodes");
                stage.setScene(new Scene(root, 450, 450));

            }

        }
        );

        Button loadGame = new Button("Load Game");

        loadGame.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root;
                    Futoshiki temp = saveAndLoad.loadGame(stage);
                    root = gameLoadPane(temp);
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            System.out.println("Size:" + game.getSize());
                            if(temp.getSize() == 3){
                                saveAndLoad.saveGame("Easy", temp);
                            } else if(temp.getSize() == 5){
                                saveAndLoad.saveGame("Medium", temp);
                            } else if(temp.getSize() == 10){
                                saveAndLoad.saveGame("Hard", temp);
                            } else{
                                saveAndLoad.saveGame("Custom", temp);
                            }
                        }

                    });
                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, temp);

                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);
                    mb.getMenus()
                            .addAll(fileMenu);

                    BorderPane bp = new BorderPane();
                    bp.setTop(mb);
                    bp.setCenter(root);

                    stage.setScene(new Scene(bp));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        });

        Button options = new Button("Options");

        options.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                Parent root;
                root = optionPane();
                Stage stage = new Stage();
                stage.setTitle("Futoshiki Options");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();

            }

        }
        );

        Button quit = new Button("Quit");

        quit.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                stage.close();
            }

        }
        );

        title.setFont(new Font(26));
        newGame.setFont(new Font(24));
        loadGame.setFont(new Font(24));
        options.setFont(new Font(24));
        quit.setFont(new Font(24));

        title.setAlignment(Pos.CENTER);

        title.setMaxWidth(Double.MAX_VALUE);
        newGame.setMaxWidth(Double.MAX_VALUE);
        loadGame.setMaxWidth(Double.MAX_VALUE);
        options.setMaxWidth(Double.MAX_VALUE);
        quit.setMaxWidth(Double.MAX_VALUE);

        vb.getChildren()
                .addAll(title, newGame, loadGame, options, quit);

        vb.setPadding(new Insets(20, 40, 20, 40));
        vb.setAlignment(Pos.CENTER);

        bp.setCenter(vb);
        return bp;
    }

    /*
    Pane for displaying the gamemode selection screen
    */
    private Pane gameModeSelecPane() {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();

        Label title = new Label("Gamemodes");

        Button easy = new Button("Easy");
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root;

                    root = gamePane(3, 2, 2);

                    stage.setTitle("Futoshiki Easy");
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            saveAndLoad.saveGame("Easy", game);
                        }

                    });

                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, game);
                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);

                    mb.getMenus()
                            .addAll(fileMenu);

                    BorderPane bp = new BorderPane();
                    bp.setTop(mb);
                    bp.setCenter(root);

                    stage.setScene(new Scene(bp));
                } catch (IOException ex) {
                    Logger.getLogger(FutoshikiFX.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        Button medium = new Button("Medium");
        medium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root;

                    root = gamePane(5, 8, 8);

                    stage.setTitle("Futoshiki Medium");
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            saveAndLoad.saveGame("Medium", game);
                        }

                    });
                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, game);
                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);
                    mb.getMenus()
                            .addAll(fileMenu);

                    BorderPane bp = new BorderPane();
                    bp.setTop(mb);
                    bp.setCenter(root);

                    stage.setScene(new Scene(bp));
                } catch (IOException ex) {
                    Logger.getLogger(FutoshikiFX.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        Button hard = new Button("Hard");
        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root;

                    root = gamePane(10, 13, 13);

                    stage.setTitle("Futoshiki Hard");
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            saveAndLoad.saveGame("Hard", game);
                        }

                    });
                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, game);

                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);

                    mb.getMenus()
                            .addAll(fileMenu);

                    BorderPane bp = new BorderPane();
                    bp.setTop(mb);
                    bp.setCenter(root);

                    stage.setScene(new Scene(bp));
                } catch (IOException ex) {
                    Logger.getLogger(FutoshikiFX.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        Button custom = new Button("Custom");
        custom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = customPane();;
                stage.setTitle("Futoshiki Custom");
                stage.setScene(new Scene(root, 800, 800));
            }

        });

        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = mainMenuPane();
                stage.setTitle("Futoshiki");
                stage.setScene(new Scene(root, 450, 450));
            }

        });

        title.setFont(new Font(26));
        easy.setFont(new Font(24));
        medium.setFont(new Font(24));
        hard.setFont(new Font(24));
        custom.setFont(new Font(24));
        back.setFont(new Font(24));

        title.setMaxWidth(Double.MAX_VALUE);
        easy.setMaxWidth(Double.MAX_VALUE);
        medium.setMaxWidth(Double.MAX_VALUE);
        hard.setMaxWidth(Double.MAX_VALUE);
        custom.setMaxWidth(Double.MAX_VALUE);
        back.setMaxWidth(Double.MAX_VALUE);

        vb.getChildren().addAll(title, easy, medium, hard, custom, back);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(0, 150, 0, 150));
        bp.setCenter(vb);
        return bp;

    }

    /*
    Pane for displaying the option settings
    */
    private Pane optionPane() {
        VBox vbLabels = new VBox();
        VBox vbControls = new VBox();

        ToggleButton musicOnBtn = new ToggleButton("Music");
        //musicOnBtn.setSelected(!mp.isMusicPlaying());
        if (!mp.isMusicPlaying()) {
            musicOnBtn.setSelected(false);
        }

        musicOnBtn.setFont(new Font(24));
        musicOnBtn.setOnAction(e -> {
            if (musicOnBtn.isSelected()) {
                mp.stopMusic();
            } else {
                mp.playMusic();
            }
        });

        Label musicLb = new Label("Music On/Off");
        musicLb.setFont(new Font(24));

        Button filler = new Button();
        filler.setFont(new Font(24));
        filler.setVisible(false);

        Button filler2 = new Button();
        filler2.setFont(new Font(24));
        filler2.setVisible(false);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(80);
        slider.setValue(mp.getValueGainControl());
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        slider.setBlockIncrement(10);
        slider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                mp.changeVolume((float) slider.getValue());
            }

        });

        Label sliderLb = new Label("Music Volume");
        sliderLb.setFont(new Font(24));

        vbLabels.getChildren().addAll(musicLb, filler, sliderLb);
        vbControls.getChildren().addAll(musicOnBtn, filler2, slider);

        BorderPane bp = new BorderPane();

        Button backBtn = new Button("Close");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) backBtn.getScene().getWindow();
                s.close();
            }

        });
        backBtn.setFont(new Font(24));
        bp.setLeft(vbLabels);
        bp.setCenter(vbControls);

        bp.setBottom(backBtn);

        return bp;
    }

    /*
    Pane for displaying the option menu
    */
    public Pane customPane() {
        BorderPane bp = new BorderPane();
        VBox vbox = new VBox();
        VBox textvbox = new VBox();
        HBox bottomHbox = new HBox();

        Label sizeOfGameLb = new Label("Size of the game");
        sizeOfGameLb.setFont(new Font(24));
        sizeOfGameLb.setAlignment(Pos.CENTER);

        TextField gameSize = new TextField();
        gameSize.setFont(new Font(24));
        gameSize.setPrefColumnCount(2);

        Label rowConsOfGameLb = new Label("Number of Row Constraints");
        rowConsOfGameLb.setFont(new Font(24));
        rowConsOfGameLb.setAlignment(Pos.CENTER);

        TextField rowConsOfGame = new TextField();
        rowConsOfGame.setFont(new Font(24));
        rowConsOfGame.setPrefColumnCount(2);

        Label colConsOfGameLb = new Label("Number of Column Constraints");
        colConsOfGameLb.setFont(new Font(24));
        colConsOfGameLb.setAlignment(Pos.CENTER);

        TextField colConsOfGame = new TextField();
        colConsOfGame.setFont(new Font(24));
        colConsOfGame.setPrefColumnCount(2);

        vbox.getChildren().addAll(sizeOfGameLb, rowConsOfGameLb, colConsOfGameLb);
        textvbox.getChildren().addAll(gameSize, rowConsOfGame, colConsOfGame);

        Button back = new Button("Back");
        back.setFont(new Font(24));
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = gameModeSelecPane();
                stage.setTitle("Futoshiki Gamemodes");
                stage.setScene(new Scene(root, 450, 450));
            }

        });

        Button create = new Button("Create");
        create.setFont(new Font(24));
        create.setAlignment(Pos.CENTER);

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root;
                    ArrayList<String> list = new ArrayList<>();

                    for (Node node : textvbox.getChildren()) {
                        if (node instanceof TextField) {
                            list.add(((TextField) node).getText());

                        }

                    }

                    root = gamePane(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));

                    stage.setTitle("Futoshiki Custom");
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            saveAndLoad.saveGame("Custom", game);
                        }

                    });
                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, game);
                            
                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);

                    mb.getMenus()
                            .addAll(fileMenu);

                    BorderPane bp = new BorderPane();
                    bp.setTop(mb);
                    bp.setCenter(root);

                    stage.setScene(new Scene(bp));
                } catch (IOException ex) {
                    Logger.getLogger(FutoshikiFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        bottomHbox.getChildren().addAll(back, create);
        bottomHbox.setPadding(new Insets(0, 300, 0, 300));
        bp.setCenter(vbox);
        bp.setRight(textvbox);
        bp.setBottom(bottomHbox);
        return bp;
    }

    /*
    Pane for displaying the loss menus
    */
    public Pane lossMenu() {
        Label lb = new Label("Game Over");
        lb.setFont(new Font(24));
        lb.setPadding(new Insets(20, 20, 20, 20));

        Button rematch = new Button("Rematch");
        rematch.setFont(new Font(20));
        rematch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Pane root = gamePane(game.getSize(), game.getSize() * 2, game.getSize() * 2);
                    stage.setTitle("Futoshiki");
                    MenuBar mb = new MenuBar();
                    Menu fileMenu = new Menu("File");
                    Menu helpMenu = new Menu("Help");

                    MenuItem saveItem = new MenuItem("Save");
                    saveItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Saving");
                            if(game.getSize()==3){
                                saveAndLoad.saveGame("Easy", game);
                            } else if(game.getSize()==5){
                                saveAndLoad.saveGame("Medium", game);
                            } else if(game.getSize()==10){
                                saveAndLoad.saveGame("Hard", game);
                            }else{
                                saveAndLoad.saveGame("Custom", game);
                            }
                        }

                    });
                    MenuItem saveAsItem = new MenuItem("Save as");
                    saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            saveAndLoad.saveGameAs(stage, game);
                        }
                    });

                    fileMenu.getItems()
                            .addAll(saveItem, saveAsItem);

                    MenuItem giveHint = new MenuItem("Give Hint!");
                    giveHint.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            int i = rnd.nextInt(game.getSize());
                            int j = rnd.nextInt(game.getSize());
                            while (game.getGrid()[i][j] != null) {
                                i = rnd.nextInt(game.getSize());
                                j = rnd.nextInt(game.getSize());
                            }
                            int num = game.getAnswer()[i][j].getNumber();
                            hintAlert(num, j, i);
                        }

                    });

                    MenuItem solve = new MenuItem("Solve!");

                    helpMenu.getItems().addAll(giveHint, solve);

                    mb.getMenus()
                            .addAll(fileMenu, helpMenu);

                    BorderPane bp = new BorderPane();

                    bp.setCenter(root);
                    bp.setTop(mb);
                    stage.setScene(new Scene(bp));

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Button backToMenu = new Button("Back To Menu");
        backToMenu.setFont(new Font(20));
        backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                root = mainMenuPane();
                stage.setTitle("Futoshiki");
                stage.setScene(new Scene(root, 450, 450));
                mp.changeMusicToMainMenu();
            }

        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(backToMenu, rematch);
        VBox vBox = new VBox();

        GridPane answerForPuzzle = new GridPane();
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                Label numberLB = new Label();
                numberLB.setText("" + game.getAnswer()[j][i].getNumber());
                answerForPuzzle.add(numberLB, j, i);
                numberLB.setGraphic(graphics.gameOverBoxImage());
                numberLB.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                numberLB.setPadding(new Insets(5, 5, 5, 5));
            }
        }
        Label answerLB = new Label("Answer for grid");
        answerLB.setPadding(new Insets(10, 0, 10, 0));
        answerLB.setFont(new Font(18));
        vBox.getChildren().addAll(hBox, answerLB, answerForPuzzle);
        lb.setPadding(new Insets(10, 20, 10, 20));
        vBox.setPadding(new Insets(10, 20, 10, 20));

        BorderPane bp = new BorderPane();
        bp.setCenter(vBox);
        bp.setTop(lb);

        return bp;

    }

    /*
    Pane to display the win menu
    */
    public Pane winMenu() {
        Label winLB = new Label("Congratulations you finished the puzzle!");
        winLB.setFont(new Font(24));
        Button backToMainMenu = new Button("Back to Main Menu");
        backToMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                mp.changeMusicToMainMenu();
                root = mainMenuPane();
                stage.setTitle("Futoshiki");
                stage.setScene(new Scene(root, 450, 450));
            }

        });
        backToMainMenu.setFont(new Font(20));
        backToMainMenu.setMaxWidth(Double.MAX_VALUE);
        Button newGame = new Button("New Game");
        newGame.setFont(new Font(20));
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                mp.changeMusicToMainMenu();
                root = gameModeSelecPane();
                stage.setTitle("Futoshiki");
                stage.setScene(new Scene(root, 450, 450));
            }

        });
        newGame.setMaxWidth(Double.MAX_VALUE);
        VBox vBox = new VBox();
        GridPane gp = new GridPane();
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                Label numberLB = new Label();
                numberLB.setText("" + game.getAnswer()[j][i].getNumber());
                gp.add(numberLB, j, i);
                numberLB.setGraphic(graphics.gameOverBoxImage());
                numberLB.setContentDisplay(ContentDisplay.TEXT_ONLY.CENTER);
                numberLB.setPadding(new Insets(5, 5, 5, 5));
            }
        }
        VBox futo = new VBox();

        Label yourGameLB = new Label("Your futoshiki");
        yourGameLB.setFont(new Font(18));
        futo.getChildren().addAll(yourGameLB, gp);

        vBox.getChildren().addAll(newGame, backToMainMenu);
        vBox.setPadding(new Insets(20, 100, 10, 100));
        BorderPane bp = new BorderPane();
        winLB.setPadding(new Insets(20, 50, 20, 50));
        vBox.getChildren().add(futo);
        bp.setTop(winLB);
        bp.setCenter(vBox);

        return bp;
    }

    /*
    Popup window for the alert to the user for invalid change
    */
    @FXML
    private void invalidChangeAlert() {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Invalid change");
        al.setContentText("You can't change this square");
        al.setHeaderText(null);
        al.showAndWait();
    }

    /*
    Popup window for hint alert
    */
    @FXML
    private void hintAlert(int num, int row, int col) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Hint");
        al.setContentText(num + ": goes in column: " + (1 + row) + " and in row: " + (1 + col));
        al.setHeaderText(null);
        al.showAndWait();
    }

}
