#include <stdio.h>

int climb_stairs(int n);

int main() {
  int n = 5;
  // scanf("%d", &n);
  for(int i=0 ; i<101 ; i++) {
    int result = climb_stairs(i);
    printf("%d:%d\n", i, result);
  }
}

int climb_stairs(int n) {
  if(n < 3)
    return n == 1 ? -1 : 1;
  // TODO
  int stairs[n];
  stairs[0] = 0;
  stairs[1] = 1;
  stairs[2] = 1;
  for(int i=3 ; i<n ; i++) {
    stairs[i] = stairs[i-2] + stairs[i-3];
  }
  if(stairs[n-1] == 0)
    return -1;
  // for(int i=0 ; i<n ; i++)
  //   printf("%d ", stairs[i]);
  return stairs[n-1];
}