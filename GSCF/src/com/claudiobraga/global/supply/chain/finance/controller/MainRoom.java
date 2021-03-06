package com.claudiobraga.global.supply.chain.finance.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.claudiobraga.global.supply.chain.finance.domain.model.Room;

public class MainRoom {
	
	static ArrayList<Room> allRooms = new ArrayList<>();
	static ArrayList<Room> repeatedRooms = new ArrayList<>();
	
	static Path archive = Paths.get("C:/Users/cjbde/Documents/input1.txt");

	public static void main(String[] args) throws IOException {
		
		System.out.println("***** ALL ROOMS IN THE INPUT1 FILE *****");
		loadingArrayFromInput1File();

    	System.out.println("\nLISTING ALL ROOMS FROM INPUT1 THAT HAVE A CUBIC SHAPE"
    			+ "(ORDERED BY TOTAL NEEDED WALLPAPER DESCENDING)\n"
    			+ "\t\tPLUS THE NUMBER OF TOTAL SQUARE FEET WALLPAPER NEED CALCULATED\n");
    	
    	quickSort(allRooms, 0, allRooms.size() - 1);
    	showAllRoomsDesc(allRooms);
    	
    	selectRepeatedRooms();
   	 	System.out.println("\n\t***** LISTING ALL ROOMS FROM INPUT1 THAT ARE APPEARING MORE THAN ONCE *****\n");
    	showAllRooms(repeatedRooms);
	}
		
	private static void loadingArrayFromInput1File() throws IOException {
		List<String> lines = Files.readAllLines(archive);
		
		ArrayList<Integer> input1Values;
		
		String auxConverter = "";
		
		for (int i = 0; i < lines.size(); i++) {
			String rooms = lines.get(i);
			
			char ch[] = rooms.toCharArray();
			input1Values = new ArrayList<>();
			for (int j = 0; j < ch.length; j++) {
				if (ch[j] !='x' || ch[j] !='x') {
					auxConverter += ch[j];
				}else {
					input1Values.add(Integer.parseInt(auxConverter));
					auxConverter = "";
				}
				if (j == ch.length - 1) {
					input1Values.add(Integer.parseInt(auxConverter));
					auxConverter = "";
				}
			}
			
			Room room = new Room (input1Values.get(0), input1Values.get(1), input1Values.get(2));
			allRooms.add(room);
			
			System.out.println("Room " + (i + 1) + ": " + rooms);
		}
	}
		
		private static void showAllRooms(ArrayList<Room> rooms){
			int count  = 1;
			for (Room room : rooms) {
				System.out.println(count+"?;"+ room.toString());
				count++;
			}
		}
		
		private static void selectRepeatedRooms() {
			int count = 0;
			for (Room room : allRooms) {
				for (int i = 0; i < allRooms.size(); i++) {
					if (room.getLength() == allRooms.get(i).getLength() &&
						room.getWidth() == allRooms.get(i).getWidth() &&
						room.getHeight() == allRooms.get(i).getHeight()) {
						count++;
					}
				}
				if (count > 1) {
					repeatedRooms.add(room);
				}
				count = 0;
			}
		}
		
		public static void quickSort(ArrayList<Room> roomArr, int low, int high){
	        if (low < high) {
	            int partitionIndex = partition(roomArr, low, high);
	            quickSort(roomArr, low, partitionIndex-1);
	            quickSort(roomArr, partitionIndex+1, high);
	        }
	    }
		
	    private static int partition(ArrayList<Room> roomArr, int low, int high){
	    	Room partitionIndex = roomArr.get(high);
	        int i = (low-1);

	        for (int j = low; j < high; j++)
	        {
	            if (roomArr.get(j).getTotal() <= partitionIndex.getTotal()) {
	                i++;

	                Room swapTemp = new Room();
	                swapTemp.copy(roomArr.get(i));
	                roomArr.get(i).copy(roomArr.get(j));
	                roomArr.get(j).copy(swapTemp);
	            }
	        }

	        Room swapTemp = new Room();
	        swapTemp.copy(roomArr.get(i + 1));
	        roomArr.get(i+1).copy(roomArr.get(high));
	        roomArr.get(high).copy(swapTemp);

	        return i+1;
	    }
	    
		private static void showAllRoomsDesc(ArrayList<Room> rooms){
			int count  = 1, total = 0;
			for (int i = rooms.size() - 1; i >= 0; i--) {
				System.out.println(count+"?;"+ rooms.get(i).toString());
				total += rooms.get(i).getTotal();
				count++;
			}
			
			System.out.println("\nTOTAL SQUARE FEET OF WALLPAPER THAT THE COMPANY SHOULD ORDER FOR ALL ROOMS"
					+ ": [ " + total + " ]" );
		}
		
	}
