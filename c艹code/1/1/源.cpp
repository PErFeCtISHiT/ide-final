#include <iostream>
#include<string>
using namespace std;
int main() {
	string s;
	int temp = 0;
	cin >> s;
	if (s.length() == 1) {
		cout << "YES";
		return 0;
	}
	string t(s.rbegin(), s.rend());
	for (int i = 0; i < s.length(); i++) {
		if (s.at(i) != t.at(i)) {
			temp++;
		}
	}
	if (temp == 2 || (temp == 0 && s.length() % 2 == 1))
		cout << "YES";
	else
		cout << "NO";
}