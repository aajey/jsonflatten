package com.jsonflatten;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;


public class App {

    public static void main(String[] args) {

        FlattenJsonInterface fltJson = new FlattenJsonIgnoreArrayImpl();
        while(true)
        {
            System.out.println("Press 1 to flatten json using string");
            System.out.println("Press 2 to flatten json using file");
            System.out.println("Press any key to exit (except 1 or 2)");

            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            System.out.println("You entered string "+s);
            try {
                if (s.equals("1")) {
                    flattenStringJson(fltJson, scanner);
                } else if (s.equals("2")) {
                    flattenJsonUsingFile(fltJson, scanner);
                } else {
                    break;
                }
            }
            catch (JSONException jsonEx)
            {
                System.out.println("Provide valid JSON:"+jsonEx.getMessage());
            }
        }
    }

    public static void flattenStringJson(FlattenJsonInterface fltJson, Scanner scanner) throws JSONException
    {
        System.out.println("Enter your JSONString to flatten");
        String json = scanner.nextLine();
        System.out.println("You entered:"+json);
        System.out.println("Flattened JSON");
        System.out.println(fltJson.flatten(new JSONObject(json)).toString(3));
    }

    public static void flattenJsonUsingFile(FlattenJsonInterface fltJson, Scanner scanner) throws JSONException
    {
        System.out.println("Enter your JSON file absolute path to flatten");
        String filePath = scanner.nextLine();
        System.out.println("You entered:"+filePath);
        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(filePath));
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine);
                readLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("error reading file:"+e.getMessage());
        } finally {
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Flattened JSON");
        System.out.println(fltJson.flatten(new JSONObject(stringBuilder.toString())).toString(3));
    }

}
