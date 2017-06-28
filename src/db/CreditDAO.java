package db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CreditDAO {
	public CreditDAO(){
		
	}
	
	public ArrayList<CreditDTO> loading(String fileName){
		ArrayList<CreditDTO> list = new ArrayList<CreditDTO>();
		BufferedReader in = null;
		
		try {
			 in = new BufferedReader(new FileReader(fileName));
			String str = "";
			
			while(true){
				str = in.readLine();
				
				if(str == null){
					break;
				}
				str = str.trim();
				
				int start = str.indexOf('<');
				int end = str.indexOf('>');
				
				String location = str.substring(start+1, end);
				String desc = str.substring(end+1);
				
				list.add(new CreditDTO(new ImageIcon(location).getImage(), desc));
				System.out.println(str);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {in.close();} catch (IOException e) {
				System.out.println(fileName + " 에서 불러오던중 에러가 발생했습니다.");
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/*public static void main(String[] args){
		new CreditDAO().loading("rsc" + File.separator + "credit" + File.separator+ "credits.txt");
	}*/
}
