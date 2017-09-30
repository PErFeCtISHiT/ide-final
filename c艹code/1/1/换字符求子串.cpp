#include <iostream>
#include<string>
using namespace std;
int main() {
	string f;
	string s;
	int s_len;
	int f_len;
	cin >> s_len >> f_len;
	cin >> s;
	cin >> f;
	int *res = new int[s_len];
	int min = s_len + 1;
	for (int i = 0; i < f_len - s_len + 1; i++) {
		int temp = 0;
		int *a = new int[s_len];
		for (int j = 0; j < s_len; j++) {
			if (s.at(j) != f.at(j + i)) {
				a[temp] = j;
				temp++;
			}
		}
		if (temp < min) {
			min = temp;
			res = a;
		}
	}
	cout << min;
	cout << "\n";
	for (int i = 0; i < min;i++) {
		cout << res[i] + 1 << " ";
	}
}