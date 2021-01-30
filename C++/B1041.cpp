#include <iostream>
#include <stack>
#include <utility>

using namespace std;

bool visited[6] = {false,};
int dice[6];
int counter_part[6] = {5,4,3,2,1,0};

long long int get_min(int pre_min, int cur, bool visited[]){
  int check = 0;

  for(int i = 0  ; i < 6 ; i++){
    if(!visited[i]){
      if(cur >= dice[i] + pre_min){
        cur = dice[i] + pre_min;
        check = i;
      }
    }
  }
  visited[check] = true;
  visited[counter_part[check]] = true;

  return cur;
}
int main(){
  long long int length = 0;
  int check = 0;
  int max = 0;
  long long int min = 100;
  long long int min2 =  200; //50*2 = 100로 되어야 하긴 하지만 혹시 몰라 수를 올림.
  long long int min3 =  300;  //50*3 = 150
  long long int total_min = 0;

  scanf("%lld", &length);

  for(int i = 0 ; i< 6 ; i++)
    scanf("%d", &dice[i]);


  min = get_min(0, min, visited );
  min2 = get_min(min, min2, visited );
  min3 = get_min(min2, min3, visited );
  //printf("min %lld min2 %lld min3 %lld\n", min,min2,min3);

  if(length == 1){
    int sum = 0;
    for(int i = 0; i < 6 ; i++) {
      if(max < dice[i]) max = dice[i];
      sum += dice[i];
    }
      total_min = sum - max;
  }
  else if(length == 2){
    total_min += 4 * min3 + 4 * min2;
  }
  else {
    total_min = 4 * min3 + 4 * (length - 2 ) * min2
                  +  4 * (length-1) * min2 +
                  + (length-2) * (length-2) * min
                  + 4 * (length-2) * (length-1) * min;

  }
  printf("%lld",total_min);
}
