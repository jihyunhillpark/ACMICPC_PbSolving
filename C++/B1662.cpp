#include <iostream>
#include <stack>
#include <utility>

using namespace std;

//int unarchiving(char* input,int idx);

int main(){
  char input[51];
  int length = 0;
  char pos = 0;
  stack<pair<int,int> > s;

  scanf("%s", input);

  for(int i = 0 ; input[i]!= 0 ; i++)
  {
    if(input[i] == '('){
      s.push(make_pair(length-1, input[i-1]-'0'));
      length = 0;
    }
    else if(input[i] == ')'){
      pair<int,int> temp = s.top();
      s.pop();
      length = temp.first + (length* temp.second);
    }
    else{
      length++;
    }
  }

  printf("%d\n",length);

}
