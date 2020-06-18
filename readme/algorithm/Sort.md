- 排序
  - [冒泡排序](./sort/BubbleSort.md)
  - [选择排序](./sort/SelectSort.md)
  - [插入排序](./sort/InsertSort.md)
  - [快速排序](./sort/QuickSort.md)

- 区别

| 排序法 | 平均时间 | 最差情形 | 稳定度 | 额外空间 | 备注 |
| :---: | :-----: | :----: | :---: | :-----: | :---: |
| 冒泡 | O(n2) |	O(n2) | 稳定	| O(1) | n小时较好 |
| 交换 | O(n2) | O(n2) | 不稳定 | O(1) |	n小时较好 |
| 选择 | O(n2) | O(n2) | 不稳定 | O(1) | n小时较好 |
| 插入 | O(n2) | O(n2) | 稳定 | O(1) | 大部分已排序时较好 |
| 基数 | O(logRB) | O(logRB) | 稳定 | O(n) | B是真数(0-9)，R是基数(个十百) |
| Shell | O(nlogn) | O(ns)1<s<2 | 不稳定 | O(1) | s是所选分组 |
| 快速 | O(nlogn) | O(n2) | 不稳定 | O(nlogn) | n大时较好 |
| 归并 | O(nlogn) | O(nlogn) | 稳定 | O(1) | n大时较好 |
| 堆 | O(nlogn) | O(nlogn) | 不稳定 | O(1) | n大时较好 |
