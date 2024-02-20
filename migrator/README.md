# migrator

## 利用環境
- macOS Catalina 
  - v10.15.5
- dcoker desktop 
  - v3.5.2
- docker 
  - version 20.10.7, build f0df350
- docker-compose
  - version 1.29.2, build 5becea4c

## コマンド

- コンテナ起動
  - `docker-compose up -d`
- マイグレーション実行
  - `docker-compose run --rm flyway-migrate`
- マイグレーション履歴
  - `docker-compose run --rm flyway-info`
- MySQL実行
  - `docker exec -it twitter_like_app_db mysql -uroot -proot twitter_like_app`
