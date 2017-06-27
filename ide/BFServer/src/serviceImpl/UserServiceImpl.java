package serviceImpl;


import java.io.*;
import service.UserService;

public class UserServiceImpl implements UserService{
	private static String userNow;
	@Override
	public String login(String username, String password)  {//登陆
		String temp = "";
		try{
		File f = new File("fileAdmin");
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		while((temp = reader.readLine()) != null){
			String a[] = temp.split(" ");
			if(a[0].equals(username) && a[1].equals(password)){
				userNow = a[0];
				return "success";
			}
		}
		}catch(Exception x){
			x.printStackTrace();
		}
		return "failed";
	}

	@Override
	public boolean newuser(String user,String password){//新建用户
		if(user.equals(null) || password.equals(null) || user.length() == 0 || password.length() == 0)
			return false;
		String temp = "";
		try{
		File f = new File("fileAdmin");
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		while((temp = reader.readLine()) != null){
			String a[] = temp.split(" ");
			if(a[0].equals(user)){
				return false;
			}
		}
		reader.close();
		FileWriter fileWriter = new FileWriter(f,true);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		writer.write(user + " " + password + "\r\n");
		writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String getName(){//获取用户名
		return userNow;
	}

	@Override
	public String getUserList(){//获取用户列表
		String result = "";
		try{
			String temp = "";
			File f = new File("fileAdmin");
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while((temp = reader.readLine()) != null){
				String a[] = temp.split(" ");
				if(!a[0].equals("admin")){
					result = result + a[0] + " ";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteuser(String user,String mode){//删除用户
		String result = "";
		try{
			String temp = "";
			File f = new File("fileAdmin");
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			while((temp = reader.readLine()) != null){
				String a[] = temp.split(" ");
				if(a[0].equals(user)){
					for(int i = 2;i < a.length;i++){
						File m = new File(user + "_" + a[i] + "." + mode);
						m.delete();
					}
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
