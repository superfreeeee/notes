#include<stdio.h>

int f(int n);

int main() {
    for(int n=0 ; n<10 ; n++) {
        int r1 = f(n);
        printf("f(%d) = %d\n", n, r1);
    }
    
}

int f(int n) {
    int r2 = 1;
    int r3 = 1;

    if(n == 0 || n == 1) {
        return r3;
    }

    for(int i=1 ; i<=n-1 ; i++) {
        int r4 = r2 + r3;
        r2 = r3;
        r3 = r4;
    }
    return r3;
}