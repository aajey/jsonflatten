package com.jsonflatten;

import java.util.Scanner;
import org.json.JSONObject;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class App {
    
    public static void main(String[] args) {
		
		FlattenJsonInterface fltJson = new FlattenJsonIgnoreArrayImpl();
		while(true)
		{
			System.out.println();
			System.out.println("Press 1 to flatten json using string");
			System.out.println("Press 2 to flatten json using file");
			System.out.println("Press any key to exit (except 1 or 2)");
			
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
			System.out.println("You entered string "+s);
			
			if(s.equals("1"))
			{
				System.out.println("Enter your JSONString to flatten");
				String json = in.nextLine();
				System.out.println("You entered:"+json);
				
				System.out.println("Flattened JSON");
				System.out.println(fltJson.flatten(new JSONObject(json)).toString(3));
			}
			else if(s.equals("2"))
			{
				System.out.println("Enter your JSON file absolute path to flatten");
				String filePath = in.nextLine();
				System.out.println("You entered:"+filePath);
				StringBuilder stringBuilder = new StringBuilder();
				File file = new File(filePath); 
				FileInputStream fileStream = null;
				BufferedInputStream bufferedStream = null;
				DataInputStream dataStream = null;

				try {
					fileStream = new FileInputStream(file);

					bufferedStream = new BufferedInputStream(fileStream);
					dataStream = new DataInputStream(bufferedStream);

					while (dataStream.available() != 0) {
						stringBuilder.append(dataStream.readLine());
					}

				} catch (IOException e) {
					System.out.println("error reading file");
					e.printStackTrace();
				} finally {
					try {
						fileStream.close();
						bufferedStream.close();
						dataStream.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				 
     			System.out.println("Flattened JSON");
				System.out.println(fltJson.flatten(new JSONObject(stringBuilder.toString())).toString(3));
			}
			else
			{
				break;
			}		
		}	
    }
    
}
