
package main;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import linkStart.LinkStart;

import java.io.File;
import java.sql.Time;

public class Main extends Application{
  //主题色：
  Color mainColor10=Color.rgb(18,32,66,1);
  Color mainColor11=Color.rgb(18,32,90,0.7);
  Color mainColor12=Color.rgb(18,32,90,0.4);
  /*Color mainColor10=Color.rgb(40,62,173,1);
  Color mainColor11=Color.rgb(40,62,173,0.7);
  Color mainColor12=Color.rgb(40,62,173,0.4);*/
  Color mainColor20=Color.rgb(155,177,210);
  Color mainColor21=Color.rgb(155,177,210,0.7);
  Color mainColor22=Color.rgb(155,177,210,0.4);
  /*备选按钮icon：
  ImageView playIcon=new ImageView("play.PNG");
  ImageView closeIcon=new ImageView("close.PNG");*/
  //白天黑夜：
  Color backColor=Color.rgb(18,32,66);
  Rectangle day=new Rectangle(1173,711,backColor);
  FadeTransition dayFade=new FadeTransition(Duration.millis(100000),day);
  //模式界面雪：
  Circle[] snowDrop=new Circle[100];
  public void easySnow(){
    int i=0;
    for(Circle snow:snowDrop){
      snowDrop[i]=new Circle(2);
      snowDrop[i].setFill(Color.WHITE);
      snowDrop[i].setCenterX((Math.random()*600)+600);
      snowDrop[i].setCenterY((Math.random()*-700));
      beginPane.getChildren().add(snowDrop[i]);
      i++;
    }
    int d=0;
    for(Circle snow:snowDrop){
      d+=50;
      Line moveLine=new Line(snow.getCenterX(),snow.getCenterY(),snow.getCenterX(),430);
      PathTransition snowMove=new PathTransition(Duration.millis(3000),moveLine,snow);
      snowMove.setDelay(Duration.millis(d));
      snowMove.setCycleCount(Animation.INDEFINITE);
      snowMove.play();
    }
  }

  //开始方法：
  private void letsBegin(String mode,Rectangle node,Node button){
    rainButton.getChildren().remove(rain);
    snowButton.getChildren().remove(snow);
    rainSnowButton.getChildren().remove(rainSnow);
    for(Circle eSnow:snowDrop)beginPane.getChildren().remove(eSnow);
    //按钮放大：
    /*KeyValue sssX=new KeyValue(node.heightProperty(),100);
    KeyValue sssY=new KeyValue(node.widthProperty(),100);
    KeyFrame sss=new KeyFrame(Duration.millis(500),sssX,sssY);*/
    KeyValue bigValueX=new KeyValue(node.heightProperty(),1500,Interpolator.EASE_OUT);
    KeyValue bigValueY=new KeyValue(node.widthProperty(),1500,Interpolator.EASE_OUT);
    KeyValue bigValueXx=new KeyValue(button.layoutXProperty(),button.getLayoutX()-1000,Interpolator.EASE_OUT);
    KeyValue bigValueYy=new KeyValue(button.layoutYProperty(),button.getLayoutY()-1000,Interpolator.EASE_OUT);
    KeyFrame bigFrame=new KeyFrame(Duration.millis(500),new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        beginPane.getChildren().clear();
        beginPane.getChildren().add(day);
        beginPane.getChildren().addAll(aboutButton,buttonBox);
        easyFade(aboutButton);
        easyFade(buttonBox);
        e.setFont(new Font(15));
        exitGameButton.setWidth(125);
        exitGameButton.setHeight(35);
        buttonBox.setLayoutX(24);
        buttonBox.setLayoutY(20);
        if(mode.equals("snow"))new LinkStart(beginPane).beginSnow();
        else if(mode.equals("rain"))new LinkStart(beginPane).beginRain();
        else if(mode.equals("rainSnow")){
          new LinkStart(beginPane).beginRain();
          new LinkStart(beginPane).beginSnow();
        }
        Rectangle cloudRec =new Rectangle(2000,-300,mainColor21);
        cloudRec.setLayoutX(-500);
        cloudRec.setLayoutY(-50);
        GaussianBlur ett=new GaussianBlur(500);
        cloudRec.setEffect(ett);
        /*KeyValue cloudDownValue=new KeyValue(cloudRec.layoutYProperty(),500);
        KeyFrame cloudDownFrame=new KeyFrame(Duration.millis(1000),cloudDownValue);
        Timeline cloudDownLine=new Timeline(cloudDownFrame);
        cloudDownLine.play();*/
        beginPane.getChildren().add(1, cloudRec);
        dayFade.setFromValue(1);
        dayFade.setToValue(0);
        dayFade.setAutoReverse(true);
        dayFade.setCycleCount(Animation.INDEFINITE);
        dayFade.play();
      }
    },bigValueX,bigValueY,bigValueXx,bigValueYy);
    Timeline bigLine=new Timeline(bigFrame);
    bigLine.play();
