# include<stdio.h>

void f1(int* num);
void swap0(int a, int b);
void swap(int* a, int* b);
void increment(int* a, int size);

int main() {
  // int num = 0;
  // printf("num in main: %p\n", &num);
  // printf("%d\n", num);

  // f1(&num);
  // printf("num: %d\n", num);

  // int a = 1;
  // int b = 2;
  // swap0(a, b);
  // printf("a: %d, b: %d\n", a, b);

  // swap(&a, &b);
  // printf("a: %d, b: %d\n", a, b);

  int a[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
  increment(a, 10);
  for(int i=0 ; i<10 ; i++) {
    printf("%d ", a[i]);
  }
}

void f1(int* num) {
  printf("num in f1: %p\n", &num);
  printf("%p\n", num);
  
  *num = *num + 1;
}

void swap0(int a, int b) {
  int temp;
  temp = a; 
  a = b;
  b = temp;
}

void swap(int* a, int* b) {
  int temp;
  temp = *a;
  *a = *b;
  *b = temp;
}

void increment(int* a, int size) {
  for(int i=0 ; i<size ; i++) {
    *(a+i) = *(a+i) + 1;
  }
}