package main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import linkStart.LinkStart;
import linkStart.PlaneWar;

public class Main2 extends Application{
    //开始方法：
    private void letsBegin(int snowNum){
        beginPane.getChildren().clear();
        e.setFont(new Font(15));
        exitGameButton.setWidth(125);
        exitGameButton.setHeight(35);
        buttonBox.setLayoutX(24);
        buttonBox.setLayoutY(20);
        beginPane.getChildren().addAll(aboutButton,buttonBox);
        new PlaneWar(beginPane,100).begin();
        beginPane.setStyle("-fx-background-color:rgb(44,56,88)");
    }
    //按钮反应方法：
    private void fadeButtonReaction(Shape node,Text text,final FadeTransition fade) throws NullPointerException{
        text.setOnMouseEntered(event -> {
            text.setCursor(Cursor.HAND);
            node.setFill(mainColor21);
            fade.pause();
        });
        node.setOnMouseEntered(event -> {
            node.setCursor(Cursor.HAND);
            node.setFill(mainColor21);
            fade.pause();
        });
        node.setOnMouseExited(event -> {
            node.setCursor(Cursor.HAND);
            node.setFill(mainColor20);
            fade.play();
        });
        text.setOnMousePressed(event -> node.setFill(mainColor22));
        node.setOnMousePressed(event -> node.setFill(mainColor22));
        node.setOnMouseReleased(event -> node.setFill(mainColor21));
    }
    private void nonFadeButtonReaction(Shape node,Text text){
        text.setOnMouseEntered(event -> {
            text.setCursor(Cursor.HAND);
            node.setFill(mainColor21);
        });
        node.setOnMouseEntered(event -> {
            node.setCursor(Cursor.HAND);
            node.setFill(mainColor21);
        });
        node.setOnMouseExited(event -> {
            node.setCursor(Cursor.HAND);
            node.setFill(mainColor20);
        });
        text.setOnMousePressed(event -> node.setFill(mainColor22));
        node.setOnMousePressed(event -> node.setFill(mainColor22));
        node.setOnMouseReleased(event -> node.setFill(mainColor20));
    }
    //关于界面：
    private void about(){
        ImageView javaFX=new ImageView("JavaFX.PNG");
        ImageView aboutLogo=new ImageView(logo);
        aboutLogo.setPreserveRatio(true);
        aboutLogo.setFitWidth(100);
        javaFX.setPreserveRatio(true);
        javaFX.setFitWidth(300);
        Stage aboutStage=new Stage();
        VBox aboutPane=new VBox(javaFX,new Text("本程序使用 JavaFX 制作\n\n"),aboutLogo);
        aboutPane.setSpacing(20);
        aboutPane.setPadding(new Insets(20,20,20,20));
        aboutPane.setAlignment(Pos.CENTER);
        aboutPane.setStyle("-fx-background-color: WHITE");
        Scene aboutScene=new Scene(aboutPane);
        aboutStage.setScene(aboutScene);
        aboutStage.initStyle(StageStyle.UNDECORATED);
        aboutStage.show();
        aboutPane.setOnMouseClicked(event2 -> aboutStage.close());
    }
    //Line动画方法：
    Line shortLine=new Line(500,500,490,500);
    Line recMoveLine=new Line(500,500,-700,500);
    private PathTransition recMove(Node node){
        PathTransition move=new PathTransition(Duration.millis(1000),recMoveLine,node);
        move.play();
        return move;
    }
    private PathTransition shortChange(Node node,double delay){
        PathTransition move=new PathTransition(Duration.millis(2000),shortLine,node);
        move.setDelay(Duration.millis(delay));
        move.setCycleCount(Animation.INDEFINITE);
        move.setAutoReverse(true);
        move.play();
        return move;
    }
    //Fade动画方法：
    //注意一种动画对象只能用于一个节点
//    FadeTransition recFade=new FadeTransition();
    private FadeTransition fade(Node node,int mill,double from,double to,double delay){
        FadeTransition recFade=new FadeTransition();
        recFade.setDuration(Duration.millis(mill));
        recFade.setDelay(Duration.millis(delay));
        recFade.setFromValue(from);
        recFade.setToValue(to);
        recFade.setNode(node);
        recFade.setCycleCount(Animation.INDEFINITE);
        recFade.setAutoReverse(true);
        recFade.play();
        return recFade;
    }
    private FadeTransition easyFade(Node node){
        FadeTransition cFade=new FadeTransition(Duration.millis(1000));
        cFade.setFromValue(0);
        cFade.setToValue(1);
        cFade.setCycleCount(1);
        cFade.setNode(node);
        cFade.play();
        return cFade;
    }
    //开始后方法：
    private void startStage(){
        recMove(rec1);
        recMove(rec2);
        recMove(rec3);
        beginPane.getChildren().set(1,c4);
        c1.setFill(mainColor20);
        beginPane.getChildren().remove(boj);
        buttonBox.getChildren().remove(button1);
        beginPane.getChildren().addAll(gameModeText,easyMode,midMode,diffMode);
        easyFade(easyMode);
        easyFade(midMode);
        easyFade(diffMode);
    }
    //关于按钮：
    Rectangle aboutRec=new Rectangle(125,35);
    Text about=new Text("关于");
    StackPane aboutButton=new StackPane(aboutRec,about);
    //主页面圆：
    Circle c1=new Circle(150);
    Circle c2=new Circle(100);
    Circle c3=new Circle(50);
    //第二页面文字：
    Text gameModeText=new Text("选择你的模式");
    //第二页面模式按钮：
    Text easyMode=new Text("千球下落");
    Text midMode=new Text("五千下落");
    Text diffMode=new Text("万球下落");
    Circle c4=new Circle(154);
    FadeTransition c4Fade = fade(c4,1000,0,0.4,0);
    //Logo资源：
    Image logo=new Image("snowball.PNG");
    ImageView mainLogo=new ImageView(logo);
    //主页面矩形：
    Rectangle rec1=new Rectangle(1000,1000);
    Rectangle rec2=new Rectangle(1000,1000);
    Rectangle rec3=new Rectangle(1000,1000);
    //主题色：
    Color mainColor10=Color.rgb(31,65,111,1);
    Color mainColor11=Color.rgb(31,65,111,0.4);
    Color mainColor12=Color.rgb(31,65,111,0.1);
    Color mainColor20=Color.rgb(90,181,231);
    Color mainColor21=Color.rgb(90,181,231,0.7);
    Color mainColor22=Color.rgb(90,181,231,0.4);
    //游戏按钮：
    Rectangle beginGameButton=new Rectangle(210,50,mainColor20);
    Rectangle exitGameButton=new Rectangle(210,50,mainColor20);
    Rectangle beginGameButtonArc=new Rectangle(216,56,mainColor10);
    Text b=new Text("开始");
    Text e=new Text("关闭");
    StackPane button1=new StackPane(beginGameButtonArc,beginGameButton,b);
    StackPane button2=new StackPane(exitGameButton,e);
    HBox buttonBox=new HBox(button1,button2);
    //Based on JavaFX:
    Text boj=new Text("Based on JavaFX");
    //面板：
    Pane beginPane=new Pane(rec1,rec2,rec3,c1,c2,c3,buttonBox,mainLogo,boj);
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws InterruptedException{
        //面板设置：
        beginPane.setPrefSize(1170,711);
        //按钮设置：
        b.setFill(mainColor10);
        e.setFill(mainColor10);
        b.setFont(new Font(25));
        e.setFont(new Font(25));
        buttonBox.setSpacing(10);
        buttonBox.setLayoutX(80);
        buttonBox.setLayoutY(540);
        beginGameButton.setArcHeight(20);
        beginGameButton.setArcWidth(20);
        exitGameButton.setArcHeight(20);
        exitGameButton.setArcWidth(20);
        beginGameButtonArc.setArcWidth(24);
        beginGameButtonArc.setArcHeight(24);
//        beginGameButton.setStrokeWidth(3);
//        beginGameButton.setStroke(mainColor10);
        exitGameButton.setStrokeWidth(3);
        exitGameButton.setStroke(mainColor10);
        boj.setFill(mainColor20);
        boj.setFont(new Font("Gilroy",20));
        boj.setLayoutX(80);
        boj.setLayoutY(500);
        about.setFill(mainColor10);
        about.setFont(new Font(15));
        aboutRec.setFill(mainColor20);
        aboutRec.setArcHeight(20);
        aboutRec.setArcWidth(20);
        aboutButton.setLayoutX(1020);
        aboutButton.setLayoutY(20);
        //矩形设置：
        rec1.setFill(mainColor10);
        rec2.setFill(mainColor11);
        rec3.setFill(mainColor12);
        rec1.setRotate(45);
        rec2.setRotate(45);
        rec3.setRotate(45);
        rec1.setLayoutX(515);
        rec1.setLayoutY(-150);
        rec2.setLayoutX(415);
        rec2.setLayoutY(-150);
        rec3.setLayoutX(315);
        rec3.setLayoutY(-150);
        //圆设置：
        c1.setFill(mainColor21);
        c2.setFill(mainColor21);
        c3.setFill(mainColor21);
        c4.setFill(mainColor10);
        c1.setCenterX(800);
        c1.setCenterY(200);
        c2.setCenterX(800);
        c2.setCenterY(500);
        c3.setCenterX(1000);
        c3.setCenterY(400);
        c4.setCenterX(800);
        c4.setCenterY(200);
        //Logo设置：
        mainLogo.setLayoutY(50);
        mainLogo.setLayoutX(80);
        mainLogo.setPreserveRatio(true);
        mainLogo.setFitWidth(350);
        //游戏模式界面：
        easyMode.setFill(mainColor10);
        midMode.setFill(mainColor10);
        diffMode.setFill(mainColor10);
        easyMode.setFont(new Font(50));
        midMode.setFont(new Font(25));
        diffMode.setFont(new Font(15));
        easyMode.setLayoutX(700);
        easyMode.setLayoutY(220);
        midMode.setLayoutX(747);
        midMode.setLayoutY(510);
        diffMode.setLayoutX(970);
        diffMode.setLayoutY(407);
        //设置文字：
        gameModeText.setFont(new Font(30));
        gameModeText.setFill(mainColor20);
        gameModeText.setLayoutX(80);
        gameModeText.setLayoutY(370);
        //设置矩形动画：
        final FadeTransition rec1Fade=fade(rec1,2700,0.4,0.8,0);
        final FadeTransition rec2Fade=fade(rec2,2000,0.4,1,500);
        final FadeTransition rec3Fade=fade(rec3,1300,0.2,1,1000);
        final FadeTransition buttonFade=fade(beginGameButtonArc,1200,0,1,0);
        final PathTransition rec1Change=shortChange(rec1,0);
        final PathTransition rec2Change=shortChange(rec2,500);
        final PathTransition rec3Change=shortChange(rec3,1000);
        //设置舞台：
        Scene scene=new Scene(beginPane);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
        //设置按钮监听器：
        fadeButtonReaction(beginGameButton,b,buttonFade);
        beginGameButton.setOnMouseClicked(event -> {
            rec1Change.pause();
            rec2Change.pause();
            rec3Change.pause();
            startStage();
        });
        b.setOnMouseClicked(event -> {
            rec1Change.pause();
            rec2Change.pause();
            rec3Change.pause();
            startStage();
        });
        nonFadeButtonReaction(exitGameButton,e);
        e.setOnMouseClicked(event -> Platform.exit());
        exitGameButton.setOnMouseClicked(event -> Platform.exit());
        fadeButtonReaction(c1,easyMode,c4Fade);
        nonFadeButtonReaction(c2,midMode);
        nonFadeButtonReaction(c3,diffMode);
        c1.setOnMouseClicked(event -> letsBegin(5000));
        easyMode.setOnMouseClicked(event -> letsBegin(5000));
        c2.setOnMouseClicked(event -> letsBegin(10000));
        midMode.setOnMouseClicked(event -> letsBegin(10000));
        c3.setOnMouseClicked(event -> letsBegin(50000));
        diffMode.setOnMouseClicked(event -> letsBegin(50000));
        nonFadeButtonReaction(aboutRec,about);
        aboutRec.setOnMouseClicked(event -> about());
        about.setOnMouseClicked(event -> about());

        /*c2.setOnMouseClicked(event -> linkStartMid());
        c3.setOnMouseClicked(event -> linkStarDiff());
        easyMode.setOnMouseClicked(event -> linkStartEasy());
        midMode.setOnMouseClicked(event -> linkStartMid());
        diffMode.setOnMouseClicked(event -> linkStarDiff());*/





        /*b.setOnMouseEntered(event ->{
            b.setCursor(Cursor.HAND);
            beginGameButton.setFill(mainColor21);
            buttonFade.stop();
        });
        b.setOnMouseClicked(event ->startStage());*/
        /*e.setOnMouseEntered(event ->{
            e.setCursor(Cursor.HAND);
            exitGameButton.setFill(mainColor21);
        });*/
        /*beginGameButton.setOnMouseEntered(event -> {
            beginGameButton.setCursor(Cursor.HAND);
            beginGameButton.setFill(mainColor21);
            buttonFade.stop();
        });
        beginGameButton.setOnMouseExited(event -> {
            beginGameButton.setCursor(Cursor.DEFAULT);
            beginGameButton.setFill(mainColor20);
            buttonFade.play();
        });*/
        /*exitGameButton.setOnMouseEntered(event -> {
            exitGameButton.setFill(mainColor21);
            exitGameButton.setCursor(Cursor.HAND);
        });*/
        /*exitGameButton.setOnMouseExited(event -> exitGameButton.setFill(mainColor20));*/
        /*beginGameButton.setOnMousePressed(event -> beginGameButton.setFill(mainColor22));
        beginGameButton.setOnMouseReleased(event -> beginGameButton.setFill(mainColor20));*/

    }
}
