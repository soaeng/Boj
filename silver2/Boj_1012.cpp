#include <iostream>
using namespace std;
int M, N, K;
bool map[50][50];
int deltas[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

void init() {
  for(int r=0; r<N; r++) {
    for (int c = 0; c < M; c++) {
      map[r][c] = 0;
    }
  }
}

void dfs(int r, int c) {
  map[r][c] = 0;
  for(int d=0; d<4; d++) {
    int nr = r + deltas[d][0];
    int nc = c + deltas[d][1];
    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    if(!map[nr][nc])
      continue;
    map[nr][nc] = 0;
    dfs(nr, nc);
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  int T;
  cin >> T;
  for (int tc = 0; tc < T; tc++) {
    init();
    cin >> M >> N >> K;
    int count = 0;
    while(K--) {
      int r, c;
      cin >> c >> r;
      map[r][c] = 1;
    }
    for(int r=0; r<N; r++) {
      for(int c=0; c<M; c++) {
        if(!map[r][c])
          continue;
        count++;
        dfs(r, c);
      }
    }
    cout << count << "\n";
  }
}