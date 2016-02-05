//package edu.byuh.cis.cs203.bw2;
//
//import android.os.Handler;
//import android.os.Message;
//
//import java.util.ArrayList;
//
///**
// * Created by steve on 1/20/2016.
// */
//public class Timer extends Handler {
//    private ArrayList<TickListener> listeners;
//
//    public Timer(){
////        listeners = new TickListener();
//        sendMessageDelayed(obtainMessage(), 50);
//    }
//
//    public void handleMessage(Message m){
//        //code goes here
//        for(TickListener t : listeners){
//            t.tick();
//        }
//        sendMessageDelayed(obtainMessage(), 100);
//    }
//
//    public void subscribe(TickL istener t){
//        listeners.add(t);
//    }
//
//    public void unSubscribe(TickListener t){
//        listeners.remove(t);
//        //only things that need to be deregistered are the missiles and depth charges.
//    }
//
//    //now in gameView you instantiate a plane, then do timer.subscribe(plane);
//
//}
