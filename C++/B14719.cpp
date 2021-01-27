#include <iostream>
#include <stack>

using namespace std;

int main(){
    stack<int> s;
    int w,h;
    int count = 0, max_idx = 0, previous;

    scanf("%d%d\n", &h,&w);
    int elements[w], max = 0, raindrop = 0;

    for(int i = 0; i < w; i++)
      scanf("%d", &elements[i]);

    s.push(max = elements[0]);

    for(int i = 1; i < w; i++)
    {
      previous = s.top();
      if(elements[i] <= previous){
        s.push(elements[i]);
      }
      else
      {
        int bases[w];
        int j = 0;
        int cmp = previous;

        for(; cmp < max ; j++){
          if(cmp < elements[i]) bases[j] = cmp ;
          else break;
          s.pop();
          cmp = s.top();
        }

        int fill = (max < elements[i] )? max : elements[i];
        //채우기
        for(; 0 < j ; j--)
        {
          raindrop += (fill - bases[j-1]);
          s.push(fill);
        }
        s.push(elements[i]);
        max = (max > elements[i] )?max : elements[i];
      }
    }
    printf("%d\n", raindrop);
}
