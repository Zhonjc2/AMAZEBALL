package linkStart;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlaneWar{
    Circle baseCircle=new Circle(30);
    Circle[] aimCircle;
    Pane mainPane;
    int score;
    int aimNum;
//    String a;

    public PlaneWar(Pane pane,int aimNum){
        aimCircle=new Circle[aimNum];
        this.aimNum=aimNum;
        mainPane=pane;
        baseCircle.setFill(Color.WHITE);
        baseCircle.setCenterX(500);
        baseCircle.setCenterY(650);
        mainPane.getChildren().add(baseCircle);
        int p=0;
        for(Circle aim:aimCircle){
            // a=new String("1234");
            // aim=new Circle(2);
            // 这里aim作为局部变量，在for循环完毕后便会消失，消失后定义自然会消失。
            // 注意，foreach循环最好不要为数组元素赋值，因为出了foreach循环，所有赋值都无效。
            // 但是可以采用数组加下标的形式赋值。
            aimCircle[p]=new Circle(20);//正确用法，连这也反应不过来吗，aim是局部的啊。
            aimCircle[p].setCenterX(Math.random()*1200);
            aimCircle[p].setCenterY(Math.random()*-30000);
            aimCircle[p].setFill(Color.rgb(137,192,217));
            mainPane.getChildren().add(aimCircle[p]);
            p++;
            //在此循环内，除了赋值都可以将aimCircle[p]替换为aim。
        }
    }

    public void beaterTo(Circle beater) {
        beater.setCenterX(baseCircle.getCenterX());
        beater.setCenterY(530);
        Line beaterLine = new Line(beater.getCenterX(), beater.getCenterY(), beater.getCenterX(), beater.getCenterY() - 600);
        PathTransition move = new PathTransition(Duration.millis(100), beaterLine, beater);
        mainPane.getChildren().add(beater);
        move.play();
        if(aimBeaten(beaterLine))score+=10;
    }

    public boolean aimBeaten(Line beaterLine){
        int i;
        for(Circle aim:aimCircle){
            if(beaterLine.getStartX()>aim.getCenterX()-aim.getRadius() && beaterLine.getStartX()<aim.getCenterX()+aim.getRadius()){
                mainPane.getChildren().remove(aim);
                return true;
            }
        }
        return false;
    }

    //准备编写
//    public boolean aimIsBeyond(Line aimLine,Line beaterLine,PathTransition aimMove){
//        for(Circle aim:aimCircle){
//            if(aimMove.)return true;
//        }
//        return false;
//    }

    public void aimFader(Circle aim,int delay){
        FadeTransition aimFader=new FadeTransition(Duration.millis(1000),aim);
        aimFader.setFromValue(0.2);
        aimFader.setToValue(1);
        aimFader.setDelay(Duration.millis(delay));
        aimFader.setCycleCount(Animation.INDEFINITE);
        aimFader.setAutoReverse(true);
        aimFader.play();
    }
    
    public void aimDrop(Circle aim,int delay){
        System.out.println(aim.getRadius());
        Line aimMoveLine=new Line(aim.getCenterX(),aim.getCenterY(),aim.getCenterX(),600);
        PathTransition moves=new PathTransition(Duration.millis((600-aim.getCenterY())*2),aimMoveLine,aim);
        moves.setDelay(Duration.millis(delay));
        moves.play();
    }

    public void begin(){
        aimCircle=new Circle[aimNum];
        int i=0;
        int p=0;
        for(Circle aim:aimCircle){
            i+=100;
            aimFader(aimCircle[p],i);
            aimDrop(aimCircle[p],i);
            p++;
        }
        baseCircle.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case A:baseCircle.setCenterX(baseCircle.getCenterX()-20);break;
                case D:baseCircle.setCenterX(baseCircle.getCenterX()+20);break;
                case SPACE:{
                    Circle beater=new Circle(2);
                    beater.setFill(Color.rgb(140,182,247));
                    beaterTo(beater);
                }
            }
        });

        baseCircle.requestFocus();




    }

    public static void main(String[] args){
        PlaneWar pw=new PlaneWar(new Pane(),100);
        for(Circle aim:pw.aimCircle)System.out.println(aim.getCenterY());
        pw.begin();
//        System.out.println(a.aimCircle[0].getRadius());
    }
}
