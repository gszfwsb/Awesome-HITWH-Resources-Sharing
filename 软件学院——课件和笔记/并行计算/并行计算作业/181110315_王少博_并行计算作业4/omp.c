/* File:    omp.c
 *
 * Purpose: Compare parallel count sort, serial count sort, qsort.
 *
 * Compile: gcc -g -Wall -fopenmp -I. -o omp omp.c
 * Usage:   ./omp <thread count> <n> <g|i>
 *             n:   number of elements in list
 *            'g':  generate list using a random number generator
 *            'i':  user input list
 *
 * Input:   list (optional)
 * Output:  elapsed time for parallel count sort, serial count sort,
 *			qsort.
 *
 * Note:
 * 1.  DEBUG flag prints the contents of the list
 * 2.  This version forks and joins the threads only once.
 * 3.  Uses the OpenMP library function omp_get_wtime for timing.
 *     This function returns the number of seconds since some time
 *     in the past.
 *
 * IPP:  Section 5.6.2 (pp. 235 and ff.)
 */
#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <string.h>
#include <time.h>

#ifdef DEBUG
const int RMAX = 100;
#else
const int RMAX = 10000000;
#endif

int thread_count;

void Usage(char* prog_name);
void Get_args(int argc, char* argv[], int* n_p, char* g_i_p);
void Generate_list(int a[], int n);
void Print_list(int a[], int n, char* title);
void Read_list(int a[], int n);
void Count_sort_parallel(int a[], int n);
void Count_sort_serial(int a[], int n);
int cmp(const void * a, const void *b);

/*-----------------------------------------------------------------*/
int main(int argc, char* argv[]) {
	int  n;
	char g_i;
	int* a;
	int* b;
	int* c;
	double start, finish;
	clock_t  start_time, end_time;
	double Total_time;

	Get_args(argc, argv, &n, &g_i);
	a = malloc(n*sizeof(int));
	b = malloc(n*sizeof(int));
	c = malloc(n*sizeof(int));
	if (g_i == 'g') {
		Generate_list(a, n);
#     ifdef DEBUG
		Print_list(a, n, "Before sort");
#     endif
	} else {
		Read_list(a, n);
	}
	for (int i = 0; i < n; ++i) {
		b[i] = a[i];
		c[i] = a[i];
	}

	start = omp_get_wtime();
	Count_sort_parallel(a, n);
	finish = omp_get_wtime();
	printf("Parallel count sort time = %e seconds\n", finish - start);

#  ifdef DEBUG
	Print_list(a, n, "After sort");
#  endif

	start_time = clock();
	Count_sort_serial(b, n);
	end_time = clock();
	Total_time = (double)(end_time-start_time) / CLOCKS_PER_SEC;
	printf("Serial count sort time = %e seconds\n", Total_time);

#  ifdef DEBUG
	Print_list(b, n, "After sort");
#  endif

	start_time = clock();
	qsort(c, n, sizeof(int), cmp);
	end_time = clock();
	Total_time = (double)(end_time-start_time) / CLOCKS_PER_SEC;
	printf("qsort time = %e seconds\n", Total_time);

#  ifdef DEBUG
	Print_list(c, n, "After sort");
#  endif

	free(a);
	free(b);
	free(c);
	return 0;
}  /* main */


/*-----------------------------------------------------------------
 * Function:  Usage
 * Purpose:   Summary of how to run program
 */
void Usage(char* prog_name) {
	fprintf(stderr, "usage:   %s <thread count> <n> <g|i>\n", prog_name);
	fprintf(stderr, "   n:   number of elements in list\n");
	fprintf(stderr, "  'g':  generate list using a random number generator\n");
	fprintf(stderr, "  'i':  user input list\n");
}  /* Usage */


/*-----------------------------------------------------------------
 * Function:  Get_args
 * Purpose:   Get and check command line arguments
 * In args:   argc, argv
 * Out args:  n_p, g_i_p
 */
void Get_args(int argc, char* argv[], int* n_p, char* g_i_p) {
	if (argc != 4 ) {
		Usage(argv[0]);
		exit(0);
	}
	thread_count = strtol(argv[1], NULL, 10);
	*n_p = strtol(argv[2], NULL, 10);
	*g_i_p = argv[3][0];

	if (*n_p <= 0 || (*g_i_p != 'g' && *g_i_p != 'i') ) {
		Usage(argv[0]);
		exit(0);
	}
}  /* Get_args */


/*-----------------------------------------------------------------
 * Function:  Generate_list
 * Purpose:   Use random number generator to generate list elements
 * In args:   n
 * Out args:  a
 */
void Generate_list(int a[], int n) {
	int i;

	srandom(1);
	for (i = 0; i < n; i++)
		a[i] = random() % RMAX;
}  /* Generate_list */


/*-----------------------------------------------------------------
 * Function:  Print_list
 * Purpose:   Print the elements in the list
 * In args:   a, n
 */
void Print_list(int a[], int n, char* title) {
	int i;

	printf("%s:\n", title);
	for (i = 0; i < n; i++)
		printf("%d ", a[i]);
	printf("\n\n");
}  /* Print_list */


/*-----------------------------------------------------------------
 * Function:  Read_list
 * Purpose:   Read elements of list from stdin
 * In args:   n
 * Out args:  a
 */
void Read_list(int a[], int n) {
	int i;

	printf("Please enter the elements of the list\n");
	for (i = 0; i < n; i++)
		scanf("%d", &a[i]);
}  /* Read_list */


/*-----------------------------------------------------------------
 * Function:     Count_sort_parallel
 * Purpose:      Sort list using Count sort
 * In args:      n
 * In/out args:  a
 */
void Count_sort_parallel(int a[], int n) {
	int i,j,count;
	int *temp = malloc(n*sizeof(int));

#  pragma omp parallel for num_threads(thread_count) \
   default(none) shared(a, n, temp) private(i, j, count)\
   schedule(static, 2)
	for(i=0; i<n; i++) {
		count = 0;
		for(j=0; j<n; j++) {
			if(a[j]<a[i])
				count++;
			else if(a[j] == a[i] && j<i)
				count++;
		}
		temp[count] = a[i];
	}

	memcpy(a, temp, n*sizeof(int));
	free(temp);
}/* Count_sort_parallel */


/*-----------------------------------------------------------------
 * Function:     Count_sort_serial
 * Purpose:      Sort list using Count sort
 * In args:      n
 * In/out args:  a
 */
void Count_sort_serial(int a[], int n) {
	int i,j,count;
	int *temp = malloc(n*sizeof(int));

	for(i=0; i<n; i++) {
		count = 0;
		for(j=0; j<n; j++) {
			if(a[j]<a[i])
				count++;
			else if(a[j] == a[i] && j<i)
				count++;
		}
		temp[count] = a[i];
	}

	memcpy(a, temp, n*sizeof(int));
	free(temp);
}/* Count_sort_serial */

/*-----------------------------------------------------------------
 * Function:     cmp
 * Purpose:      compare 2 close elements from small to big
 * In args:      a b
 * In/out args:  a b
 */
int cmp(const void * a, const void *b) {
	return *(int *)a - *(int *)b;
}
