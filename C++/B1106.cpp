#include <iostream>
#include <stack>
#include <utility>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
  int people, cities;

  scanf("%d%d",&people,&cities);

  int min_cost[people+1];
  vector<pair<int, int> > cost_n_people(cities);

  for(int i = 0 ; i < cities ; i++){
    scanf("%d%d",&cost_n_people[i].first, & cost_n_people[i].second);
  }
  sort(cost_n_people.begin(),cost_n_people.end());

  min_cost[0] = 0;
  for(int i = 1 ; i <= people ; i++){
    min_cost[i] = min_cost[i-1] + cost_n_people[0].first;
    for(int j = 0 ; j < cities ; j++){
      int idx = ( i < cost_n_people[j].second )? 0 : i - cost_n_people[j].second;
      int temp = cost_n_people[j].first + min_cost[idx];
      min_cost[i] = (temp < min_cost[i])? temp : min_cost[i];
    }
  }

  printf("%d\n",min_cost[people]);
}
