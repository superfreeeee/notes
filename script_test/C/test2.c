#include<stdio.h>
#define LENGTH 5

int possible(char enqueue[], char dequeue[]);

int main() {
  char enqueue[] = {'1', '2', '3', '4','5'};
  char dequeue[] = {'3', '2', '4', '1', '5'};
  int valid = possible(enqueue, dequeue);
  if(valid)
    printf("Possible");
  else
    printf("Impoosible");
}

int possible(char enqueue[], char dequeue[]) {
  char stack[LENGTH];
  stack[0] = enqueue[0];
  int top = 0, ep = 1, dp = 0;  // top為棧頂位置，ep為入棧隊列位置，dp為出棧隊列位置
  while(dp < LENGTH) {
    if(ep >= LENGTH) {  // 入棧隊列空
      while(dp < LENGTH) {  // 出棧到底，有不符合直接返回0，否則返回1
        if(stack[top--] != dequeue[dp++]) 
          return 0;
      }
      return 1;
    }   
    if(dequeue[dp] == stack[top]) {  // 檢查棧頂是否為當前出棧，是的話pop並指向出棧隊列下一個 
      top--;  // pop一個數
      dp++;  // 出棧隊列位置後移一格
    }
    else {
      stack[++top] = enqueue[ep++];
    }
  }
  return 1;
}
