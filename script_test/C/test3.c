#include<stdio.h>

struct Point {
  int x;
  int y;
}p = {1000,100};

struct IEEEFloat {
  int sign:1;
  int exp:8;
  int frac:23;
};

void printPoint(struct Point point);
void printPoint2(struct Point* point);

int main() {
  struct Point* p2 = & p;
  p2->x = 2;
  p2->y = 3;
  printPoint2(p2);

  printf("%lu", sizeof(struct IEEEFloat));

}

void printPoint(struct Point point) {
  printf("x: %d, y: %d\n", point.x, point.y);
}

void printPoint2(struct Point* point) {
  printf("x: %d, y: %d\n", point->x, point->y);  
}

