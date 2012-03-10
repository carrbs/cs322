#include <stdlib.h>
int *scan(int * address, int size)
{
    if (size <= 0)
        return NULL;

    int *foo = malloc (size+1);
    int result = 0;
    int i;
    int max = 0;
    for (i = 0; i < size; i++)
    {
        if (address[i] > max)
            max = address[i];
        result += address[i];
        foo[i] = result;
    }
    foo[size] = max;
    return foo;
}


