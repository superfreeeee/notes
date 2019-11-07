# Abstrac Factory 抽象工廠模式

## database simulation

### Interfaces
Name | Simulation
-|-
IUser | 操作User的接口
IType | 操作Type的接口
IFactory | 創造數據庫操作類的接口

### Classes
Name | Simulation
-|-
User | User表
Type | Type表
MySQLUser | MySQL操作User實現類
MySQLType | MySQL操作Type實現類
OracleUser | Oracle操作User實現類
OracleType | Oracle操作Type實現類
MySQLFactory | 創造MySQL操作類
OracleFactory | 創造Oracle操作類
Client | 客戶端(使用者)