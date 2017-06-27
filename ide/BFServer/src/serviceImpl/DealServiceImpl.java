package serviceImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import service.DealService;
public class DealServiceImpl implements DealService{
	public String dealing(String filePath){//去除文件中的换行符
	String str = null;
	String result = "";
	String type = "";
	String temp[] = filePath.split("\\.");
	type = temp[1];
	try{
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		if(type.equals("bf"))
		while((str = reader.readLine()) != null && str.length() != 0){
			result = result + str;
		}
		else{
			while((str = reader.readLine()) != null && str.length() != 0){
				result = result + str + " ";
			}
			result = result.substring(0,result.length() - 1);
		}
		reader.close();
	}catch(Exception x){

	}
	return result;
}

}
