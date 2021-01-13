//
// Created by VMao on 4/23/20.
//

#include <iostream>
#include <vector>
#include <fstream>
#include <stdlib.h>
#include <math.h>
#include <random>
#include <chrono>

using namespace std;
using namespace std::chrono;

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
    for (int i = 0; i <n; i++) {
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


long long repeatedRandom(vector<long long int> input, int max_iter) {
    //vector<long long int> input_list = deepCopy(input);
    vector<long long int> input_list = input;
    vector<int> S = generateS(input.size());
    long long int residual = residualCompute(S, input_list);
    for (int i = 0; i < max_iter; i++) {
        vector<int> neighbor = generateS(input.size());
        long long int localResidual = residualCompute(neighbor, input_list);
        if (localResidual <residual) {
            residual = localResidual;
            S = neighbor;
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
            P = neighbor;
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

        random_device rd;
        mt19937_64 gen(rd());

        uniform_int_distribution<long long> distribution(1, 1000000000000);
        max_iter = 25000;

        ofstream output;
        output.open ("/Users/vmao/Desktop/CS124/Proj3/100trials_output.txt");
        output << "Karmarkar_Karp \t repeatedRandom \t hillClimbing \t simulatedAnnealing "
                  "\t PPrepeatedRandom \t PPhillClimbing \t PPsimulatedAnnealing\n";
        output.close();

        ofstream times;
        times.open ("/Users/vmao/Desktop/CS124/Proj3/100trials_times.txt");
        times << "Karmarkar_Karp \t repeatedRandom \t hillClimbing \t simulatedAnnealing "
                  "\t PPrepeatedRandom \t PPhillClimbing \t PPsimulatedAnnealing\n";
        times.close();


        for (int t = 0; t < 100; t++) {
            vector<long long> inputs;
            for (int i = 0; i < 100; i++) {
                inputs.insert(inputs.end(), distribution(gen));
            }
            auto start = high_resolution_clock::now();
            long long KKresidual = Karmarkar_Karp(inputs);
            auto end = high_resolution_clock::now();
            auto duration1 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long RRresidual = repeatedRandom(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration2 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long HCresidual = hillClimbing(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration3 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long SAresidual = simulatedAnnealing(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration4 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long PPRRresidual = PPrepeatedRandom(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration5 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long PPHCresidual = PPhillClimbing(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration6 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            start = high_resolution_clock::now();
            long long PPSAresidual = PPsimulatedAnnealing(inputs, max_iter);
            end = high_resolution_clock::now();
            auto duration7 = std::chrono::duration_cast<std::chrono::microseconds>( end - start ).count();

            output.open ("/Users/vmao/Desktop/CS124/Proj3/100trials_output.txt", ios::app);
            output << KKresidual << "\t" << RRresidual << "\t" << HCresidual << "\t" << SAresidual <<
                    "\t" << PPRRresidual << "\t" << PPHCresidual << "\t" << PPSAresidual << "\n";
            output.close();

            times.open ("/Users/vmao/Desktop/CS124/Proj3/100trials_times.txt", ios::app);
            times << duration1 << "\t" << duration2 << "\t" << duration3 << "\t" << duration4 <<
                   "\t" << duration5 << "\t" << duration6 << "\t" << duration7 << "\n";
            times.close();
        }
    }

}








