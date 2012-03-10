/* Driver program for testing scan.s. (Jingke Li)
 * 
 * Should print [ 1 3 6 10 15 21 28 20 7]
 */
#include <stdio.h>

extern int *scan(int *, int);

int main(void)
{
    int a[] = {1, 2, 3, 4, 5, 6, 7, -8};
    int i, *c;
    if ((c = scan(a, 8))) {
      printf("c = [");
      for (i=0; i<9; i++)
    printf(" %4d", c[i]);
      printf("]\n");
    } else {
      printf("Error in scan\n");
    }
    return 0;
}





