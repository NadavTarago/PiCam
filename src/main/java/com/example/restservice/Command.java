package com.example.restservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Command {

    public static final String DEFAULT_WIDE = "1280";
    public static final String DEFAULT_HEIGHT = "720";
    public static final String DEFAULT_TIMEOUT = "100";


    public static final String SUDO = "sudo ";
    public static final String RASPISTILL = "raspistill ";
    public static final String OUTPUT_ENABLE = "-o ";
    public static final String PATH_TO_SAVE = "~/Pictures/";
    public static final String FILE_NAME = "new_image.jpg ";
    public static final String WIDE_HIEGHT = "-w "+DEFAULT_WIDE +" -h "+DEFAULT_HEIGHT+" ";
    public static final String TIMEOUT = "-t "+DEFAULT_TIMEOUT+" ";

    public String takePicCommand;



    public Command (){
        takePicCommand = SUDO+RASPISTILL+OUTPUT_ENABLE+PATH_TO_SAVE+FILE_NAME+WIDE_HIEGHT+TIMEOUT;
    }


    public boolean run(){

        try {
            Process proc = Runtime.getRuntime().exec(takePicCommand);
            // Read the output

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }





}
