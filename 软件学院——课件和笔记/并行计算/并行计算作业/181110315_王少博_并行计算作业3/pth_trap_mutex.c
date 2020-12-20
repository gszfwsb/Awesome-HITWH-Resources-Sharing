/* File:     pth_trap_mutex.c
 * Purpose:  Estimate trapezoidal area using trapezoidal rule 
 *           This version uses a mutex to protect the critical section
 * Compile:  gcc -g -Wall -o pth_trap_mutex pth_trap_mutex.c -lm -lpthread
 *           timer.h needs to be available
 * Run:      ./pth_trap_mutex <number of threads> <n> a b
 *           n is the number of terms of the trapezoidal rule split
 *           n should be evenly divisible by the number of threads
 *			 a should not be larger than b
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <pthread.h>
#include "timer.h"

const int MAX_THREADS = 1024;

/*global variable */
long thread_count;//in
long long n;//in
double a;//left_endpt  in
double b;//right_endpt  in
long double h;
long long trap_count;

long double sum;
pthread_mutex_t mutex;

/*To calculate the trapezoidal area by multi-thread*/
void* Thread_sum(void* rank);

/* Only executed by main thread */
void Get_args(int argc, char* argv[]);
void Usage(char* prog_name);
double Serial_trap(long long n);

/* Function we're integrating */
double f(double x);

int main(int argc, char* argv[])
{
    long       thread;  /* Use long in case of a 64-bit system */
    pthread_t* thread_handles;
    double start, finish, elapsed;

    /* Get number of threads from command line */
    Get_args(argc, argv);

    thread_handles = (pthread_t*) malloc (thread_count*sizeof(pthread_t));
    pthread_mutex_init(&mutex, NULL);
    sum = 0.0;
    h = (long double)(b-a)/n;
    trap_count = n/thread_count;

    GET_TIME(start);
    for (thread = 0; thread < thread_count; thread++)
        pthread_create(&thread_handles[thread], NULL, Thread_sum, (void*)thread);

    for (thread = 0; thread < thread_count; thread++)
        pthread_join(thread_handles[thread], NULL);
    GET_TIME(finish);
    elapsed = finish - start;

    sum = h*sum;
    printf("With n = %lld terms,\n", n);
    printf("   Our estimate of area = %.15Lf\n", sum);
    printf("The elapsed time is %e seconds\n", elapsed);

    GET_TIME(start);
    sum = Serial_trap(n);
    GET_TIME(finish);
    elapsed = finish - start;
    printf("   Single thread est    = %.15Lf\n", sum);
    printf("The elapsed time is %e seconds\n", elapsed);
    printf("          accurate area = 2.666666666666667\n");

    pthread_mutex_destroy(&mutex);
    free(thread_handles);
    return 0;
}  /* main */

/*------------------------------------------------------------------
 * Function:   Thread_sum
 * Purpose:    Estimate area using multi-thread
 * In arg:     rank
 * Return val: null
 */
void* Thread_sum(void* rank)
{
    long my_rank = (long) rank;
    long double x;
    long double my_sum = 0.0;
    long double start = a+trap_count*my_rank*h;
    long double end = a+trap_count*(my_rank+1)*h;

    my_sum = (f(start) + f(end))/2.0;
    for (long long i = 1; i <= trap_count; i++)
    {
        x = start+i*h;
        my_sum += f(x);
    }

    pthread_mutex_lock(&mutex);
    sum += my_sum;
    pthread_mutex_unlock(&mutex);

    return NULL;
}  /* Thread_sum */

/*------------------------------------------------------------------
 * Function:   Serial_trap
 * Purpose:    Estimate area using 1 thread
 * In arg:     n
 * Return val: Estimate of area
 */
double Serial_trap(long long n)
{
    int right_endpt=2;
    int left_endpt=0;
    long double high = (long double)(right_endpt-left_endpt)/n;
    long double approx = (long double)(1-0)/2;
    for(int i = 1; i<=n-1; i++)
    {
        long double x_i = (long double)left_endpt+i*high;
        approx+=f(x_i);
    }
    approx = high*approx;
    return approx;
}  /* Serial_trap */

/*------------------------------------------------------------------
 * Function:    Get_args
 * Purpose:     Get the command line args
 * In args:     argc, argv
 * Globals out: thread_count, n
 */
void Get_args(int argc, char* argv[])
{
    if (argc != 5)
        Usage(argv[0]);
    thread_count = strtol(argv[1], NULL, 10);
    if (thread_count <= 0 || thread_count > MAX_THREADS)
        Usage(argv[0]);
    n = strtoll(argv[2], NULL, 10);
    if (n <= 0)
        Usage(argv[0]);
    a = strtod(argv[3],NULL);
    b = strtod(argv[4],NULL);
    if(a>b)
        Usage(argv[0]);
}  /* Get_args */

/*------------------------------------------------------------------
 * Function:  Usage
 * Purpose:   Print a message explaining how to run the program
 * In arg:    prog_name
 */
void Usage(char* prog_name)
{
    fprintf(stderr, "usage: %s <number of threads> <n>\n", prog_name);
    fprintf(stderr, "   n is the number of terms and should be >= 1\n");
    fprintf(stderr, "   n should be evenly divisible by the number of threads\n");
    fprintf(stderr, "   a should not be larger than b\n");
    exit(0);
}  /* Usage */

/*------------------------------------------------------------------
 * Function:    f
 * Purpose:     Compute value of function to be integrated
 * Input args:  x
 */
double f(double x /* in */)
{
    return x*x;
} /* f */

