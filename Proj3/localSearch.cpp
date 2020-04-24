//
// Created by VMao on 4/22/20.
//

#include "localSearch.h"
#include <iostream>
#include <vector>
#include "methods.h"
#include <math.h>
using namespace std;

long long repeatedRandom(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> S = generateS(input.size());
    long long int residual = residualCompute(S, input_list);
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = generateS(input.size());
        long long int localResidual = residualCompute(neighbor, input_list);
        if (localResidual < residual) {
            residual = localResidual;
        }
    }

    return residual;
}

long long hillClimbing(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> S = generateS(input.size());
    long long int residual = residualCompute(S, input_list);
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = getNeighbor(S);
        long long int localResidual = residualCompute(neighbor, input_list);
        if (localResidual <residual) {
            residual = localResidual;
            S = neighbor;
        }
    }

    return residual;
}


long long simulatedAnnealing(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> S = generateS(input.size());
    //vector<int> bestS = deepCopy(S);
    vector<int> bestS = S;
    long long int residual = residualCompute(S, input_list);
    long long int bestResidual = residual;
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = getNeighbor(S);
         long long int localResidual = residualCompute(neighbor, input_list);
        if (localResidual <residual) {
            S = neighbor;
            residual = localResidual;
        } else {
            double r = static_cast <double> (rand()) / static_cast <double> (RAND_MAX);
            double cooldown = pow(10, 10) * pow(0.8, static_cast <double> (i / 300));
            double cutoff = exp(static_cast <double> (-(localResidual - residual)) / cooldown);
            if (r <cutoff) {
                S = neighbor;
                residual = localResidual;
            }
        }

        if (residual < bestResidual) {
            bestS = S;
            bestResidual = residual;
        }
    }

    return bestResidual;
}



long long PPrepeatedRandom(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> P = generateP(input.size());
    long long int residual = Karmarkar_Karp(input);
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = generateP(input.size());
        vector<long long int> localCost = computeCost(neighbor, input_list);
        long long int localResidual = Karmarkar_Karp(localCost);
        if (localResidual < residual) {
            residual = localResidual;
        }
    }

    return residual;
}

long long PPhillClimbing(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> P = generateP(input.size());
     long long int residual = Karmarkar_Karp(input);
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = PPgetNeighbor(P);
        vector<long long int> localCost = computeCost(neighbor, input_list);
        long long int localResidual = Karmarkar_Karp(localCost);
        if (localResidual < residual) {
            residual = localResidual;
            P = neighbor;
        }
    }

    return residual;
}

long long PPsimulatedAnnealing(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> P = generateP(input.size());
    vector<int> bestP = P;
    long long int residual = Karmarkar_Karp(input);
    long long int bestResidual = residual;
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = PPgetNeighbor(P);
        vector<long long int> localCost = computeCost(neighbor, input_list);
        long long int localResidual = Karmarkar_Karp(localCost);
        if (localResidual < residual) {
            residual = localResidual;
            P = neighbor;
        } else {
            double r = static_cast <double> (rand()) / static_cast <double> (RAND_MAX);
            double cooldown = pow(10, 10) * pow(0.8, static_cast <double> (i / 300));
            double cutoff = exp(static_cast <double> (-(localResidual - residual)) / cooldown);
            if (r <cutoff) {
                residual = localResidual;
                P = neighbor;
            }
        }

        if (residual < bestResidual) {
            bestP = P;
            bestResidual = residual;
        }
    }

    return bestResidual;
}




