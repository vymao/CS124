#include <iostream>
#include <vector>
#include "methods.h"
#include "localSearch.h"
#include <fstream>
#include <stdlib.h>
#include <random>
using namespace std;


vector<long long> readFile(string file) {
    //vector<long long> values(100);
    vector<long long> values;
    string line;
    ifstream myfile(file);
    if (myfile.is_open()) {
        while ( getline (myfile,line) ) {
            char* endptr = NULL;
            long long val = strtoll(line.c_str(), &endptr, 10);
            values.insert(values.end(), val);
            //values[index] = val;
        }
        myfile.close();
    } else {
        cout << "Unable to open file";
    }
    return values;
}


int main(int argc, char *argv[]) {
    srand(time(NULL) );
    int flag;
    int algorithm;
    string input_file;

    flag = atoi(argv[1]);
    algorithm = atoi(argv[2]);
    input_file = string(argv[3]);
    int max_iter = 25000;
    if (flag == 0) {
        if (algorithm == 0) {
            vector<long long> values = readFile(input_file);
            long long residual = Karmarkar_Karp(values);
            cout << residual;
        }
        if (algorithm == 1) {
            vector<long long> values = readFile(input_file);
            long long residual = repeatedRandom(values, max_iter);
            cout << residual;
        }
        if (algorithm == 2) {
            vector<long long> values = readFile(input_file);
            long long residual = hillClimbing(values, max_iter);
            cout << residual;
        }
        if (algorithm == 3) {
            vector<long long> values = readFile(input_file);
            long long residual = simulatedAnnealing(values, max_iter);
            cout << residual;
        }
        if (algorithm == 11) {
            vector<long long> values = readFile(input_file);
            long long residual = PPrepeatedRandom(values, max_iter);
            cout << residual;
        }
        if (algorithm == 12) {
            vector<long long> values = readFile(input_file);
            long long residual = PPhillClimbing(values, max_iter);
            cout << residual;
        }
        if (algorithm == 13) {
            vector<long long> values = readFile(input_file);
            long long residual = PPsimulatedAnnealing(values, max_iter);
            cout << residual;
        }
    } else {
        /*
        vector<long long int> input = {10, 8, 7, 6, 5};
        vector<int> P = {1, 2, 2, 2, 5};
        vector<long long int> localCost = computeCost(P, input);
        long long int localResidual = Karmarkar_Karp(localCost);
        cout << localResidual;

        for (int i = 0; i < max_iter; i++) {
            vector<int> neighbor = generateP(input.size());
            vector<long long int> localCost = computeCost(neighbor, input_list);
            long long int localResidual = Karmarkar_Karp(localCost);
            if (localResidual < residual) {
                residual = localResidual;
            }
        }
         */
        random_device rd;
        mt19937_64 gen(rd());

        uniform_int_distribution<long long> distribution(1, llround(pow(10, 12)));
        cout << distribution(gen);

    }
}


