package linkStart;

import javafx.animation.*;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;

public class LinkStart{
    Circle[] snowDrops;
    Line[] rainDrops;
    Pane mainPane;
    //音频：
    MediaPlayer rainSound=new MediaPlayer(new Media("file:///Users/zhonjc/IdeaProjects/AMAZEBALL/MainInterface/src/rain.mp3"));
    MediaPlayer snowSound=new MediaPlayer(new Media("file:///Users/zhonjc/IdeaProjects/AMAZEBALL/MainInterface/src/snow.mp3"));
    public LinkStart(Pane pane){
        mainPane=pane;
    }

    /*public void beaterTo(Circle beater){
        beater.setCenterX(baseCircle.getCenterX());
        beater.setCenterY(530);
        Line moveLine=new Line(beater.getCenterX(),beater.getCenterY(),beater.getCenterX(),beater.getCenterY()-600);
        PathTransition move=new PathTransition(Duration.millis(1000),moveLine,beater);
        mainPane.getChildren().add(beater);
        move.play();*/
        /*for(Circle aim:aimCircle){
            if(moveLine.getStartX()>aim.getCenterX()-30 && moveLine.getStartX()<aim.getCenterX()+30){
                move.stop();
                moveLine=new Line(beater.getCenterX(),beater.getCenterY(),beater.getCenterX(),aim.getCenterY());
                move=new PathTransition(Duration.millis((baseCircle.getCenterY()-aim.getCenterY())*2),moveLine,beater);
                move.play();
                FadeTransition hide1=new FadeTransition(Duration.millis(500),aim);
                hide1.setFromValue(1);
                hide1.setToValue(0);
                hide1.setDelay(Duration.millis((baseCircle.getCenterY()-aim.getCenterY())*2));
                hide1.play();
                FadeTransition hide2=new FadeTransition(Duration.millis(500),beater);
                hide2.setFromValue(1);
                hide2.setToValue(0);
                hide2.setDelay(Duration.millis((baseCircle.getCenterY()-aim.getCenterY())*1.5));
                hide2.play();
            }
        }*/
//    }
    /*private void setInt(PathTransition moves){
        Stage setStage=new Stage();
        VBox setPane=new VBox();
        setPane.getChildren().add(speedSlider);
        Scene setScene=new Scene(setPane);
        setStage.setScene(setScene);
        setStage.show();
        Button okButton=new Button("确定");
        setPane.getChildren().add(okButton);
        okButton.setOnAction(event -> {
            moves.setDuration(Duration.millis(speedSlider.getValue()));
            moves.setDelay(Duration.millis(speedSlider.getValue()));
            setStage.close();
        });
    }*/
    public void beginSnow(){
        System.out.print("file:"+new File("rain.mp3").toURI().getPath());
        snowSound.play();
        snowSound.setCycleCount(-1);
        snowDrops =new Circle[5000];
        int i=0;
        for(Circle drops: snowDrops){
            i+=50;
            drops=new Circle(2);
            drops.setCenterX(Math.random()*1200);
            drops.setCenterY(Math.random()*-30000);
            drops.setFill(Color.rgb(137,192,217));
            mainPane.getChildren().add(1,drops);
            FadeTransition fader=new FadeTransition(Duration.millis(1000),drops);
            fader.setFromValue(0.2);
            fader.setToValue(1);
            fader.setDelay(Duration.millis(i));
            fader.setCycleCount(Animation.INDEFINITE);
            fader.setAutoReverse(true);
            fader.play();
            /*Glow glow=new Glow(5);
            drops.setEffect(glow);*/
            Line aimMoveLine=new Line(drops.getCenterX(),drops.getCenterY(),drops.getCenterX(),715);//设定无数的最简单方法
            PathTransition moves=new PathTransition(Duration.millis((600-drops.getCenterY())*4),aimMoveLine,drops);
            moves.setCycleCount(Animation.INDEFINITE);
//            moves.setAutoReverse(true);
            moves.setDelay(Duration.millis(i));
            moves.play();
            KeyValue a1=new KeyValue(drops.centerXProperty(),drops.getCenterX()-20);
            KeyValue a2=new KeyValue(drops.centerXProperty(),drops.getCenterX()+20);
            KeyFrame b=new KeyFrame(Duration.millis(2000),a1,a2);
            Timeline c=new Timeline(b);
            c.setCycleCount(Animation.INDEFINITE);
            c.setAutoReverse(true);
            c.play();

            //时间轴动画的原理是改变节点的属性来达到动画的目的。因此，可以用该动画来实现获取aim位置的功能。
            //除此之外，时间轴动画还支持当动画播放完毕后自动触发事件并交由监听器处理，只需要在构造方法里添加即可，太tm好用了，我为什么不早知道这个。
            /*PathTransition letFloat=new PathTransition(Duration.millis(1000),new Line(aim.getCenterX(),aim.getCenterY(),aim.getCenterX()+10,aim.getCenterY()),aim);
            letFloat.setDelay(Duration.millis(i));
            letFloat.setAutoReverse(true);
            letFloat.setCycleCount(Animation.INDEFINITE);
            letFloat.play();*/
            /*Button set=new Button("设置");
            set.setLayoutX(20);
            set.setLayoutY(7);*/
//            mainPane.getChildren().add(set);
            /*set.setOnAction(event -> {
                setInt(moves);
            });*/

        }
        /*baseCircle.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case A:baseCircle.setCenterX(baseCircle.getCenterX()-20);break;
                case D:baseCircle.setCenterX(baseCircle.getCenterX()+20);break;
                case SPACE:{
                    Circle beater=new Circle(2);
                    beater.setFill(Color.rgb(140,182,247));
                    beaterTo(beater);
                }
            }
        });*/

//        baseCircle.requestFocus();
    }
    public void beginRain(){
        rainSound.play();
        rainSound.setCycleCount(-1);
        rainDrops=new Line[10000];
        int i=0;
        for(Line drops:rainDrops){
            double startX=Math.random()*1500;
            double startY=Math.random()*-30000;
            rainDrops[i]=new Line(startX,startY,startX-2,startY+10);
            rainDrops[i].setStroke(Color.rgb(137,192,217));
            rainDrops[i].setStrokeWidth(2);
            mainPane.getChildren().add(1,rainDrops[i]);
            i++;
        }
        i=0;
        for(Line drops:rainDrops){
            i+=10;
            KeyValue moveValueY=new KeyValue(drops.layoutYProperty(),715);
            KeyValue moveValueX=new KeyValue(drops.layoutXProperty(),-143+0.5*drops.getStartY());
            KeyFrame moveFrame=new KeyFrame(Duration.millis(800),moveValueY,moveValueX);//一个关键帧可以有多个关键值
            Timeline move=new Timeline(moveFrame);
            move.setDelay(Duration.millis(i));
            move.setCycleCount(Animation.INDEFINITE);
            move.play();
        }
    }
}
