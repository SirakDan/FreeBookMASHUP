/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

//import twitter4j.Twitter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


/**
 *
 * @author Daniel
 */
public class MyTwitter {
    String[] authStrings;
    public void publicaTest(String text) {
        Twitter twitter = TwitterFactory.getSingleton();
        try {
            Status status = twitter.updateStatus(text);
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(MyTwitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getAuth(){
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("C8iAYESEoYSkcswuTzT8tZvBA", "T1pA10hL7mlfAzXXSZ7lJxHuYnN1PcO7XuuY5F3oS7CgPgNbt2");
        RequestToken requestToken = null;
        try {
            requestToken = twitter.getOAuthRequestToken();

            
        } catch (TwitterException ex) {
            System.err.println("Error al obtener RequestToken" + ex.getMessage());
        }
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if(readAccessToken()){
            
            accessToken = new AccessToken(authStrings[1], authStrings[2], Long.parseLong(authStrings[0]));
            twitter.setOAuthAccessToken(accessToken);

        }else{
            while (null == accessToken) {
                try {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) or just hit enter.[PIN]:");
                    String pin = "";
                    try {
                        pin = br.readLine();
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(MyTwitter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try{
                        if(pin.length() > 0){
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        }else{
                            accessToken = twitter.getOAuthAccessToken();

                        }
                    } catch (TwitterException te) {
                        if(401 == te.getStatusCode()){
                            System.out.println("Unable to get the access token.");
                        }else{
                            te.printStackTrace();
                        }
                    }
                    //persist to the accessToken for future reference.
                    storeAccessToken((int) twitter.verifyCredentials().getId(), accessToken);
                } catch (TwitterException ex) {
                    java.util.logging.Logger.getLogger(MyTwitter.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
    }
    private ArrayList<String> getAccessToken(){
        String accessToken = "";
        return null;
    }
    private void storeAccessToken(int useId, AccessToken accessToken){
        //store accessToken.getToken()
        try {

            String content = useId + "\n" + accessToken.getToken() + "\n" + accessToken.getTokenSecret();

            File file = new File("properties.prop");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                    file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
                e.printStackTrace();
        }
        //store accessToken.getTokenSecret()
        
    }
    private boolean readAccessToken(){
        File file = new File("properties.prop");
        FileReader fr = null;
        BufferedReader br = null;
        authStrings = new String[3];
        if(!file.exists()) return false;
        
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            String linea;
            int i = 0;
            while((linea=br.readLine())!=null) authStrings[i++] = linea;      
            
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MyTwitter.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                br.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(MyTwitter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
