#include <iostream>
#include <vector>
#include <algorithm>

struct Intersection
{
	bool has_bin = false;
	std::vector<Intersection*> adjacent;

	bool CanHaveBin() const
	{
		for (const auto& i : adjacent)
		{
			if (i->has_bin)
			{
				return false;
			}
		}
		return true;
	}
};

bool LeastNeighbours(const Intersection* i, const Intersection* j)
{
	return i->adjacent.size() < j->adjacent.size();
}

bool CanPlaceBins(std::vector<Intersection*>& city, int n_bins)
{
	std::stable_sort(city.begin(), city.end(), LeastNeighbours);

	for (unsigned i = 0; i < city.size(); i++)
	{
		int current_bins = n_bins;

		for (unsigned j = 0; j < city.size(); j++)
		{
			Intersection* current = city[(i + j) % city.size()];
			if (current->CanHaveBin())
			{
				current_bins--;
				current->has_bin = true;
			}

			if (current_bins == 0)
			{
				return true;
			}
		}

		for (const auto& i : city)
		{
			i->has_bin = false;
		}
	}

	return false;
}

int main()
{
	std::ios::sync_with_stdio();

	int n, m, k;
	std::cin >> n >> m >> k;
	std::vector<Intersection*> city(m);
	for (int i = 0; i < m; i++)
	{
		city[i] = new Intersection();
	}
	while (n-- > 0)
	{
		int a, b;
		std::cin >> a >> b;
		a--;
		b--;
		city[a]->adjacent.push_back(city[b]);
		city[b]->adjacent.push_back(city[a]);
	}

	if (CanPlaceBins(city, k))
	{
		std::cout << "possible" << std::endl;
	}
	else
	{
		std::cout << "impossible" << std::endl;
	}
    return 0;
}
