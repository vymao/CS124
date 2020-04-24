//
// Created by VMao on 4/19/20.
//

#ifndef PROJ3_METHODS_H
#define PROJ3_METHODS_H
#include <vector>
using namespace std;

long long Karmarkar_Karp(vector<long long> input);
template <typename T> vector<T> deepCopy(vector<T> array);
vector<int> generateP(int n);
vector<int> generateS(int n);
vector<int> PPgetNeighbor(vector<int> input);
vector<int> getNeighbor(vector<int> input);
vector<long long int> computeCost(vector<int> input, vector<long long int> A);
long long residualCompute(vector<int> sign, vector<long long int> A);
#endif //PROJ3_METHODS_H
