#include <iostream>
#include <algorithm>

bool can_place_bin(int c, bool bins[], bool edges[], int n)
{
	for (int i = 0; i < n; i++)
	{
		if (edges[c + i * n] && bins[i])
		{
			return false;
		}
	}
	return true;
}

int max_bins(bool bins[], bool edges[], int c, int n)
{
	if (c == n)
	{
		return 0;
	}
	int with_c = 0;
	if (can_place_bin(c, bins, edges, n))
	{
		bins[c] = true;
		with_c = 1 + max_bins(bins, edges, c + 1, n);
		bins[c] = false;
	}
	int without_c = max_bins(bins, edges, c + 1, n);
	return std::max(with_c, without_c);
}

int main()
{
	std::ios::sync_with_stdio(false);
	int m, n, k;
	std::cin >> m >> n >> k;
	bool bins[n];
	bool edges[n * n];
	for(int i = 0; i < n * n; i++)
	{
		edges[i] = false;
	}
	for (int i = 0; i < n; i++)
	{
		bins[i] = false;
	}
	while (m-- > 0)
	{
		int i, j;
		std::cin >> i >> j;
		i--;
		j--;
		edges[i + j * n] = edges[j + i * n] = true;
	}
	int placed_bins = max_bins(bins, edges, 0, n);
	std::cout << placed_bins << std::endl;
	if (placed_bins >= k)
	{
		std::cout << "possible" << std::endl;
	}
	else
	{
		std::cout << "impossible" << std::endl;
	}
	return 0;
}