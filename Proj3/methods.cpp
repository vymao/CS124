//
// Created by VMao on 4/19/20.
//
#include <iostream>
#include <vector>
#include "methods.h"
#include <stdlib.h>
using namespace std;


template <typename T> vector<T> deepCopy(vector<T> array) {
    int* deepCopy = new int[array.size()];
    for(int i = 0; i <array.size(); i++)
        deepCopy[i] = array[i];
    vector<T> v(deepCopy, deepCopy + array.size());
    return v;
}

long long Karmarkar_Karp(vector<long long> input) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    long long int max = -1;
    long long int max2 = -1;
    int index_max = 0;
    int index_max2 = 0;
    while (max2 != 0) {
        max = -1;
        max2 = -1;
        for (int i = 0; i <input_list.size(); i++) {
            if (input_list[i] > max) {
                max = input_list[i];
                index_max = i;
            }
        }

        for (int i = 0; i <input_list.size(); i++) {
            if (input_list[i] > max2 && input_list[i] != max) {
                max2 = input_list[i];
                index_max2 = i;
            }
        }

         long long int difference = llabs(max - max2);
        input_list[index_max] = 0;
        input_list[index_max2] = difference;
        //cout <<(difference == max) <<(difference == max2) <<"\n";

        //cout <<input_list[0] <<" " <<input_list[1] <<" " <<input_list[2] <<" " <<input_list[3] <<" " <<input_list[4] <<"\n";
    }

    for (int i = 0; i <input_list.size(); i++) {
        if (input_list[i] != 0) {
            return input_list[i];
        }
    }

    return 0;
}

vector<int> generateS(int n) {
    vector<int> values;
    for (int i = 0; i <n; i++) {
        int random_value = 0;
        while (random_value == 0) {
            random_value = (rand() % 3) - 1;
        }

        values.insert(values.end(), random_value);
    }
    return values;
}

vector<int> generateP(int n) {
    vector<int> values;
    for (int i = 0; i < n; i++) {
        values.insert(values.end(), 1 + (rand() % n));
    }
    return values;
}

vector<int> PPgetNeighbor(vector<int> input) {
    //vector<int> input_copy = deepCopy(input);
    vector<int> input_copy = input;
    bool finish = false;
    while (!finish) {
        int i = rand() % input.size();
        int j = rand() % input.size();
        if (input_copy[i] != (j + 1)) {
            input_copy[i] = j + 1;
            finish = true;
        }
    }
    return input_copy;
}

vector<long long int> computeCost(vector<int> input, vector<long long int> A) {
    vector<long long int> neighbor(A.size());
    for (int i = 0; i <A.size(); i++) {
         long long int value = 0;
        for (int j = 0; j <input.size(); j++) {
            if (input[j] == i + 1) {
                value += A[j];
            }
        }
        neighbor[i] = value;
    }
    return neighbor;
}

long long residualCompute(vector<int> sign, vector<long long int> A) {
     long long int residual = 0;
    for (int i = 0; i <sign.size(); i++) {
        residual += sign[i] * A[i];
    }
    return llabs(residual);
}

vector<int> getNeighbor(vector<int> input) {
    //vector<int> input_copy = deepCopy(input);
    srand(time(NULL) );
    vector<int> input_copy = input;
    int i = rand() % input.size();
    int j = rand() % input.size();
    int coinFlip = rand() % 2;
    if (coinFlip == 1) {
        input_copy[i] = input_copy[i] * -1;
    } else {
        input_copy[i] = input_copy[i] * -1;
        input_copy[j] = input_copy[i] * -1;
    }
    return input_copy;
}




