package com.github.xorDash.twitchplaysclone.gba;

import java.io.IOException;
import java.util.Random;

/***
 * @author xor
 ***/
public class VisualBoyAdvanceHandler {
    private Random random = new Random();
    private boolean handleInput = true;

    /***
     * Create a new thread to run visualBoyAdvance in.
     ***/
    public void start(){
        Runnable vbaThread = new VisualBoyAdvanceThread();
        Thread thread = new Thread(vbaThread);
        thread.setName("visualBoyAdvance");
        thread.start();
    }


    /***
     * Send a keypress to the VisualBoyAdvance emulator
     * @param gbaKeyEnum the gbaKeyEnum to send
     ***/
    public void sendKey(GbaKeyEnum gbaKeyEnum){
        try{
            String cmd = "keydown " + gbaKeyEnum.getKeyboardKey();
            runCommand(cmd);
            cmd = "keyup " + gbaKeyEnum.getKeyboardKey();
            runCommand(cmd);
            System.gc();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    /***
     * Internal executing of command to send key to VisualBoyAdvance emulator
     * @param cmd
     * @throws IOException
     * @throws InterruptedException
     ***/
    private void runCommand(String cmd) throws IOException, InterruptedException{
        String command = "/usr/bin/xdotool search --classname ^Visual " + cmd;
        Runtime.getRuntime().exec(command);
    }

}
