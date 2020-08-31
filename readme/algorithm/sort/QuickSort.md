# 快速排序

- 步骤

    - 从数列中挑出一个元素，称为 “基准”（pivot）;
    
    - 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
    
    - 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；

- [动画演示](../../../其他/algorithm/sort/QuickSort.gif)

- [实现](../../../src/main/java/xyz/zzyitj/demo/algorithm/sort/QuickSort.java)