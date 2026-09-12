    static class BinaryLifting {
        private final int MAXN;
        private final int LOG;
        private ArrayList<ArrayList<Integer>> adj;
        private int[] in, out, depth;
        private int[][] ancestor;
        private int timer;

        public BinaryLifting(ArrayList<ArrayList<Integer>> adj) {
            this.adj = adj;
            MAXN = adj.size();
            LOG = 20;
            in = new int[MAXN];
            out = new int[MAXN];
            depth = new int[MAXN];
            ancestor = new int[MAXN][LOG];
            for (int i = 0; i < MAXN; i++) {
                Arrays.fill(ancestor[i], -1);
            }
            timer = 0;
        }

        public void dfs(int node, int parent) {
            in[node] = timer++;
            ancestor[node][0] = parent;
            if (parent != -1) {
                depth[node] = depth[parent] + 1;
            } else {
                depth[node] = 0;
            }

            for (int neighbor : adj.get(node)) {
                if (neighbor != parent) {
                    dfs(neighbor, node);
                }
            }
            out[node] = timer++;
        }

        public void computeBinaryLifting() {
            for (int i = 1; i < LOG; i++) {
                for (int j = 0; j < MAXN; j++) {
                    int mid = ancestor[j][i - 1];
                    if (mid != -1) {
                        ancestor[j][i] = ancestor[mid][i - 1];
                    }
                }
            }
        }

        public int lift(int node, int k) {
            for (int i = LOG - 1; i >= 0; i--) {
                if ((k & (1 << i)) != 0) {
                    node = ancestor[node][i];
                    if (node == -1) {
                        return -1;
                    }
                }
            }
            return node;
        }

        public boolean isAncestor(int u, int v) {
            return in[u] <= in[v] && out[u] >= out[v];
        }

        public int lca(int u, int v) {
            if (depth[u] < depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }

            u = lift(u, depth[u] - depth[v]);
            if (u == v) {
                return u;
            }

            for (int i = LOG - 1; i >= 0; i--) {
                if (ancestor[u][i] != -1 && ancestor[u][i] != ancestor[v][i]) {
                    u = ancestor[u][i];
                    v = ancestor[v][i];
                }
            }

            return ancestor[u][0];
        }

        public int distance(int u, int v) {
            int lca = lca(u, v);
            return depth[u] + depth[v] - 2 * depth[lca];
        }
    }

}
