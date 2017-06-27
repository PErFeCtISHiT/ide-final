//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {


	@Override
	public String execute(String code, String param) throws RemoteException {//bf解释器
		ArrayList<Byte> major = new ArrayList<Byte>();
		int count = 0;
		String result = "";
		byte a = 0;
		major.add(a);
		int ptr = 0;
		for(int i = 0;i < code.length();i++){
			switch(code.charAt(i)){
			case '+':{
				byte temp = major.get(ptr);
				temp++;
				major.set(ptr, temp);
				break;
			}
			case '-':{
				byte temp = major.get(ptr);
				temp--;
				major.set(ptr, temp);
				break;
			}
			case '>':{
				major.add(a);
				ptr++;
				break;
			}
			case '<':{
				ptr--;
				break;
			}
			case '.':{
				byte temp = major.get(ptr);
				result = result + (char)temp;
				break;
			}
			case ',':{
				byte temp = (byte)param.charAt(count);
				count++;
				major.set(ptr, temp);
				break;
			}
			case '[':{
				if(major.get(ptr) == 0){
					i++;
					int looptimes = 1;
					while(code.charAt(i) != ']' || looptimes != 1){
						if(code.charAt(i) == '[')
							looptimes++;
						else if(code.charAt(i) == ']')
							looptimes--;
						i++;
					}
				}
				break;
			}
			case ']':{
				if(major.get(ptr) != 0){
					i--;
					int looptimes = 1;
					while(code.charAt(i) != '[' || looptimes != 1){
						if(code.charAt(i) == ']')
							looptimes++;
						else if(code.charAt(i) == '[')
							looptimes--;
						i--;
					}
				}
				break;
			}
			}
		}
		return result;
	}
	public String turn(String ook){//ook转换器
		String BF = "";
		for(int i = 0;i < ook.length();i+=10){
			String temp = ook.substring(i,i + 10);
			switch(temp){
			case("Ook. Ook? "):{
				BF = BF + ">";
				break;
			}
			case("Ook? Ook. "):{
				BF = BF + "<";
				break;
			}
			case("Ook. Ook. "):{
				BF = BF + "+";
				break;
			}
			case("Ook! Ook! "):{
				BF = BF + "-";
				break;
			}
			case("Ook! Ook. "):{
				BF = BF + ".";
				break;
			}
			case("Ook. Ook! "):{
				BF = BF + ",";
				break;
			}
			case("Ook! Ook? "):{
				BF = BF + "[";
				break;
			}
			case("Ook? Ook! "):{
				BF = BF + "]";
				break;
			}
			}
		}
		return BF;
	}
}
