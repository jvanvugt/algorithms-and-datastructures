#include <iostream>
#include <vector>

int round(int cost)
{
    int remainder = cost % 5;
    if(remainder < 3)
    {
        return cost - remainder;
    }
    return cost + (5 - remainder);
}

int min_cost(int products[], int n, int k)
{
    // 3 dimensional vector, for each number of products and number of dividers
    // the rounded price and the current order(unrounded)
    std::vector<std::vector<std::vector<int> > > V(n, std::vector<std::vector<int> >(k, std::vector<int>(2)));
    for(int i = 0; i < k; i++)
    {
        for(int j = 1; j < n; j++)
        {
            // Don't place the divider
            V[j][i][0] = V[j - 1][i][0];
            V[j][i][1] = V[j - 1][i][1] + products[j];

            // Place the divider
            if(i > 0)
            {
                int divider_rounded = V[j - 1][i - 1][0] + round(V[j - 1][i - 1][1] + products[j]);
                if(divider_rounded < V[j][i][1] + V[j][i][0])
                {
                    V[j][i][0] = divider_rounded;
                    V[j][i][1] = 0;
                }
            }
        }
    }
    return V[n - 1][k - 1][0] + round(V[n - 1][k - 1][1]);
}

int main(int argc, char const *argv[])
{
    int n, k;
    std::cin >> n >> k;
    n++;
    k++;
    int products[n];
    products[0] = 0;
    for(int i = 1; i < n; i++)
    {
        std::cin >> products[i];
    }
    std::cout << min_cost(products, n, k) << std::endl;
    return 0;
}
