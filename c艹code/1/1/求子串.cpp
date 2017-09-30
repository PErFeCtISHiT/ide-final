/*#include <iostream>
#include<string>
using namespace std;
int main() {
	int num;
	cin >> num;
	string *s = new string[num];
	for (int i = 0; i < num; i++)
		cin >> s[i];
	string *p = new string[num];
	for (int i = 0; i < num; i++)
		p[i] = s[i];
	for (int k = 0; k < num; k++) {
		string *pub = new string[num];
		for (int i = 0; i < num; i++) {
			if (i != k) {
				int count = 0;
				string temp;
				for (int j = 0; j < s[k].length(); j++) {
					if (s[k].at(j) == s[i].at(count)) {
						count++;
						temp += s[k].at(j);
						if (temp.length() > pub[i].length())
							pub[i] = temp;
					}
					else if (s[k].at(j) != s[i].at(count) && count != 0) {
						j--;
						count = 0;
						if (temp.length() > pub[i].length())
							pub[i] = temp;
						temp = "";
					}
				}
			}
		}
		for (int i = 0; i < num; i++) {
			if (i != k) {
				if (pub[i].length() < p[k].length())
					p[k] = pub[i];
			}
		}
	}
	string *pt = new string[num];
	for (int i = 0; i < num; i++)
		pt[i] = "";
	int con = 0;
	for (int i = 0; i < num; i++) {
		if (pt[con].length() < p[i].length())
			pt[con] = p[i];
		else if (pt[con].length() == p[i].length()) {
			con++;
			pt[con] = p[i];
		}
	}
	con++;
	int *res = new int[con];
	for (int i = 0; i < con; i++) {
		res[i] = 0;
	}
	for (int i = 0; i < con; i++) {
		string *store = new string[num];
		for (int j = 0; j < num; j++)
			store[j] = s[j];
		while (s[0].substr(0, pt[i].length()) != pt[i]) {
			char temp = s[0].at(0);
			s[0] = s[0].substr(1) + temp;
			res[i]++;
		}
		for (int j = 1; j < num; j++) {
			long int t = 0;
			while (s[j] != s[0]) {
				char temp = s[j].at(0);
				s[j] = s[j].substr(1) + temp;
				t++;
				if (t == s[j].length()) {
					cout << -1;
					return 0;
				}
			}
			res[i] += t;
		}
		for (int j = 0; j < num; j++)
			s[j] = store[j];
	}
	int resu = 5000;
	for (int i = 0; i < con; i++) {
		if (res[i] < resu)
			resu = res[i];
	}
	cout << resu;
}*/
#include<iostream>
#include<string> 
#include<algorithm>
using namespace std;

int main(){
	int n;
	cin >> n;
	string *a = new string[n];
		for (int i = 0; i < n; i++)
			cin >> a[i];
		int ans = 10000, flag = 0;
		for (int i = 0; i < n; i++){
			int sum = 0;
			for (int j = 0; j < n; j++)
			{
				string tmp = a[j] + a[j];
				if (tmp.find(a[i]) == string::npos) 
					flag = 1;
				else 
					sum += tmp.find(a[i]);
			}
			ans = min(ans, sum);
		}
		if (flag == 1)
			cout << -1;
		else
			cout << ans;
}

