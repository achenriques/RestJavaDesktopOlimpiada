package com.example.TareaRest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonConvert {

	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
	
	public static ArrayList<Olimpiada> Json2ArrayOlimpiada(InputStream inputStream)
	{
		Gson gson = new Gson();
		String data = getStringFromInputStream(inputStream);
		
		TypeToken<ArrayList<Olimpiada>> token = new TypeToken<ArrayList<Olimpiada>>() {};
		return gson.fromJson(data, token.getType());
	}
	
	public static ArrayList<SedeJJOO> Json2ArraySede(InputStream inputStream)
	{
		Gson gson = new Gson();
		String data = getStringFromInputStream(inputStream);
		
		TypeToken<ArrayList<SedeJJOO>> token = new TypeToken<ArrayList<SedeJJOO>>() {};
		return gson.fromJson(data, token.getType());
	}
	
	public static ArrayList<ArrayList<String>> Json2ArrayPais(InputStream inputStream)
	{
		Gson gson = new Gson();
		String data = getStringFromInputStream(inputStream);
		
		ArrayList<ArrayList<String>> s = gson.fromJson(data, new TypeToken <ArrayList<ArrayList<String>>>(){}.getType());
		System.out.println(s);
		return s;
	}
}