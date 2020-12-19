一方面，我们有：
$$
\begin{align}P(n)&=\sum_{i=0}^da_in^i
\\ &=(a_0+a_1n+...+a_{d-1}n^{d-1})+a_dn^d
\\&\geq (-|a_0|-|a_1|n-...-|a_{d-1}|n^{d-1})+a_dn^d
\\&\geq (-|a_0|n^{d-1}-|a_1|n^{d-1}-...-|a_{d-1}|n^{d-1})+a_dn^d
\\&\geq n^{d-1}(a_dn-\sum_i^{d-1}|a_i|)
\end{align}
$$
故取$c_1=\frac{a_d}2$，当$n=n_0>[2\cdot \frac{\sum_i^{d-1}|a_i|}{a_d}]+2$时，我们有
$$
\begin{aligned}P(n)-c_1n^d&\geq n^{d-1}(a_dn-\sum_i^{d-1}|a_i|) - \frac{a_d}2n^d
\\&=n^{d-1}(\frac{a_d}2n-\sum_i^{d-1}|a_i|)
\\&\geq n^{d-1}(\frac{a_d}2(2\cdot \frac{\sum_i^{d-1}|a_i|}{a_d}-1+2)-\sum_i^{d-1}|a_i|)
\\&\geq \frac{a_d}{2}n^{d-1}
\\&\geq 0
\end{aligned}
$$
因此



另一方面，我们有：
$$
\begin{align}P(n) &=\sum_{i=0}^da_in^i
\\&=(a_0+a_1n+...+a_{d-1}n^{d-1})+a_dn^d
\\&\leq (|a_0|+|a_1|n+...+|a_{d-1}|n^{d-1})+|a_d|n^d
\\&\leq (|a_0|n^d+|a_1|n^d+...+|a_{d-1}|n^d)+|a_d|n^d
\\&= (|a_0|+|a_1|+...+|a_{d-1}|+|a_d|)n^d
\\&= \sum_{i=0}^d|a_i|n^d
\end{align}
$$
故取$c_2=\sum_{i=0}^d|a_i|$，当$n=n_0>[\frac{\sum_i^{d-1}|a_i|}{|a_d|}]+2$时，我们有$P(n)\leq a_dn^d$

综上，我们有 $n=n_0>[2\cdot \frac{\sum_i^{d-1}|a_i|}{|a_d|}]+2$时，
$$
c_1n^d\leq P(n)\leq c_2n^d
$$



$\forall c>0$,令$c=c_1\cdot c_2,\ c_1,c_2>0$


由于$f(n)=o(g(n))$，对于$c_1>0$，存在$n_1$使得，$\forall n>n_1$时，$f(n)<c_1g(n)$

同理$g(n)=o(h(n))$，对于$c_2>0$，存在$n_0$使得，$\forall n>n_2$时，$g(n)<c_2h(n)$

存在$n_0=max\{n_1,n_2\}$使得，$n>max\{n_1,n_2\}$时，
$$
f(n)<c_1g(n)<c_1\cdot c_2h(n)<(c_1c_2)h(n)=ch(n)
$$
因此$f(n)=o(h(n))$

