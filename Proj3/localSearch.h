//
// Created by VMao on 4/22/20.
//

#ifndef PROJ3_LOCALSEARCH_H
#define PROJ3_LOCALSEARCH_H
#include "localSearch.h"
#include <iostream>
#include <vector>
#include "methods.h"
using namespace std;
long long repeatedRandom(vector<long long int> input, int max_iter);
long long hillClimbing(vector<long long int> input, int max_iter);
long long simulatedAnnealing(vector<long long int> input, int max_iter);
long long PPrepeatedRandom(vector<long long int> input, int max_iter);
long long PPhillClimbing(vector<long long int> input, int max_iter);
long long PPsimulatedAnnealing(vector<long long int> input, int max_iter);


#endif //PROJ3_LOCALSEARCH_H