//        beginPane.setStyle("-fx-background-color:rgb(18,32,66)");
  }
  //按钮反应方法：
  private void fadeButtonReaction(Shape node,Text text,final FadeTransition fade) throws NullPointerException{
    text.setOnMouseEntered(event -> {
      text.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
      fade.pause();
    });
    node.setOnMouseEntered(event -> {
      node.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
      fade.pause();
    });
    node.setOnMouseExited(event -> {
      node.setCursor(Cursor.HAND);
      node.setFill(mainColor10);
      fade.play();
    });
    text.setOnMousePressed(event -> node.setFill(mainColor12));
    node.setOnMousePressed(event -> node.setFill(mainColor12));
    node.setOnMouseReleased(event -> node.setFill(mainColor10));
  }
  private void nonFadeButtonReaction(Rectangle node,Text text){
    text.setOnMouseEntered(event -> {
      text.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
    });
    node.setOnMouseEntered(event -> {
      node.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
    });
    node.setOnMouseExited(event -> {
      node.setCursor(Cursor.HAND);
      node.setFill(mainColor10);
    });
    text.setOnMousePressed(event -> node.setFill(mainColor12));
    node.setOnMousePressed(event -> node.setFill(mainColor12));
    node.setOnMouseReleased(event -> node.setFill(mainColor10));
  }
  private void floatNonFadeButtonReaction(Rectangle node,Text text,DropShadow e){
    KeyValue toBigShapeX=new KeyValue(node.widthProperty(),110);
    KeyValue toBigShapeY=new KeyValue(node.heightProperty(),110);
    KeyValue toShadow=new KeyValue(e.radiusProperty(),50);
    KeyValue toSmallShapeX=new KeyValue(node.widthProperty(),100);
    KeyValue toSmallShapeY=new KeyValue(node.heightProperty(),100);
    KeyValue toLeShadow=new KeyValue(e.radiusProperty(),10);
    text.setOnMouseEntered(event -> {
      text.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
      KeyFrame bigFrame=new KeyFrame(Duration.millis(100),toBigShapeY,toBigShapeX,toShadow);
      Timeline bigLine=new Timeline(bigFrame);
      bigLine.play();
    });
    node.setOnMouseEntered(event -> {
      node.setCursor(Cursor.HAND);
      node.setFill(mainColor11);
      KeyFrame bigFrame=new KeyFrame(Duration.millis(100),toBigShapeY,toBigShapeX,toShadow);
      Timeline bigLine=new Timeline(bigFrame);
      bigLine.play();
    });
    node.setOnMouseExited(event -> {
      //if(event.getX()<=node.getLayoutX()&&event.getY()<=node.getLayoutY()&&event.getX()>node.getLayoutX()+100&&event.getY()>node.getLayoutY()+100){
      node.setCursor(Cursor.DEFAULT);
      node.setFill(mainColor10);
      KeyFrame smallFrame=new KeyFrame(Duration.millis(100),toSmallShapeY,toSmallShapeX,toLeShadow);
      Timeline smallLine=new Timeline(smallFrame);
      smallLine.play();
    });
    text.setOnMousePressed(event -> node.setFill(mainColor12));
    text.setOnMouseReleased(event -> node.setFill(mainColor10));
    node.setOnMousePressed(event -> node.setFill(mainColor12));
    node.setOnMouseReleased(event -> node.setFill(mainColor10));
  }
  //关于界面：
  private void about(){
    ImageView javaFX=new ImageView("JavaFX.PNG");
    ImageView aboutLogo=new ImageView(logo1);
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
  Line recMoveLine=new Line(500,500,-800,500);
  /*private PathTransition recMove(Node node){
      PathTransition move=new PathTransition(Duration.millis(1000),recMoveLine,node);
      move.play();
      return move;
  }*/
  private Timeline recMove(Node node,double endValue){
    KeyValue moveValue=new KeyValue(node.layoutXProperty(),endValue,Interpolator.EASE_OUT);
    KeyFrame moveFrame=new KeyFrame(Duration.millis(800),moveValue);
    Timeline moveLine=new Timeline(moveFrame);
    moveLine.play();
    return moveLine;
  }
  private PathTransition shortChange(Node node,double delay){
    PathTransition move=new PathTransition(Duration.millis(2000),shortLine,node);
    move.setDelay(Duration.millis(delay));
    move.setCycleCount(Animation.INDEFINITE);
    move.setAutoReverse(true);
    move.play();
    return move;
  }
  //Logo旋转动画：
  private RotateTransition logoRotate(Node node){
    RotateTransition rotate=new RotateTransition(Duration.millis(6000),node);
    rotate.setFromAngle(0);
    rotate.setToAngle(720);
    rotate.setAutoReverse(true);
    rotate.setCycleCount(Animation.INDEFINITE);
    rotate.play();
    return rotate;
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
  //按钮阴影：
  DropShadow et1 =new DropShadow(10,mainColor10);
  DropShadow et2 =new DropShadow(10,mainColor10);
  DropShadow et3 =new DropShadow(10,mainColor10);
  //开始后方法：
  private void startStage(){
    recMove(rec1,-800);
    recMove(rec2,-720);
    recMove(rec3,-640);
    beginPane.getChildren().remove(boj);
    buttonBox.getChildren().remove(button1);
    beginPane.getChildren().addAll(gameModeText, rainButton, rainSnowButton, snowButton);
    rainRec.setEffect(et1);
    snowRec.setEffect(et2);
    rainSnowRec.setEffect(et3);
    easyFade(rain);
    easyFade(snow);
    easyFade(rainSnow);
    easyFade(rainRec);
    easyFade(snowRec);
    easyFade(rainSnowRec);
    easySnow();
  }
  //关于按钮：
  Rectangle aboutRec=new Rectangle(125,35);
  Text about=new Text("关于");
  StackPane aboutButton=new StackPane(aboutRec,about);
  //第二页面文字：
  Text gameModeText=new Text("选择你的模式");
  //第二页面模式按钮：
  Rectangle rainRec =new Rectangle(100,100);
  Rectangle snowRec =new Rectangle(100,100);
  Rectangle rainSnowRec =new Rectangle(100,100);
  Text rain =new Text("雨");
  Text snow =new Text("雪");
  Text rainSnow =new Text("雨夹雪");
  StackPane rainButton=new StackPane(rainRec,rain);
  StackPane snowButton=new StackPane(snowRec,snow);
  StackPane rainSnowButton=new StackPane(rainSnowRec,rainSnow);
  //Logo资源：
  Image logo1=new Image("logo1.PNG");
  Image logo0=new Image("logo0.PNG");
  ImageView mainLogo1 =new ImageView(logo1);
  ImageView mainLogo0 =new ImageView(logo0);
  //主页面矩形：
  Rectangle rec1=new Rectangle(1000,1000);
  Rectangle rec2=new Rectangle(1000,1000);
  Rectangle rec3=new Rectangle(1000,1000);
  //游戏按钮：
  Rectangle beginGameButton=new Rectangle(210,50,mainColor10);
  Rectangle exitGameButton=new Rectangle(210,50,mainColor10);
  Rectangle beginGameButtonArc=new Rectangle(216,56,Color.PURPLE);
  Text b=new Text("开始");
  Text e=new Text("关闭");
  StackPane button1=new StackPane(beginGameButtonArc,beginGameButton,b);
  StackPane button2=new StackPane(exitGameButton,e);
  HBox buttonBox=new HBox(button1,button2);
  //Based on JavaFX:
  Text boj=new Text("Based on JavaFX");
  //面板：
  Pane beginPane=new Pane(rec1,rec2,rec3,buttonBox, mainLogo0,mainLogo1,boj);
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage) throws InterruptedException{
    /*//开始按钮设置：
    playIcon.setPreserveRatio(true);
    playIcon.setFitHeight(30);
    closeIcon.setPreserveRatio(true);
    closeIcon.setFitHeight(30);*/
    //面板设置：
    beginPane.setPrefSize(1170,711);
    //按钮设置：
    b.setFill(Color.WHITE);
    e.setFill(Color.WHITE);
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
    exitGameButton.setStroke(mainColor20);
    boj.setFill(mainColor10);
    boj.setFont(new Font("Gilroy",20));
    boj.setLayoutX(80);
    boj.setLayoutY(500);
    about.setFill(Color.WHITE);
    about.setFont(new Font(15));
    aboutRec.setFill(mainColor10);
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
    rec1.setLayoutX(715);
    rec1.setLayoutY(-150);
    rec2.setLayoutX(615);
    rec2.setLayoutY(-150);
    rec3.setLayoutX(515);
    rec3.setLayoutY(-150);
    //第二界面按钮设置：
    rainRec.setFill(backColor);
    snowRec.setFill(backColor);
    rainSnowRec.setFill(backColor);
    rainButton.setLayoutX(680);
    rainButton.setLayoutY(300);
    snowButton.setLayoutX(830);
    snowButton.setLayoutY(300);
    rainSnowButton.setLayoutX(980);
    rainSnowButton.setLayoutY(300);
    rainRec.setArcWidth(50);
    rainRec.setArcHeight(50);
    snowRec.setArcWidth(50);
    snowRec.setArcHeight(50);
    rainSnowRec.setArcWidth(50);
    rainSnowRec.setArcHeight(50);
    //Logo设置：
    mainLogo1.setLayoutY(160);
    mainLogo1.setLayoutX(160);
    mainLogo1.setPreserveRatio(true);
    mainLogo1.setFitWidth(200);
    mainLogo0.setPreserveRatio(true);
    mainLogo0.setFitWidth(100);
    mainLogo0.setLayoutX(35);
    mainLogo0.setLayoutY(170);
    //游戏模式界面：
    rain.setFill(Color.WHITE);
    snow.setFill(Color.WHITE);
    rainSnow.setFill(Color.WHITE);
    rain.setFont(new Font(35));
    snow.setFont(new Font(35));
    rainSnow.setFont(new Font(25));
    rain.setLayoutX(700);
    rain.setLayoutY(220);
    snow.setLayoutX(747);
    snow.setLayoutY(510);
    rainSnow.setLayoutX(970);
    rainSnow.setLayoutY(407);
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
    //矩形推动动画：
    final PathTransition rec1Change=shortChange(rec1,0);
    final PathTransition rec2Change=shortChange(rec2,500);
    final PathTransition rec3Change=shortChange(rec3,1000);
    //开启Logo动画：
    logoRotate(mainLogo0);
    //备选开始界面：
    /*//Based On JFX begin interface:
    ImageView jFX=new ImageView("jfxlogo.PNG");
    jFX.setPreserveRatio(true);
    jFX.setFitWidth(1250);
    beginPane.getChildren().add(jFX);*/
    //渐变开始：
    Rectangle beginFadeRec=new Rectangle(2000,2000,Color.WHITE);
    beginPane.getChildren().add(beginFadeRec);
    FadeTransition beginFade=new FadeTransition(Duration.millis(1000),beginFadeRec);
    beginFade.setDelay(Duration.millis(500));
    beginFade.setFromValue(1);
    beginFade.setToValue(0);
    beginFade.play();
    //设置舞台：
    Scene scene=new Scene(beginPane);
    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(scene);
    primaryStage.show();
    //设置监听器：
    beginPane.setOnMouseMoved(event -> {
      if(beginFade.getStatus() == Animation.Status.STOPPED)beginPane.getChildren().remove(beginFadeRec);
    });
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

    floatNonFadeButtonReaction(rainRec, rain,et1);
    floatNonFadeButtonReaction(snowRec, snow,et2);
    floatNonFadeButtonReaction(rainSnowRec, rainSnow,et3);

    rainRec.setOnMouseClicked(event -> letsBegin("rain",rainRec,rainButton));
    rain.setOnMouseClicked(event -> letsBegin("rain",rainRec,rainButton));
    snowRec.setOnMouseClicked(event -> letsBegin("snow",snowRec,snowButton));
    snow.setOnMouseClicked(event -> letsBegin("snow",snowRec,snowButton));
    rainSnowRec.setOnMouseClicked(event -> letsBegin("rainSnow",rainSnowRec,rainSnowButton));
    rainSnow.setOnMouseClicked(event -> letsBegin("rainSnow",rainSnowRec,rainSnowButton));

    nonFadeButtonReaction(aboutRec,about);
    aboutRec.setOnMouseClicked(event -> about());
    about.setOnMouseClicked(event -> about());
    //备选动画：
    /*beginPane.setOnMouseClicked(event -> {
      KeyValue jFXValue=new KeyValue(jFX.layoutYProperty(),-2000);
      KeyValue jFXSubValue=new KeyValue(jFX.layoutYProperty(),20);
      KeyFrame jFXSubFrame=new KeyFrame(Duration.millis(500),jFXSubValue);
      KeyFrame jFXFrame=new KeyFrame(Duration.millis(1000),events -> beginPane.getChildren().remove(jFX),jFXValue);
      Timeline jFXLine=new Timeline(jFXSubFrame,jFXFrame);
      jFXLine.play();
    });*/

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
