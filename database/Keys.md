# 重點概念 / 名詞

Abbreviation | Full Name | Translation | Description
-|-|-
RDBMS | Relational Database Management System | 關係型數據庫
CAP | - | CAP原則：Consistency, Availability, Partition tolerance
DDL | Data Define Language | 數據定義語言
DML | Data Manipulate Language | 數據操作語言
BSON | 
RRD | Round Robin Database | 

## CAP
- Consistency(C)一致性：所有节点在同一时间具有相同的数据
- Availability(A)可用性：保证每个请求不管成功或者失败都有响应
- Partition tolerance(P)分區容忍性： 系统中任意信息的丢失或失败不会影响系统的继续运作

*理論核心：最多同時滿足兩點*

可區分成三種集群
- CA: 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。
- CP: 满足一致性，分区容忍性的系统，通常性能不是特别高。
- AP: 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。
