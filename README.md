# Social Media - Backend

Note:
- IntelliJ IDEA + Gradle
- JDK 17
- Spring Boot 3.4.4
- Oracle 19c + SQL Developer
- localhost:8080
- 身分驗證：JWT

每張資料表有設定序列(例如：SEQ_USERS) 與觸發器(例如：TRG_USERS_ID) 自動新增 id

系統架構：
1. 展示層(Controller)
2. 業務層(Service)
3. 資料層(DAO、Repository、Entity)
4. 共用層(DTO、Utils、Exception、Response、Enums、Config、Valid)

總共有 10 支 API：
1. 使用者註冊(`/api/user/register`)
2. 使用者登入(`/api/user/login`)
3. 使用者登出(`/api/user/logout`)
4. 查詢使用者資訊(`/api/user/query-user`)
5. 新增貼文(`/api/post/create-post`)：新增時會自動添加一條留言，內容為發文時間。
6. 查詢所有貼文(`/api/post/query-posts`)
7. 編輯貼文(`/api/post/update-post`)
8. 刪除貼文(`/api/post/delete-post`)：會連貼文內的留言一併刪除。
9. 新增留言(`/api/comment/create-comment`)
10. 查詢一篇貼文的所有留言(`/api/comment/query-comments`)
