package yaniv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import game.main;


public class Stats {
	
		private String nameOfWinner; // 1 - line
		private int maxScore; // 2 - line
		private File f;
		
		public Stats() throws IOException {
			f = new File("Scores");
			if(!f.exists()) {
			  this.nameOfWinner = "no winner yet";
			  this.maxScore = 100000000;//point must be smaller than that
			  f.createNewFile();
		      f.setWritable(true);
			  writeToFile();
			} else {
				readFromFile();
			}
		}
		
		/**
		 * read from the file the data
		 * @throws IOException
		 */
		public void readFromFile() throws IOException {
		    BufferedReader br = new BufferedReader(new FileReader(f)); 
		    String st; 
		   //lines = new ArrayList<Integer>();
		    st = br.readLine();
		    this.maxScore = Integer.parseInt(st);
		    st = br.readLine();
		    this.nameOfWinner = st;
		    br.close();
		    //updateLocalVariables();
		}
		
		/**
		 * write to file the stats
		 * @throws IOException
		 */
		public void writeToFile() throws IOException {
	        FileWriter fw = new FileWriter(f.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(Integer.toString(this.maxScore));
			bw.newLine();
			bw.write(this.nameOfWinner);
			bw.newLine();
	        bw.close();
		}
		
		
		public String getNameOfWinner() {
			return nameOfWinner;
		}

		public void setNameOfWinner(String nameOfWinner) {
			this.nameOfWinner = nameOfWinner;
		}

		public int getMaxScore() {
			return maxScore;
		}

		public void setMaxScore(int maxScore) {
			this.maxScore = maxScore;
		}

		public File getF() {
			return f;
		}

		public void setF(File f) {
			this.f = f;
		}

		public void updateStats(String name, int points) throws IOException {
			readFromFile();
			if (points / main.game.getGameCounter() < this.maxScore) {
				this.maxScore = points / main.game.getGameCounter();
				this.nameOfWinner = name;
			}
				
		//	}
			writeToFile();
		}
}
