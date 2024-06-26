# java_final
2024 Spring NCKU CSIE Java Development
## 店面經營系統
### 功能
- 多個庫存點分別的庫存商品和數量
- 即時計算櫃台剩餘數量，並提醒補貨和從哪個庫存點拿
    - 依據庫存自動計算並顯示還能做幾份
- 收益管理和計算
    - 日期、成本、收益
### 架構
- 店家：名稱、地址、電話、店租、圖片
- 商品：所需材料及數量、販售價格、製作成本
- 庫存點：新增庫存材料及數量
- 櫃台：新增商品及數量
- 財務報表：商品成本、水電成本、店租成本、販售收入，時間允許可嫁接excel
### page flow
1. 新增店家
2. 新增商品(第3點)、庫存點(第4點)、財務圖表
3. 顯示商品的材料、數量、價格、成本
4. 庫存地點名稱、庫存材料及數量、新增材料
5. 財務報表：各品項收入、當日收入、當月收入

### 程式碼架構
**CommonClass 放屬性**
- material
- goods
- store
- inventory point
- inventory item
**Pages**
- 設定各自Panel的長相
**DataStore**
- 執行動作
- 放大家共同使用的東西

### database
- 使用mysql
- 帳號：root
- 密碼：javafinalproject
- 資料庫連接檔案在src/Database.java
- 目前有接上前後端的功能：
    - create store
    - get store
    - create inventory point
    - get inventory point
    - create inventory item