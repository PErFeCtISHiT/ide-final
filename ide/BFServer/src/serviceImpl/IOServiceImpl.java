package serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
public class IOServiceImpl implements IOService{
	public ArrayList<String> opList = new ArrayList<String>();
	private ArrayList<String> antiOpList = new ArrayList<String>();
	private static String code[] = new String[6];
	@Override
	public boolean writeFile(String file, String userId, String fileName) {//写文件
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			int i = 0;
			while(i < file.length()){
			if(((int)file.charAt(i)) != 10){
				fw.write(file.charAt(i));
			}
			else
				fw.write("\r\n");
			i++;
			}
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {//读文件
		String result = "";
		String temp = "";
		try{
		File f = new File(userId + "_" + fileName);
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		while((temp = reader.readLine()) != null){
			result = result + temp + "\r\n";
		}
		return result;
		}catch (IOException e){
			//e.printStackTrace();
			return "reading error";
		}
	}

	@Override
	public String readFileList(String userId) {//获取用户的文件列表
		String temp = "";
		String result = "";
		try{
			File f = new File("fileAdmin");
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while((temp = reader.readLine()) != null){
				String a[] = temp.split(" ");
				if(a[0].equals(userId) && a.length > 2){
					for(int i = 2;i < a.length;i++){
						result = result + a[i] + " ";
					}
					return result;
				}
			}
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return "Exception";
		}
	}

	@Override
	public boolean save(String newcode) {//保存历史版本的代码
		if(newcode.equals(code[0]))
			return true;
		for(int i = 5;i > 0;i--){
			code[i] = code[i - 1];
		}
		code[0] = newcode;
	return true;
	}

	@Override
	public String getCode(int i){//获取历史版本代码
		return code[i - 1];
	}


	@Override
	public boolean addFile(String fileName,String user){//添加文件
		String temp = "";
		String result = "";
		try{
			File f = new File("fileAdmin");
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while((temp = reader.readLine()) != null){
				String a[] = temp.split(" ");
				if(a[0].equals(user)){
					if(a.length <= 2)
					    result = result + temp + " " + fileName + " " + "\r\n";
					else
						result = result + temp + fileName + " " + "\r\n";
				}
				else
					result = result + temp + "\r\n";
			}
			reader.close();
			FileWriter fileWriter = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(result);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		}

	@Override
	public String redo(){
		if(opList.isEmpty())
			return null;
		else if(opList.size() == 1){
			opList.remove(0);
			return null;
		}
		else{
			String temp = opList.get(opList.size() - 2);
			opList.remove(opList.size() - 1);
			return temp;
		}
	}

	@Override
	public String undo(){
		if(antiOpList.isEmpty())
			return null;
		else{
			String temp = antiOpList.get(antiOpList.size() - 1);
			antiOpList.remove(antiOpList.size() - 1);
			return temp;
		}
	}

	@Override
	public boolean addRe(String s){
		opList.add(s);
		return true;
	}

	@Override
	public boolean addUn(String s){
		antiOpList.add(s);
		return true;
	}

	@Override
	public boolean textredo(){
		if(opList.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public boolean textundo(){
		if(antiOpList.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public boolean clear(){
		opList.clear();
		antiOpList.clear();
		for(int i = 0;i < 6;i++)
			code[i] = null;
		return true;
	}


	@Override
	public boolean delete(String userNow, String cmd, int mode){
		String temp = "";
		String result = "";
		try{
			if(mode == 1){
			File f = new File(userNow + "_" + cmd + ".bf");
			f.delete();
			}
			else{
				File f = new File(userNow + "_" + cmd + "ook");
				f.delete();
			}
			File f = new File("fileAdmin");
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while((temp = reader.readLine()) != null){
				String a[] = temp.split(" ");
				if(a[0].equals(userNow)){
					result = result + a[0];
					for(int i = 1;i < a.length;i++){
						if(!a[i].equals(cmd))
						result = result + " " + a[i];
					}
					result = result + "\r\n";
				}
				else{
					result = result + temp + "\r\n";
				}
			}
			reader.close();
			FileWriter fileWriter = new FileWriter(f,false);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(result);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}

