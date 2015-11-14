#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

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
	std::sort(city.begin(), city.end(), LeastNeighbours);

	for (unsigned i = 0; i < city.size(); i++)
	{
		std::queue<Intersection*> current_queue;
		for (unsigned i = 0; i < city.size(); i++)
		{
			current_queue.push(city[i]);
		}

		for (unsigned j = 0; j < i; j++)
		{
			Intersection* temp = current_queue.front();
			current_queue.pop();
			current_queue.push(temp);
		}

		int current_bins = n_bins;
		while (!current_queue.empty() && current_bins != 0)
		{
			Intersection* current = current_queue.front();
			current_queue.pop();
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
